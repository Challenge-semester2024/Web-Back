package Challengesemester2024.domain.manager.service;

import Challengesemester2024.Exception.collections.business.DuplicateUniqueKeyException;
import Challengesemester2024.Exception.collections.business.PasswordNotMatchException;
import Challengesemester2024.Exception.collections.business.ManagerAlreadyExistsException;
import Challengesemester2024.Exception.collections.business.UsernameNotFoundException;
import Challengesemester2024.SpringSecurity.jwt.JwtTokenProvider;
import Challengesemester2024.SpringSecurity.jwt.dto.AccessTokenDto;
import Challengesemester2024.SpringSecurity.jwt.dto.AllJwtTokenDto;
import Challengesemester2024.SpringSecurity.jwt.dto.RefreshTokenDto;
import Challengesemester2024.businessProcess.auth.dto.SignInDto;
import Challengesemester2024.businessProcess.auth.dto.SignUpDto;
import Challengesemester2024.domain.manager.model.Manager;
import Challengesemester2024.domain.manager.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerServiceImpl implements MangerService{
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void checkExits(SignUpDto.Manager manager) {
        //해당 멤버가 존재하는지 확인
        Optional<Manager> found = this.managerRepository.findByEmailId(manager.getEmail());
        if(found.isPresent()) throw new ManagerAlreadyExistsException();

        //비밀번호가 올바른지 확인
        if(!manager.getPassword().equals(manager.getCheckPassword())) throw new PasswordNotMatchException();
    }

    @Override
    public void register(SignUpDto.Manager manager) {
        String encodePassword = passwordEncoder.encode(manager.getPassword());
        Manager managerEntity = new Manager(manager.getEmail(), encodePassword, manager.getPhoneNum());

        try {
            managerRepository.save(managerEntity);
        } catch (DataIntegrityViolationException e) { //unique 키 중복시 발생시킬 오류
            throw new DuplicateUniqueKeyException();
        }

    }

    @Override
    public AllJwtTokenDto signIn(SignInDto signInDto) { //UserDetails 객체를 활용한 인가 진행
        Optional<Manager> found = managerRepository.findByEmailId(signInDto.getEmail());

        //이메일 존재하지 않는 경우
        if(!found.isPresent()) throw new UsernameNotFoundException();
        //비밀번호 다른 경우
        if(!passwordEncoder.matches(signInDto.getPassword(), found.get().getPassword())){
            throw new PasswordNotMatchException();
        }

        //토큰 발급
        AccessTokenDto accessTokenDto = jwtTokenProvider.createAccessToken(found.get().getEmailId(), String.valueOf(found.get().getRole()));
        RefreshTokenDto refreshTokenDto = jwtTokenProvider.createRefreshToken(found.get().getEmailId(), String.valueOf(found.get().getRole()));

        return AllJwtTokenDto
                .builder()
                .accessTokenDto(accessTokenDto)
                .refreshTokenDto(refreshTokenDto)
                .build();
    }

}

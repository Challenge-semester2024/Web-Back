package Challengesemester2024.domain.manager.service;

import Challengesemester2024.Exception.collections.business.DuplicateUniqueKeyException;
import Challengesemester2024.Exception.collections.business.PasswordNotMatchException;
import Challengesemester2024.Exception.collections.business.UserAlreadyExistsException;
import Challengesemester2024.Exception.collections.business.UsernameNotFoundException;
import Challengesemester2024.SpringSecurity.jwt.JwtTokenProvider;
import Challengesemester2024.SpringSecurity.jwt.dto.AccessTokenDto;
import Challengesemester2024.SpringSecurity.jwt.dto.AllJwtTokenDto;
import Challengesemester2024.SpringSecurity.jwt.dto.RefreshTokenDto;
import Challengesemester2024.businessProcess.facade.dto.ManagerRegisterDto;
import Challengesemester2024.businessProcess.auth.web.dto.WebSignInDto;
import Challengesemester2024.businessProcess.auth.web.dto.WebSignUpDto;
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
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void checkExits(WebSignUpDto.ceoInfo manager) {
        //해당 멤버가 존재하는지 확인
        Optional<Manager> found = this.managerRepository.findByEmailId(manager.getEmail());
        if(found.isPresent()) throw new UserAlreadyExistsException();

    }

    @Override
    public void register(WebSignUpDto.ceoInfo manager, ManagerRegisterDto managerRegisterDto) {
        String encodePassword = passwordEncoder.encode(manager.getPassword());

        Manager managerEntity = Manager.builder()
                .emailId(manager.getEmail())
                .password(encodePassword)
                .phoneNum(manager.getPhoneNum())
                .role(Manager.ManagerRoleEnum.User)
                .childCenter(managerRegisterDto.getChildCenter())
                .build();

        try {
            managerRepository.save(managerEntity);
        } catch (DataIntegrityViolationException e) { //unique 키 중복시 발생시킬 오류
            throw new DuplicateUniqueKeyException();
        }

    }

    @Override
    public AllJwtTokenDto signIn(WebSignInDto webSignInDto) { //UserDetails 객체를 활용한 인가 진행
        Optional<Manager> found = managerRepository.findByEmailId(webSignInDto.getEmail());

        //이메일 존재하지 않는 경우
        if(!found.isPresent()) throw new UsernameNotFoundException();
        //비밀번호 다른 경우
        if(!passwordEncoder.matches(webSignInDto.getPassword(), found.get().getPassword())){
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

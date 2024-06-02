package Challengesemester2024.domain.volunteer.service;

import Challengesemester2024.Exception.collections.business.UserAlreadyExistsException;
import Challengesemester2024.Exception.collections.business.PasswordNotMatchException;
import Challengesemester2024.Exception.collections.business.UsernameNotFoundException;
import Challengesemester2024.SpringSecurity.jwt.JwtTokenProvider;
import Challengesemester2024.SpringSecurity.jwt.dto.AccessTokenDto;
import Challengesemester2024.SpringSecurity.jwt.dto.AllJwtTokenDto;
import Challengesemester2024.SpringSecurity.jwt.dto.RefreshTokenDto;
import Challengesemester2024.businessProcess.auth.app.dto.AppSignInDto;
import Challengesemester2024.businessProcess.auth.app.dto.AppSignUpDto;
import Challengesemester2024.businessProcess.auth.app.dto.CheckExitsVoluteerDto;
import Challengesemester2024.businessProcess.util.UtilService;
import Challengesemester2024.domain.volunteer.model.Volunteer;
import Challengesemester2024.domain.volunteer.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoluteerServiceImpl implements VoluteerService {
    private final VolunteerRepository volunteerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UtilService utilService;

    @Override
    public void checkExits(CheckExitsVoluteerDto checkExits ) {
        //해당 멤버가 존재하는지 확인
        Optional<Volunteer> foundbyEmail = this.volunteerRepository.findByEmailId(checkExits.getEmail());
        Optional<Volunteer> foundByPhoneNUm = this.volunteerRepository.findByPhoneNum(checkExits.getPhonNum());
        if(foundByPhoneNUm.isPresent() || foundbyEmail.isPresent()) throw new UserAlreadyExistsException();
    }

    @Override
    public void signUp(AppSignUpDto appSignUpDto) {
        String encodePassword = passwordEncoder.encode(appSignUpDto.getPassword());

        Volunteer volunteer = Volunteer.builder()
                .name(appSignUpDto.getName())
                .emailId(appSignUpDto.getEmailId())
                .password(encodePassword)
                .phoneNum(appSignUpDto.getPhoneNum())
                .birth(appSignUpDto.getBirth())
                .role(Volunteer.UserRoleEnum.USER)
                .build();

        volunteer.setGender(appSignUpDto.isGender());
        volunteerRepository.save(volunteer);

    }

    @Override
    public AllJwtTokenDto signIn(AppSignInDto appSignInDto) {
        Optional<Volunteer> foundVoluteer = volunteerRepository.findByEmailId(appSignInDto.getEmail());

        if(foundVoluteer.isEmpty()) throw new UsernameNotFoundException();
        if(!passwordEncoder.matches(appSignInDto.getPassword(), foundVoluteer.get().getPassword())){
            throw new PasswordNotMatchException();
        }

        AccessTokenDto accessTokenDto = jwtTokenProvider.createAccessToken(foundVoluteer.get().getPhoneNum(), String.valueOf(foundVoluteer.get().getRole()));
        RefreshTokenDto refreshTokenDto = jwtTokenProvider.createRefreshToken(foundVoluteer.get().getPhoneNum(), String.valueOf(foundVoluteer.get().getRole()));

        return AllJwtTokenDto
                .builder()
                .accessTokenDto(accessTokenDto)
                .refreshTokenDto(refreshTokenDto)
                .build();
    }

    @Override
    public Volunteer getVolunteeerPK(Authentication authentication) {
        return volunteerRepository.getVolunteerPk(authentication);
    }


}

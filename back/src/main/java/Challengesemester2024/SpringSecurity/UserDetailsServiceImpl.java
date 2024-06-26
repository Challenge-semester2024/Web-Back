package Challengesemester2024.SpringSecurity;

import Challengesemester2024.domain.manager.model.Manager;
import Challengesemester2024.domain.manager.repository.ManagerRepository;
import Challengesemester2024.domain.volunteer.model.Volunteer;
import Challengesemester2024.domain.volunteer.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ManagerRepository managerRepository;
    private final VolunteerRepository volunteerRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        // 이메일 또는 전화번호를 기반으로 사용자 로드
        if (identifier.contains("@")) {
            // 이메일로 Manager 로드
            Manager manager = managerRepository.findByEmailId(identifier)
                    .orElseThrow(() -> new UsernameNotFoundException("Manager Not Found"));
            return new UserDetailsImpl(manager.getEmailId(), manager.getRole().name());
        } else {
            // 전화번호로 Volunteer 로드
            Volunteer volunteer = volunteerRepository.findByPhoneNum(identifier)
                    .orElseThrow(() -> new UsernameNotFoundException("Volunteer Not Found"));
            return new UserDetailsImpl(volunteer.getPhoneNum(), volunteer.getRole().name());
        }
    }

    // 기존 메서드는 유지하되, 비즈니스 로직 변경에 따라 새 메서드를 활용하도록 수정
    public UserDetails loadUserByIdentifier(String identifier) throws UsernameNotFoundException {
        return loadUserByUsername(identifier);
    }
}

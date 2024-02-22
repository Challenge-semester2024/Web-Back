package Challengesemester2024.SpringSecurity;

import Challengesemester2024.domain.manager.model.Manager;
import Challengesemester2024.domain.manager.repository.ManagerRepository;
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

    @Override
    public UserDetails loadUserByUsername(String managerEmail) throws UsernameNotFoundException {
        final Manager manager = managerRepository.findByEmailId(managerEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Manager Not Found"));
        return new UserDetailsImpl(manager, manager.getRole());
      }

}
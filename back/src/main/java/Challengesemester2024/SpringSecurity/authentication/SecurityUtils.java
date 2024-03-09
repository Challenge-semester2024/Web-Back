package Challengesemester2024.SpringSecurity.authentication;

import Challengesemester2024.Exception.collections.business.UnAuthenticatedUserAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    public AuthenticatedEmailDTO getAuthenticationEmail(){
        Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();

        if(!authentication.isAuthenticated()){ //인증된 상태가 아닐 때, 에러 처리
            throw new UnAuthenticatedUserAccessException();
        }

        AuthenticatedEmailDTO authenticatedEmailDTO = AuthenticatedEmailDTO.builder()
                .email(authentication.getName())
                .build();
        return authenticatedEmailDTO;
    }

}

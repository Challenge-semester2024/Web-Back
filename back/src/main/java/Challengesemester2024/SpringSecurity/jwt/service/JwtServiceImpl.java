package Challengesemester2024.SpringSecurity.jwt.service;

import Challengesemester2024.Exception.collections.business.TokenMissingException;
import Challengesemester2024.SpringSecurity.jwt.JwtTokenProvider;
import Challengesemester2024.SpringSecurity.jwt.dto.AccessTokenDto;
import Challengesemester2024.businessProcess.redis.dto.TokenSubAndRoleDto;
import Challengesemester2024.Exception.collections.redis.RefreshTokenExpirationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService{
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AccessTokenDto updateAccessToken(HttpServletRequest request) {
        //리프래시 토큰이 있는지 검증
        String refreshToken = jwtTokenProvider.resolveToken(request);
        if(refreshToken.equals(null)) throw new TokenMissingException();

        boolean isRefreshTokenValid = jwtTokenProvider.validateRefreshTokenInRedis(refreshToken);

        //해당 토큰이 레디스에 존재하는지 + 토큰 자체의 유효성 검증
        if (!isRefreshTokenValid) throw new RefreshTokenExpirationException();

        TokenSubAndRoleDto tokenSubAndRoleDto = jwtTokenProvider.getSubjectAndRoleFromRefresh(refreshToken);
        return jwtTokenProvider.createAccessToken(tokenSubAndRoleDto.getAccountId(), tokenSubAndRoleDto.getRole());
    }

}

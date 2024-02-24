package Challengesemester2024.SpringSecurity.jwt;

import Challengesemester2024.SpringSecurity.jwt.dto.AccessTokenDto;
import Challengesemester2024.SpringSecurity.jwt.dto.RefreshTokenDto;
import Challengesemester2024.SpringSecurity.jwt.redis.model.RedisRefreshTokenDto;
import Challengesemester2024.businessProcess.redis.dto.TokenSubAndRoleDto;
import Challengesemester2024.businessProcess.redis.service.JwtRedisService;
import Challengesemester2024.config.Constants;
import Challengesemester2024.config.spring.JwtProps;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;

import java.util.Date;

import static Challengesemester2024.config.Constants.BEARER_TYPE;

@Component
@Slf4j
public class JwtTokenProvider {
    private SecretKey secretKey;
    private Long refreshTokenExpirationMinutes;
    private Long accessTokenExpirationMinutes;
    private final JwtRedisService redisService;
    public JwtTokenProvider(JwtProps jwtProps, JwtRedisService redisService) {
        refreshTokenExpirationMinutes = jwtProps.getRefreshTokenExpirationMinutes();
        accessTokenExpirationMinutes = jwtProps.getAccessTokenExpirationMinutes();
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtProps.getSecretKey()));
        this.redisService = redisService;
    }

    public String resolveAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TYPE)) {
            return bearerToken.substring(7);
        }
        return null;
    }
    //헤더에 포함되어 날아온 Authorization을 가져온다

    public String validateAdminAccessTokenData(String token) {
        // SignatureException :  비밀키 일치 X 처리
        //ExpiredJwtException :  만료 exception 처리
        Jws<Claims> claimsJws = Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token);

        return claimsJws.getPayload().getSubject();
        //여기에 해당 클레임의 subject가 우리의 레포에도 존재하는지 확인해..줘야 하나? 이미 비밀키를 통해 확인하는디
    }

    public AccessTokenDto createAccessToken(String managerEmailId, String roles) {
        JwtBuilder token = Jwts.builder();

        Date now = new Date();
        Date accessExpiredTime = new Date(now.getTime() + accessTokenExpirationMinutes );

        String jwt = Jwts.builder()
                .header().type(Constants.JWT)
                .and()
                .claims()
                .subject(managerEmailId)
                .add("role",roles)
                .issuedAt(now)
                .expiration(accessExpiredTime)
                .and()
                .signWith(secretKey,SignatureAlgorithm.HS256)
                .compact();

        return AccessTokenDto.builder()
                .accessToken(jwt)
                .accessTokenExpiredTime(accessExpiredTime)
                .build();

    }

    public RefreshTokenDto createRefreshToken(String managerEmailId, String roles) {
        JwtBuilder token = Jwts.builder();

        Date now = new Date();
        Date refreshExpiredTime = new Date(now.getTime() + refreshTokenExpirationMinutes );


        String jwt = Jwts.builder()
                .header().type(Constants.JWT)
                .and()
                .claims()
                .subject(managerEmailId)
                .add("role",roles)
                .issuedAt(now)
                .expiration(refreshExpiredTime)
                .and()
                .signWith(secretKey,SignatureAlgorithm.HS256)
                .compact();

        RedisRefreshTokenDto refreshTokenDto = RedisRefreshTokenDto.builder()
                .id(managerEmailId)
                .refrehToken(jwt)
                .build();

        redisService.save(refreshTokenDto);

        return RefreshTokenDto.builder()
                .refreshToken(jwt)
                .refreshTokenExpiredTime(refreshExpiredTime)
                .build();

    }

    public boolean validateRefreshTokenInRedis(String token) {
        // SignatureException :  비밀키 일치 X 처리
        //ExpiredJwtException :  만료 exception 처리
        Jws<Claims> claimsJws = Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token);

        //sub와 권한 가져옴
        String sub = claimsJws.getPayload().getSubject();
        if(redisService.find(sub).equals(null)) return false;

        //토큰이 만료된 거라면 false, 아니면 true 반환
        return !claimsJws.getPayload().getExpiration().before(new Date());
    }

    public TokenSubAndRoleDto getSubjectAndRoleFromRefresh (String token) {
        //토큰 기반 회원 정보 추출
        //jwt parser 을 통해 SecretKey를 설정
        Jws<Claims> claimsJws = Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token);

        //claim을 추출해서 토큰을 생성할때 넣었던 sub 값과 권한 추출.
        String sub = claimsJws.getPayload().getSubject();
        String role = (String) claimsJws.getPayload().get("role");

        TokenSubAndRoleDto tokenSubAndRoleDto = TokenSubAndRoleDto.builder()
                .accountId(sub)
                .role(role)
                .build();
        //account ID 와 roles 을 반환

        return tokenSubAndRoleDto;
    }



}

//        Long ChildCenter_Id = claims.
//
//        Optional<ChildCenter> found = childCenterRepository.findBychildcenter_id(Long.valueOf(claims.getId()));
//        //사장 account ID 로 해당 사장이 관리 하는 매장의 DB 식별 Primary Key 를 get
//
//        if (!found.isPresent()){
//            throw new CenterByManagerNotFoundException();
//            //해당 사장이 관리하는 매장을 찾을 수 없음
//        }

//claims.put("storeId" , storeIdByOwnerAccountId);
//해당 사장이 관리하는 매장의 식별 ID 를 클레임에 주입
//API 에서 매장 식별 키를 인자로 최대한 받지 않기 위함
//이건 나중에 보고 넣든가 말든가 하기

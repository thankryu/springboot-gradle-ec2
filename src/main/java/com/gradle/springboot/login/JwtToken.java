package com.gradle.springboot.login;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Slf4j
@Component
public class JwtToken {

    private String SECRET_KEY = "secret";

    /**
     * JWT 토큰 생성
     * @return
     */
    public String makeJwtToken(UserVo userVo) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // 헤더 타입
                .setIssuer("fresh") // 토큰 발급자
                .setIssuedAt(now) // 발급 시간 (Date 타입만 추가 가능 )
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // 만료시간 (Date 타입만 추가 가능)
                .claim("id", userVo.getId())
                .claim("email", userVo.getEmail())
                .claim("password", userVo.getPassword())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 해싱 알고리즘, 시크릿 키
                .compact();
    }

    /**
     * header에 담긴 jwt 토큰 값 꺼내기
     * @param authorizationHeader
     * @return
     */
    public UserVo getUserVo(String authorizationHeader){

        log.info("authorizationHeader::"+authorizationHeader);
        validationAuthorizationHeader(authorizationHeader);
        String token = extractToken(authorizationHeader);

        Claims claims = parsingToken(token);
        log.info("claims::"+claims.toString());
        return new UserVo(claims);
    }

    /**
     * header 유효성 검사
     * @param header
     */
    private void validationAuthorizationHeader(String header){
        if(header == null || !header.startsWith("Bearer ")){
            throw new IllegalArgumentException();
        }
    }
    /**
     * 토큰 추출
     * @param authorizationHeader
     * @return
     */
    private String extractToken(String authorizationHeader) {
        return authorizationHeader.substring("Bearer ".length());
    }

    /**
     * 토큰 복호화
     * @param token
     * @return
     */
    private Claims parsingToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

}

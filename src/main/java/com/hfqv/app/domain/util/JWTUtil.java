package com.hfqv.app.domain.util;

import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class JWTUtil {

    private static Logger logger = LoggerFactory.getLogger(JWTUtil.class);
    @Value("${app.jwt.secretKey}")
    private String jwtSecretKey;
    private static final long EXP_TIME = 30*60*1000; // 30 minutos de duracion
    public String generateToken(String userToken) {

        return Jwts.builder()
                .setSubject(userToken)
                .setExpiration(new Date(System.currentTimeMillis()
                                                        + EXP_TIME)
                )
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }

    public Boolean validateToken(String token, String userLogin) {
        logger.info("userLogin:"+userLogin);
        logger.info("token:"+token);
        if( (token==null || token.isEmpty())){
            return false;
        }
        if(userLogin==null || userLogin.isEmpty()){
            return false;
        }
        String userIdentifier = getUserTokenFromToken(token);

        logger.info("userIdentifier:",userIdentifier);
        logger.info("userLogin:",userLogin);
        //TODO validar si token esta expirado
        return userIdentifier.equals(userLogin);
    }

    private String getUserTokenFromToken(String tokenCode) {

        return extractClaimFromToke(tokenCode, Claims::getSubject);
    }

    private <T> T extractClaimFromToke(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder()
                                .setSigningKey(jwtSecretKey)
                                .build()
                                .parseClaimsJws(token)
                                .getBody();
        return claimsResolver.apply(claims);
    }



}

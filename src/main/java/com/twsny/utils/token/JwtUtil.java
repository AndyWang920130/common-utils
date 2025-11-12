package com.twsny.utils.token;

import com.twsny.constants.TimeStampConstants;
import com.twsny.utils.date.DateUtil;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private static Key key;
    private static long expiredMilliseconds;
    static {
        String secret = "21313wdqwwe";
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        key = Keys.hmacShaKeyFor(keyBytes);

        expiredMilliseconds = TimeStampConstants.ONE_DAY_TIMESTAMP;
    }

    public static String createToken(String subject, String claimKey, String claimValue) {
        Map<String, String> claimMap = new HashMap<>();
        claimMap.put(claimKey, claimValue);
        JwtBuilder jwtBuilder = initJwtBuilder(subject, claimMap);
        return jwtBuilder.compact();
    }

    private static JwtBuilder initJwtBuilder(String subject, Map<String, String> claimMap) {
        long now = DateUtil.currentMillis();
        Date validity = new Date(now + expiredMilliseconds);
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(subject)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity);
        if (claimMap == null) return jwtBuilder;

        for (Map.Entry<String, String> entry: claimMap.entrySet()) {
            jwtBuilder.claim(entry.getKey(), entry.getValue());
        }

        return jwtBuilder;
    }

    public static boolean validateToken(String jwtToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("Invalid JWT token trace.", e.getMessage());
        }
        return false;
    }
}

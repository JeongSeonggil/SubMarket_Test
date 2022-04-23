package com.submarket.userservice.util;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Slf4j
public class TokenUtil {

    public static String getUserIdByToken(HttpHeaders headers, String secret) {
        log.debug("Token Util Start!");
        String token = headers.get("Authorization").get(0); // Get Token in headers
        String jwt = token.replace("Bearer", ""); // delete Bearer

        log.info("JWT : " + jwt); // TokenValue
        String userId = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody().getSubject();

        log.info("getUserId End");

        return userId;
    }
}

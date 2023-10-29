package org.jubadeveloper.adapters.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.jubadeveloper.core.domain.user.User;
import org.jubadeveloper.core.ports.auth.AuthenticatorPort;
import org.jubadeveloper.core.usecases.exceptions.AuthenticationException;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

public class AuthenticatorAdapter implements AuthenticatorPort {
    private final JWTVerifier verifier;
    private final Algorithm algorithm;
    public AuthenticatorAdapter(String privateKey) throws UnsupportedEncodingException {
        algorithm = Algorithm.HMAC256(privateKey);
        verifier = JWT.require(algorithm).withIssuer("auth0").build();
    }
    @Override
    public String auth(User user) {
        return JWT.create()
                .withIssuer("auth0")
                .withSubject(user.getEmail())
                .withClaim("userId", user.getId())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1200000L))
                .withJWTId(UUID.randomUUID().toString())
                .sign(algorithm);
    }

    @Override
    public Long checkAuth(String token) throws AuthenticationException {
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getClaim("userId").asLong();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new AuthenticationException(token);
        }
    }

    @Override
    public String updateToken(String token) throws AuthenticationException {
        DecodedJWT decodedJWT = verifier.verify(token);
        if (decodedJWT.getToken() != null) {
            return JWT.create()
                    .withIssuer(decodedJWT.getIssuer())
                    .withSubject(decodedJWT.getSubject())
                    .withClaim("userId", decodedJWT.getClaim("userId").asString())
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1200000L))
                    .withJWTId(UUID.randomUUID().toString())
                    .sign(algorithm);
        } else {
            throw new AuthenticationException(token);
        }
    }
    @Override
    public String getAuthRequest (String authHeader) {
        return authHeader.split(" ")[1];
    }
}

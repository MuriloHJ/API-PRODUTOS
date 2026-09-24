package com.br.apiprodutos.config.security;

import com.br.apiprodutos.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@Getter
@Setter
public class TokenService
{
    private final SecretKey key;
    private final String issuer;
    private final String audience;
    private final long accessToken;

    public TokenService(
            @Value("${api-settings.secret}") String secret,
            @Value("${api-settings.issuer}") String issuer,
            @Value("${api-settings.audience}") String audience,
            @Value("${api-settings.access-token-expiry-minutes}") long accessToken
    )
    {
        // Cria a chave uma única vez (equivalente ao SymmetricSecurityKey)
        this.key =  Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.issuer = issuer;
        this.audience = audience;
        this.accessToken = accessToken;
    }

    public String generatedToken(User user)
    {
        Instant now = Instant.now();

        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("name", user.getUsername())
                .claim("role", user.getRole())
                .issuer(issuer)
                .audience().add(audience).and()
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(accessToken, ChronoUnit.MINUTES)))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }
}

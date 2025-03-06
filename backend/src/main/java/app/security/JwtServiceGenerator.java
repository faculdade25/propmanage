package app.security;

import app.entity.Cliente;
import app.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JwtServiceGenerator {

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(User userDetails) {


        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username", userDetails.getEmail());
        extraClaims.put("name", userDetails.getFirstName());
        extraClaims.put("id", userDetails.getId().toString());
        extraClaims.put("Role", userDetails.getRole());


        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(new Date().getTime() + 3600000 * JwtConfig.expire))
                .signWith(getSigningKey(), JwtConfig.signatureAlgorithm)
                .compact();
    }

    public String generateTokenUser(Cliente userDetails) {


        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username", userDetails.getEmail());
        extraClaims.put("name", userDetails.getNome());
        extraClaims.put("id", userDetails.getId());

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(new Date().getTime() + 3600000 * JwtConfig.expire))
                .signWith(getSigningKey(), JwtConfig.signatureAlgorithm)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public boolean isTokenValid(String token, Optional<User> userDetails) {
        if (userDetails.isEmpty()) {
            return false; // Retorna falso se o usuário não for encontrado
        }
        final String username = extractUsername(token);
        return username.equals(userDetails.get().getEmail()) && !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Key getSigningKey() {

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

}
package app.security;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

public class JwtConfig {
    //Parâmetros para geração do token
    @Value("${jwt.secret}")
    private String secretKey;
    public static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    public static final int expire = 1;

}

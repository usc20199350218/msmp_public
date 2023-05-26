package com.yw.msmp.common.config;

import com.yw.msmp.dto.LoginDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;


/**
 * @author yanhaoyuwen
 */
@Data
@Component
@ConfigurationProperties( prefix = "config.jwt" )
public class JWTConfig {

    private String secret;
    private Integer expire;

    /**
     * 生成token
     * LoginDTO---> TOKEN 令牌
     */
    public String generateJwt( LoginDTO member ) {

        // 把秘钥转成byte[]
        byte[] keyBytes = secret.getBytes( );
        // 获得密钥对象
        SecretKey key = Keys.hmacShaKeyFor( keyBytes );

        String token = Jwts.builder( )
                           //令牌类型
                           .setHeaderParam( "typ", "JWT" )
                           // .setHeaderParam("alg", "HS256") //签名算法
                           .setIssuedAt( new Date( ) ) //签发时间
                           .setExpiration( new Date( System.currentTimeMillis( ) + expire * 1000 ) ) //过期时间
                           .claim( "userId", member.getUserId( ) )
                           .claim( "userName", member.getUserName( ) )
                           .claim( "userAvatarUrl", member.getUserAvatarUrl( ) )
                           .claim( "roleId", member.getRoleId( ) )
                           .signWith( key, SignatureAlgorithm.HS256 )
                           .compact( );
        return token;
    }

    /**
     * 解析jwt
     * 解析token
     * token令牌 还原成LoginDto 对象
     *
     * @param jwtToken token
     * @return LoginDTO
     */
    public LoginDTO checkJwt( String jwtToken ) {

        Jws< Claims > claimsJws = Jwts.parser( )
                                      .setSigningKey( this.secret.getBytes( ) )
                                      .parseClaimsJws( jwtToken );
        // map
        Claims claims = claimsJws.getBody( );
        //        Long userId = claims.get( "userId", Long.class );
        Integer userId = claims.get( "userId", Integer.class );
        String userName = claims.get( "userName", String.class );
        String userAvatarUrl = claims.get( "userAvatarUrl", String.class );
        Integer roleId = claims.get( "roleId", Integer.class );

        return LoginDTO.builder( )
                       .userId( userId )
                       .roleId( roleId )
                       .userAvatarUrl( userAvatarUrl )
                       .userName( userName )
                       .build( );
    }

}

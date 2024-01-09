//package com.example.HRM_practice.security.jwt;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.security.Key;
//import java.util.*;
//import java.util.function.Function;
//
//@Service
//@Slf4j
//public class JwtService {
//
//    //Token 過期時間 (設定15分鐘)
//    private Long EXPIRATION_TIME = (long) (60*60*1000); //單位ms
//
//    //BASE64編碼的密鑰
//    private String SECURITY_KEY = "u2yWwTh2a6g3nfTxUR2rXYzXTXk6FhfsCmfbdD6A2tPWPz9uAeR8KHgB3bnBqnBt\n";
//
//    //從JWT令牌中提取使用者名稱
//    public String extractUsername(String token){
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    //從令牌中提取過期時間
//    private Date extractExpiration(String token){
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    //提取令牌中的任何聲明(Claims)，並通過提供的Function來解析他們
//    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        //用來解析JWT令牌中的所有聲明
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//
//    public String generateToken(UserDetails userDetails){
//        return createToken(new HashMap<>(), userDetails);
//    }
//
//    //簽發Token
//    public String createToken(Map<String, Object> extractClaims, UserDetails userDetails){
//        return Jwts
//                .builder()
//                .setClaims(extractClaims)
//                .setSubject(userDetails.getUsername()) //以username做為Subject
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(getSignInKey(), SignatureAlgorithm.ES256)
//                .compact();
//    }
//
//    /**
//     * 驗證Token有效性，比對JWT和UserDetails的Username是否相同
//     * @return 有效為True，反之False
//     */
//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
//    }
//
//    //驗證token是否過期
//    private boolean isTokenExpired(String token){
//        final Date expirationDate = extractExpiration(token);
//        return expirationDate != null && expirationDate.before(new Date());
//    }
//
//
//
//    //獲取令牌中所有的聲明將其解析 return令牌中所有的聲明
//    private Claims extractAllClaims(String token) {
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    //獲取JWT簽名的密鑰
//    private Key getSignInKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECURITY_KEY);
//        // 使用Keys.hmacShaKeyFor 創建用於 HMAC 簽名的密鑰對象
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//}

//package com.example.HRM_practice.security.jwt;
//
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtService jwtService;
//    private final UserDetailsService userDetailsService;
//
//    @Override
//    protected void doFilterInternal(@NonNull HttpServletRequest request,
//                                    @NonNull HttpServletResponse response,
//                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        final String userName;
//        //以下條件為沒有攜帶Token的請求
//        //如果未攜帶JWT令牌或令牌不以 Bearer 開頭，則直接呼叫filterChain.doFilter，繼續處理下一個過濾器或請求處理程序
//        // Authorization: Bearer 是一種在HTTP request header 中用於傳遞 訪問令牌(Access Token) 的常見格式，用於在客戶端和服務器之間進行身分驗證和授權操作
//        //原理: 當客戶端發送HTTP請求時，可以在request header中添加"Authorization"字段來訪問Access Token
//        //Bearer是一種認證方案(Authentication scheme)的名稱，用於指後面的Token 是 Access Token EX: Authentication: Bearer your_access_token
//        if (authHeader == null || !authHeader.startsWith("Bearer")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        jwt = authHeader.substring(7); //取"Bearer "後面的Token
//        userName = jwtService.extractUsername(jwt);
//        //如果用戶名不為null且當前的Security上下文中不存在身份驗證
//        UserDetails userDetails = null;
//        if (SecurityContextHolder.getContext().getAuthentication() == null) {
//            userDetails = this.userDetailsService.loadUserByUsername(userName);
//        }
//
//        if (jwtService.isTokenValid(jwt, userDetails)) {
//            //如果JWT令牌有效，則創建一個usernamePasswordAuthenticationToken 並將其設置到Spring Security 的 Security 上下文忠，以確保用戶已成功驗證
//            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(authToken);
//        }else {
//            return;
//        }
//        filterChain.doFilter(request, response);
//
//    }
//}

package com.blog.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Date;


public class JwtTokenGeneratorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {

            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
            String jwt = Jwts.builder()
                    .setIssuer("BlogApplication")
                    .setSubject("JWT Token")
                    .claim("username", authentication.getName())
//                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))  we can uncomment this when we have roles in our user
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime()+ 30000000)) // millisecond expiration time of around 8 hours
                    .signWith(key, SignatureAlgorithm.HS256).compact();
            response.setHeader(SecurityConstants.JWT_HEADER, jwt);
        }
        filterChain.doFilter(request, response);
    }

//     This method is needed when we have roles in our user.
//    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
//        Set<String> authoritiesSet = new HashSet<>();
//
//        for (GrantedAuthority authority : collection) {
//            authoritiesSet.add(authority.getAuthority());
//        }
//        return String.join(",", authoritiesSet);
//    }




    //this make sure that this filter will execute only for first time when client call the api /signIn at first time
    // it will generate a jwt token for signin api
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    	
        return !request.getServletPath().equals("/api/signin");
    }


}

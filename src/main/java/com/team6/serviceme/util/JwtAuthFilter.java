package com.team6.serviceme.util;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends BasicAuthenticationFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public JwtAuthFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

//    JwtAuthFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
//
//        super(authenticationManager);
//        this.jwtUtils = jwtUtils;
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String token = request.getHeader("Authorization");

        // if header of Authorization contains nothing or not start with 'Bearer '
//        if (token == null || !token.startsWith(Constant.TOKEN_PREFIX)) {
//            chain.doFilter(request, response);
//            return;
//        }


        String userName  = jwtTokenUtil.getUsernameFromToken(token);
        System.out.println(userName);
        SecurityContextHolder.getContext().setAuthentication(
               new UsernamePasswordAuthenticationToken(userName, null, null));

//        DecodedJWT jwt = jwtUtils.decode(token.replace(Constant.TOKEN_PREFIX, ""));
//        if (jwt == null) {
//            chain.doFilter(request, response);
//            return;
//        }
//


//        Integer uid = jwt.getClaim(Constant.UID).asInt();
//        //String[] role = jwt.getClaim(Constant.ROLE).asArray(String.class);
//
//        SecurityContextHolder.getContext().setAuthentication(
//                new UsernamePasswordAuthenticationToken(uid, null, AuthorityUtils.createAuthorityList(role)));
//          加与不加
//        chain.doFilter(request, response);
    }
}

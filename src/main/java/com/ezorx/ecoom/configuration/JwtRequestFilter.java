package com.ezorx.ecoom.configuration;


import com.ezorx.ecoom.service.JwtService;
import com.ezorx.ecoom.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    public static String CURRENT_USER = "";

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");


        System.out.println(header);

        String jwtToken = null;
        String userName = null;
        if(header != null && header.startsWith("Bearer ")){
            jwtToken = header.substring(7);

            System.out.println(jwtToken);

            try{
                userName = jwtUtil.getUserNameFormToken(jwtToken);
                CURRENT_USER = userName;
                System.out.println(userName);

            }catch (IllegalArgumentException e){
                System.out.println("Unable to get JWT Token");
            }catch (ExpiredJwtException e){
                System.out.println("Jwt Token is Expired");
            }
        }else {
            System.out.println("JWT token Does not start with Bearer");
        }


        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails =  jwtService.loadUserByUsername(userName);


            System.out.println(userDetails);


            if(jwtUtil.validateToken(jwtToken , userDetails)){

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(userDetails , null , userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request , response);


    }
}

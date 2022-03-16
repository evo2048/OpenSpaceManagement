package com.assist.openspacemanagement.utils.jwt;

import com.assist.openspacemanagement.utils.userDetails.CustomUserDetailsService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private CustomUserDetailsService customUserDetailsService;
    private JwtUtilService jwtUtilService;

    @Autowired
    public JwtRequestFilter(CustomUserDetailsService customUserDetailsService, JwtUtilService jwtUtilService) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtilService = jwtUtilService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String username = null;
        String token = null;
        String path = request.getRequestURI();
        Map<String, String> mapQueryString = new TreeMap<>();

        if (request.getQueryString() != null) {
            String[] queryArray = request.getQueryString().split("&");
            for (String val : queryArray) {
                String[] values = val.split("=");
                mapQueryString.put(values[0], values[1]);
            }

            if (path.equals("/login")) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        token = mapQueryString.get("token");
        if(token != null) {
            username = jwtUtilService.extractUsername(token);
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
                if(jwtUtilService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}

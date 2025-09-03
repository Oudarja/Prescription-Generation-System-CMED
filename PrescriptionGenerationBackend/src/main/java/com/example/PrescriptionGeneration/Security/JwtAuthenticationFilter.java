package com.example.PrescriptionGeneration.Security;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

// Spring Security filter that runs once per request. Its job is to check if an 
// HTTP request contains a valid JWT token and, if so, set the authentication in
// the Spring Security context

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // Extends OncePerRequestFilter

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain) throws IOException, jakarta.servlet.ServletException {
   //Retrieves the Authorization header from the HTTP request. 
   // Authorization: Bearer <jwt_token>
    String authHeader = request.getHeader("Authorization");

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        
        // Checks if the header exists and starts with "Bearer ".
        // Removes "Bearer " prefix to get the raw token.
        String token = authHeader.substring(7);
        try {
            //JWT token used to retrieve the real email
            // If the token is invalid, it throws an exception.
            String email = jwtUtil.validateTokenAndGetEmail(token);

            // authentication only set  if:Email exists, and the security context
            //  doesn’t already have an authenticated user.
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) 
            {
                // Creates a Spring Security User object with the email and empty password and no 
                // authorities(no roles assigned here).
                User springUser = new User(email, "", Collections.emptyList());
                
                // Wraps that User into an authentication token object Spring Security understands.
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(springUser, null, springUser.getAuthorities());
                
                // Saves this authentication into Spring’s Security Context.
                // From now on, to access any secured resource, SecurityContextHolder 
                // can be used to use authenticated email.
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        } catch (Exception e) {
            
        }
    }

    filterChain.doFilter(request, response);
}

}

package ru.solonchev.backend;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.solonchev.backend.service.UserDetailsServiceImpl;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenFilter extends OncePerRequestFilter {
    private final JwtCore jwtCore;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = null;
        String username = null;
        UserDetails userDetails = null;
        UsernamePasswordAuthenticationToken auth = null;

        try {
            String headerAuth = request.getHeader("Authorization");
            if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
                jwt = headerAuth.substring(7);
            }
            if (jwt != null) {
                try {
                    username = jwtCore.getNameFromJwt(jwt);
                } catch (ExpiredJwtException e) {
                    log.error("Expired JWT token");
                }
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    userDetails = userDetailsService.loadUserByUsername(username);
                    auth = new UsernamePasswordAuthenticationToken(userDetails, null, null);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}

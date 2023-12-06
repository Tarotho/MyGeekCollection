package com.mygeekcollection.backend.security.jwt;

import com.mygeekcollection.backend.entity.User;
import com.mygeekcollection.backend.exceptionhandler.exceptions.UserNotFoundException;
import com.mygeekcollection.backend.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            final String token = getTokenFromRequest(request);

            if (StringUtils.isEmpty(token)) {
                filterChain.doFilter(request, response);
                return;
            }

            String id = jwtService.getIdFromToken(token);

            if (id != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Integer intId = Integer.valueOf(id);
                User user = userRepository.findById(intId)
                        .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con ID: " + intId));

                if (jwtService.isTokenValid(token, user)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);

        } catch (JwtException e) {
            handleJwtException(response, e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (NumberFormatException e) {
            handleGenericException(response, "Error al procesar el ID del usuario", HttpStatus.BAD_REQUEST);
        } catch (UserNotFoundException e) {
            handleGenericException(response, e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            handleGenericException(response, e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (AuthenticationException e) {
            handleGenericException(response, "Error de autenticación", HttpStatus.UNAUTHORIZED);
        } catch (AccessDeniedException e) {
            handleGenericException(response, "Acceso denegado", HttpStatus.FORBIDDEN);
        }
    }

    private void handleJwtException(HttpServletResponse response, String message, HttpStatus status) throws IOException {
        response.setStatus(status.value());
        response.getWriter().write(buildErrorResponse(status, message));
    }

    private void handleGenericException(HttpServletResponse response, String message, HttpStatus status) throws IOException {
        response.setStatus(status.value());
        response.getWriter().write(buildErrorResponse(status, message));
    }

    private String buildErrorResponse(HttpStatus status, String message) {
        return String.format("{\"error\": \"%s\", \"message\": \"%s\"}", status.getReasonPhrase(), message);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}



package com.singidunum.delivery.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HttpLoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String body = new String(request.getInputStream().readAllBytes());
        Enumeration<String> headerNames = request.getHeaderNames();
        log.info(
            "====================================================================================================================================");
        log.info("Request url {}", request.getRequestURI() + "?" + request.getQueryString());
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                final String name = headerNames.nextElement();
                log.info("Request header {}:{}", name, request.getHeader(name));
            }
        }

        log.info("Request body {}", body);

        log.info(
            "====================================================================================================================================");
        filterChain.doFilter(request, response);

    }
}

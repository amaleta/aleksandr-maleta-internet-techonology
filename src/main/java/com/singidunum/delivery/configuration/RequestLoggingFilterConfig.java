package com.singidunum.delivery.configuration;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingFilterConfig extends AbstractRequestLoggingFilter {

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter
            = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(100000);
        filter.setIncludeHeaders(true);
        filter.setAfterMessagePrefix("REQUEST DATA: ");
        return filter;
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        logger.info(message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        logger.info(message);
    }

    @Bean
    public CommonsRequestLoggingFilter springLogging() {
        Logger logger = (Logger) LoggerFactory.getLogger(CommonsRequestLoggingFilter.class);
        logger.setLevel(Level.DEBUG);
        return logFilter();
    }
}

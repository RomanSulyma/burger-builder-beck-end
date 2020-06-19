package com.net.burgerbuilder.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Component for handling unsuccessful authorizations
 */
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthEntryPointJwt.class);

  @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

    LOGGER.error("Unauthorized error: {}", authException.getMessage());
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    PrintWriter writer = response.getWriter();
    writer.println("HTTP Status 401 - " + authException.getMessage());
  }
}

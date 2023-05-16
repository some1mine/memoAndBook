package com.example.memoandbook.config.filter;

import com.example.memoandbook.config.JwtAuthenticationProvider;
import com.example.memoandbook.domain.common.UserVo;
import com.example.memoandbook.service.UserService;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@WebFilter(urlPatterns = "/user/**")
@RequiredArgsConstructor
public class UserFilter implements Filter {
  private final JwtAuthenticationProvider provider;
  private final UserService userService;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    String token = req.getHeader("X-AUTH-TOKEN");
    if (!provider.validateToken(token)) throw new ServletException("Invalid Access");

    UserVo userVo = provider.getUserVo(token);
    userService.findByIdAndEmail(userVo.getId(), userVo.getEmail())
        .orElseThrow(() -> new ServletException("Invalid Access"));
    chain.doFilter(request, response);
  }
}

package com.example.SpringMybatisXml.Config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.SpringMybatisXml.Models.Users.UserInfoModel;

@Component
public class FilterRequest extends OncePerRequestFilter {

	@Autowired
	private Helper jwtUtils;

//    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = getTokenFromRequest(request);
			System.out.println(token);
			if (token != null) {
				UserInfoModel tokenInfo = null;
				tokenInfo = jwtUtils.VerifyToken(token);
				if (tokenInfo != null) {
//					String username = tokenInfo.getUsername();
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							tokenInfo, null, null);
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			} else
				SecurityContextHolder.clearContext();
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			PrintWriter out = response.getWriter(); // custom exception
			String a = "{\"message\": " + e.getMessage() + ", \"status:\" " + HttpStatus.UNAUTHORIZED.value() + "}";
			out.print(a);
			out.flush();
		}
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
			return token.substring(7, token.length());
		}

		return null;
	}

}

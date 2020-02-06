package com.embl.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


public class JwtFilter extends GenericFilterBean {

	/*
	 * Overrided the doFilter method of GenericFilterBean.
	 * Retrieve the "authorization" header from the HttpServletRequest object.
	 * Retrieve the token from "authorization" header.
	 * If authorization header is invalid, throw Exception with message. 
	 * Parse the JWT token and get claims from the token using the secret key
	 * Set the request attribute with the retrieved claims
	 * Call FilterChain object's doFilter() method */

	public static final String AUTH_TOKEN_SECRET = "EMBL007";


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpServletResponse res = (HttpServletResponse) response;
		final String authHeader = req.getHeader("Authorization");
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With,observe");
		res.setHeader("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT,DELETE");
	

		if ("OPTIONS".equals(req.getMethod()) || req.getRequestURL().toString().contains("/auth")) {
			res.setStatus(HttpServletResponse.SC_OK);
			res.setHeader("Access-Control-Allow-Origin", "*");
			res.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(req, res);
		} else {
			if (authHeader == null) {
				res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				throw new ServletException("Missing or invalid Authorization header");
			}
			final String token = authHeader;
			final Claims claims = Jwts.parser()
					.setSigningKey(AUTH_TOKEN_SECRET)
					.parseClaimsJws(token)
					.getBody();
			request.setAttribute("claims", claims);
			chain.doFilter(req, res);
		}

	}
}

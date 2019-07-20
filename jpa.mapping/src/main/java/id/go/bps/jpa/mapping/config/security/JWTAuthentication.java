package id.go.bps.jpa.mapping.config.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.management.RuntimeErrorException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.User;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import id.go.bps.jpa.mapping.dto.login.LoginDTO;

public class JWTAuthentication extends UsernamePasswordAuthenticationFilter{
	
	@Autowired
	private AuthenticationManager authenticationManager;

	public JWTAuthentication(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		try {
			LoginDTO loginDTO = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							loginDTO.getUsername(),
							loginDTO.getPassword(),
							new ArrayList<>() // untuk role
					)
				);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String token = JWT.create()
				.withSubject(((User) authResult.getPrincipal()).getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_DATE))
				.sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
		
		// auth di header
//		response.addHeader(SecurityConstants.HEADERS_STRING, 
//				SecurityConstants.TOKEN_PREFIX + token);
		
		// auth di body
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
                "{\"" + SecurityConstants.HEADERS_STRING + "\":\"" + SecurityConstants.TOKEN_PREFIX+ token + "\"}"
        );
	}
	
	
}

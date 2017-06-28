package com.arvind.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import com.arvind.security.model.CustomUserDetail;






public class CashuAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private CustomUserDetailsService cstmUserDetailsService;
    
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	 HttpServletRequest httpRequest = (HttpServletRequest) request;
         String authToken = httpRequest.getHeader("Authorization");        
         String sourceApplication=null;
         if(authToken== null){
         	authToken=httpRequest.getParameter("token");
         	sourceApplication = httpRequest.getParameter("source");
         }
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
          if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	//if(authToken.equals(RedisUtility.getRedisValueBykey(username))){        	
            CustomUserDetail userDetails = (CustomUserDetail) cstmUserDetailsService.loadUserByUsername(username);
            System.out.println("userDetails email  is "+userDetails.getUserEmail());
            System.out.println("userDetails name  is "+userDetails.getUsername());
            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
            	userDetails.setToken(authToken);
            	//userDetails.setUserIpAddress(request.getRemoteAddr());
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        /*}
        	else{
            	throw new ConsumerLoginException(ConsumerLoginCode.LOGIN_EXPIRED,username);
            }*/
        }
        
        chain.doFilter(request, response);
    }
}

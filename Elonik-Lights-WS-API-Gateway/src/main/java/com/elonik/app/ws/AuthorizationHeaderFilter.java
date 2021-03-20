package com.elonik.app.ws;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.google.common.net.HttpHeaders;

import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

	public AuthorizationHeaderFilter() {
		super(Config.class);
	}

	public static class Config{

	}

	@Override
	public GatewayFilter apply(Config config) {
		return(exchange,chain) ->{

			ServerHttpRequest  request = exchange.getRequest();

			if(!request.getHeaders().containsKey("Authorization")) {
				return onError(exchange,"No authorization header",HttpStatus.UNAUTHORIZED);
			}
			String authorizationHeader=request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
			String token = authorizationHeader.replace("Bearer", "");

			if(!isJwtValid(token)) {
				return onError(exchange,"JWT token is not valid",HttpStatus.UNAUTHORIZED);
			}

			return chain.filter(exchange);
		};
	}

	private Mono<Void> onError(ServerWebExchange exchange,String err,HttpStatus status){
		ServerHttpResponse response = exchange.getResponse();

		return response.setComplete();

	}

	private boolean isJwtValid(String token) {
		boolean returnValue = true;
		String subject = null;
		try {
			subject = Jwts.parser().setSigningKey("elonik123456patel").parseClaimsJws(token)
					.getBody()
					.getSubject();
		}catch(Exception e) {
			returnValue = false;
		}

		if(subject == null || subject.isEmpty()){
			returnValue = false;
		}
		return returnValue;

	}

}

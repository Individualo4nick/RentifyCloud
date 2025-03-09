package org.rentifyCloud.gateway.filters;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class OidcUserTokenHeaderFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .flatMap(authentication -> {
                    String userSubject = null;
                    if (authentication.getPrincipal() instanceof OidcUser) {
                        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
                        userSubject = oidcUser.getSubject();
                    } else if (authentication.getPrincipal() instanceof Jwt) {
                        Jwt jwt = (Jwt) authentication.getPrincipal();
                        userSubject = jwt.getClaimAsString("sub");
                    }

                    ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                            .header("oidc-user-subject", userSubject)
                            .build();
                    ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
                    return chain.filter(mutatedExchange);

                })
                .switchIfEmpty(chain.filter(exchange));
    }
}
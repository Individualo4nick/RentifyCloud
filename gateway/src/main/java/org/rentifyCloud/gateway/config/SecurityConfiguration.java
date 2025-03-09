package org.rentifyCloud.gateway.config;

import org.rentifyCloud.gateway.filters.OidcUserTokenHeaderFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcReactiveOAuth2UserService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.ReactiveOAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                )
                .oauth2Login(Customizer.withDefaults())
                .build();
    }

    @Bean
    public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
        var converter = new ReactiveJwtAuthenticationConverter();
        var authoritiesConverter = new JwtGrantedAuthoritiesConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            var authorities = authoritiesConverter.convert(jwt);
            var roles = jwt.getClaimAsStringList("rentify_roles");

            return Flux.fromStream(Stream.concat(
                    authorities.stream(),
                    roles.stream()
                            .filter(role -> role.startsWith("ROLE_"))
                            .map(SimpleGrantedAuthority::new)
            ));

        });

        converter.setPrincipalClaimName("preferred_username");
        return converter;
    }

//    @Bean
//    public ReactiveOAuth2UserService<OidcUserRequest, OidcUser> oAuth2UserService() {
//        var delegate = new OidcReactiveOAuth2UserService();
//
//        return userRequest -> delegate.loadUser(userRequest)
//                .map(oidcUser -> {
//                    var roles = oidcUser.getClaimAsStringList("rentify_roles");
//                    var authorities = Stream.concat(
//                                    oidcUser.getAuthorities().stream(),
//                                    roles.stream()
//                                            .filter(role -> role.startsWith("ROLE_"))
//                                            .map(SimpleGrantedAuthority::new)
//                            )
//                            .collect(Collectors.toList());
//
//                    return new DefaultOidcUser(authorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
//                });
//    }
}

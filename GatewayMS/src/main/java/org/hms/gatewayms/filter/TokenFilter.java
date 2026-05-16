package org.hms.gatewayms.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class TokenFilter extends AbstractGatewayFilterFactory<TokenFilter.Config> {
    private static final String SECRET = "CbjGa8J+KdOtFfR3DJsc8zaIf1Xeea6o9rbQblPiOaFtsOIH9mlbvCVTFmdEHyV+qR+tQ+B8/tB6dS0tfQsdaQ==";

    public TokenFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getPath().value();

            if (RoleAuthorization.isPublicPath(path)) {
                return chain.filter(exchange.mutate()
                        .request(r -> r.header("X-Secret-Key", "SECRET"))
                        .build());
            }

            HttpHeaders headers = exchange.getRequest().getHeaders();
            if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                return forbidden(exchange, "Authorization header is missing");
            }

            String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return forbidden(exchange, "Authorization header is invalid");
            }

            String token = authHeader.substring(7);
            Claims claims;
            try {
                claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            } catch (Exception e) {
                return forbidden(exchange, "Token is invalid");
            }

            String role = claims.get("role", String.class);
            if (role == null) {
                role = String.valueOf(claims.get("role"));
            }

            if (RoleAuthorization.isAdminOnlyPath(path)
                    && !RoleAuthorization.ROLE_ADMIN.equals(role)) {
                return forbidden(exchange, "Admin access required");
            }

            Object userId = claims.get("id");
            String finalRole = role;
            ServerWebExchange mutated = exchange.mutate()
                    .request(r -> {
                        r.header("X-Secret-Key", "SECRET");
                        if (userId != null) {
                            r.header(RoleAuthorization.HEADER_USER_ID, String.valueOf(userId));
                        }
                        if (finalRole != null) {
                            r.header(RoleAuthorization.HEADER_USER_ROLE, finalRole);
                        }
                    })
                    .build();

            return chain.filter(mutated);
        };
    }

    private Mono<Void> forbidden(ServerWebExchange exchange, String message) {
        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
        exchange.getResponse().getHeaders().add("X-Error-Message", message);
        return exchange.getResponse().setComplete();
    }

    public static class Config {
    }
}

package org.hms.userms.services;

import org.hms.userms.dto.Roles;
import org.hms.userms.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiService {

    private final WebClient.Builder webClient;;
    public ApiService(WebClient.Builder webClient) {
        this.webClient = webClient;
    }

    public Mono<Long> addProfile(UserDTO userDTO) {
        if (userDTO.getRole().equals(Roles.DOCTOR)) {
            return webClient.build().post().uri("http://localhost:9100/profile/doctor/add").bodyValue(userDTO)
                    .retrieve().bodyToMono(Long.class);
        } else if (userDTO.getRole().equals(Roles.PATIENT)) {
            return webClient.build().post().uri("http://localhost:9100/profile/patient/add").bodyValue(userDTO)
                    .retrieve().bodyToMono(Long.class);
        }
        return null;
    }
}

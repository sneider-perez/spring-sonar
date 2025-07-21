package cl.test.authorizer.controller;

import cl.test.authorizer.dto.request.clientinfo.ClientInfoRequestDto;
import cl.test.authorizer.dto.response.clientinfo.ClientInfoResponseDto;
import cl.test.authorizer.service.ClientInfoService;
import cl.test.authorizer.service.JwtValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/cl/internal/auth/v1")
@RequiredArgsConstructor
public class TokenController {

    private final JwtValidatorService jwtValidatorService;
    private final ClientInfoService clientInfoService;

    @PostMapping("/token/info")
    public ResponseEntity<ClientInfoResponseDto> validateToken(
            @RequestHeader("Authorization") String authHeader
    ) {
        String accessToken = extractJwt(authHeader);

        Optional<String> username = jwtValidatorService.getUsername(accessToken);
        ClientInfoRequestDto infoRequest = new ClientInfoRequestDto(username.get());
        ClientInfoResponseDto infoResponse = clientInfoService.retrieveClientInfoFromUser(infoRequest);
        return ResponseEntity.ok(infoResponse);

    }

    private String extractJwt(String authHeader) {
        return authHeader.replace("Bearer ", "");
    }

}

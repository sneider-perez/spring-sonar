package cl.test.authorizer.controller;

import cl.test.authorizer.dto.request.login.LoginRequestDto;
import cl.test.authorizer.dto.response.login.LoginResponseDto;
import cl.test.authorizer.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cl/internal/auth/v1")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginWebPage(
            @RequestBody LoginRequestDto request
    ) {
        LoginResponseDto response = loginService.loginWebPage(request);
        return ResponseEntity.ok(response);
    }

}

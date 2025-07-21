package cl.test.authorizer.controller;

import cl.test.authorizer.dto.request.serviceaccesscheck.ServiceAccessCheckRequestDto;
import cl.test.authorizer.dto.response.serviceaccesscheck.ServiceAccessCheckResponseDto;
import cl.test.authorizer.service.ServiceAccessCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cl/internal/auth/v1")
@RequiredArgsConstructor
public class ServiceAccessCheckController {

    private final ServiceAccessCheckService serviceAccessCheckService;

    @PostMapping("/permisos/verificar")
    public ResponseEntity<ServiceAccessCheckResponseDto> verifyAccess(
            @RequestBody ServiceAccessCheckRequestDto request,
            @RequestHeader("x-channel") String channel
    ) {
        ServiceAccessCheckResponseDto response = serviceAccessCheckService.verifyAccess(request, channel);
        return ResponseEntity.ok(response);
    }

}

package cl.test.authorizer.service;


import cl.test.authorizer.dto.request.serviceaccesscheck.ServiceAccessCheckRequestDto;
import cl.test.authorizer.dto.response.serviceaccesscheck.ServiceAccessCheckResponseDto;

public interface ServiceAccessCheckService {

    ServiceAccessCheckResponseDto verifyAccess(ServiceAccessCheckRequestDto request, String channel);

}

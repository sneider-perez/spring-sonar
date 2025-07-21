package cl.test.authorizer.mapper;

import cl.test.authorizer.dto.response.serviceaccesscheck.ServiceAccessCheckResponseDto;

public class ServiceAccessCheckMapper {

    private ServiceAccessCheckMapper() {
    }

    public static ServiceAccessCheckResponseDto buildSuccessResponse() {
        return ServiceAccessCheckResponseDto.builder()
                .returnCode("DEFAULT_SUCCESS_CODE")
                .returnDescription("DEFAULT_SUCCESS_DESCRIPTION")
                .build();
    }


}

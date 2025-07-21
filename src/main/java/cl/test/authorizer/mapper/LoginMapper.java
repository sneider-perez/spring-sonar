package cl.test.authorizer.mapper;

import cl.test.authorizer.dto.response.login.LoginResponseDto;
import cl.test.authorizer.persistence.storeprocedure.LoginSPDto;

public class LoginMapper {

    private LoginMapper() {
    }

    public static LoginResponseDto buildResponse(LoginSPDto loginSPDto) {
        return LoginResponseDto.builder()
                .retcode(String.valueOf(loginSPDto.getReturnCode()))
                .retdesc("DEFAULT_SUCCESS_DESCRIPTION")
                .client(loginSPDto.getClient())
                .sessionId(loginSPDto.getSessionId())
                .userProfileMacro(loginSPDto.getUserProfileMacro())
                .userProfile(loginSPDto.getUserProfile())
                .institutionName(loginSPDto.getInstitutionName())
                .onlyLegalEntities(loginSPDto.getOnlyLegalEntities())
                .institutionFilterLegalEntities(loginSPDto.getInstitutionFilterLegalEntities())
                .showPasswordManagementQuestions(loginSPDto.getShowPasswordManagementQuestions())
                .timeout(loginSPDto.getTimeout())
                .institutionRut(loginSPDto.getInstitutionRut())
                .showMFA(loginSPDto.getShowMFA())
                .build();
    }

}

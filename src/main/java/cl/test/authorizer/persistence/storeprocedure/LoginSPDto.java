package cl.test.authorizer.persistence.storeprocedure;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginSPDto {

    private Integer returnCode;
    private String client;
    private String sessionId;
    private String userProfileMacro;
    private String userProfile;
    private String institutionName;
    private Character onlyLegalEntities;
    private Character institutionFilterLegalEntities;
    private Character showPasswordManagementQuestions;
    private Integer timeout;
    private String institutionRut;
    private Character showMFA;

    public static LoginSPDto buildFromSP() {
        return null;
    }

}

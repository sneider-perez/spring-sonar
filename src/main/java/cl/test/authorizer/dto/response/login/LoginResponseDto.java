package cl.test.authorizer.dto.response.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseDto {

    @JsonProperty("retcode")
    private String retcode;

    @JsonProperty("retdesc")
    private String retdesc;

    @JsonProperty("idtrx")
    private String idtrx;

    @JsonProperty("idCliente")
    private String client;

    @JsonProperty("idSesion")
    private String sessionId;

    @JsonProperty("macroPerfilUsuario")
    private String userProfileMacro;

    @JsonProperty("perfilUsuario")
    private String userProfile;

    @JsonProperty("nombreInstitucion")
    private String institutionName;

    @JsonProperty("flagPersonasJuridicas")
    private Character onlyLegalEntities;

    @JsonProperty("flagFiltroPersonasJuridicas")
    private Character institutionFilterLegalEntities;

    @JsonProperty("flagAutogestionClaves")
    private Character showPasswordManagementQuestions;

    @JsonProperty("timeout")
    private Integer timeout;

    @JsonProperty("rutInstitucion")
    private String institutionRut;

    @JsonProperty("flagMFA")
    private Character showMFA;

    public String fetchReturnCode() {
        return retcode;
    }

    public String fetchReturnMessage() {
        return retdesc;
    }

}
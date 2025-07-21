package cl.test.authorizer.dto.response.clientinfo;

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
public class ClientInfoResponseDto {

    @JsonProperty("retcode")
    private String returnCode;

    @JsonProperty("retdesc")
    private String returnDescription;

    @JsonProperty("idtrx")
    private String transactionId;

    @JsonProperty("idCliente")
    private String client;

    @JsonProperty("idSesion")
    private String sessionId;

    @JsonProperty("usuario")
    private String user;

    @JsonProperty("institucion")
    private String intitutionCode;

    public String fetchReturnCode() {
        return returnCode;
    }

    public String fetchReturnMessage() {
        return returnDescription;
    }

}

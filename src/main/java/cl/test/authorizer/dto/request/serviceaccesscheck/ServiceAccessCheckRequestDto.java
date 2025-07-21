package cl.test.authorizer.dto.request.serviceaccesscheck;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ServiceAccessCheckRequestDto {

    @JsonProperty("idCliente")
    private String clientId;

    @JsonProperty("idSesion")
    private String sessionId;

    @JsonProperty("codigoServicio")
    private String serviceCode;

    public String fetchRutToTrace() {
        return "";
    }

}

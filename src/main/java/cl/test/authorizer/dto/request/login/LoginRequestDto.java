package cl.test.authorizer.dto.request.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @JsonProperty("usuario")
    private String username;

    @JsonProperty("clave")
    private String password;

    private String ip;

    public String fetchRutToTrace() {
        return "";
    }

}

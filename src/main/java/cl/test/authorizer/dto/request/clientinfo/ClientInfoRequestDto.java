package cl.test.authorizer.dto.request.clientinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientInfoRequestDto {

    @JsonProperty("usuario")
    private String username;

    public String fetchRutToTrace() {
        return "";
    }

}

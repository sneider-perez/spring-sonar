package cl.test.authorizer.dto.response.serviceaccesscheck;


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
public class ServiceAccessCheckResponseDto {

    @JsonProperty("retcode")
    private String returnCode;

    @JsonProperty("retdesc")
    private String returnDescription;

    @JsonProperty("idtrx")
    private String transactionId;

    public String fetchReturnCode() {
        return returnCode;
    }

    public String fetchReturnMessage() {
        return returnDescription;
    }

}

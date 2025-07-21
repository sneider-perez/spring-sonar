package cl.test.authorizer.persistence.storeprocedure;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ServiceAccessCheckSPDto {

    private Integer returnCode;

    public static ServiceAccessCheckSPDto buildFromSP() {
        return null;
    }

}

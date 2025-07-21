package cl.test.authorizer.persistence.storeprocedure;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClientInfoSPDto {

    private String clientId;
    private String sessionId;

    public static ClientInfoSPDto buildFromSP(Object[] result) {
        return null;
    }

}

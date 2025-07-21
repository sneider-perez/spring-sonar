package cl.test.authorizer.mapper;

import cl.test.authorizer.dto.response.clientinfo.ClientInfoResponseDto;
import cl.test.authorizer.persistence.storeprocedure.ClientInfoSPDto;

import java.util.Objects;

public class ClientInfoMapper {

    private ClientInfoMapper() {
    }

    public static ClientInfoResponseDto buildResponse(ClientInfoSPDto clientInfoSPDto) {
        return ClientInfoResponseDto.builder()
                .returnCode("DEFAULT_SUCCESS_CODE")
                .returnDescription("DEFAULT_SUCCESS_DESCRIPTION")
                .client(clientInfoSPDto.getClientId())
                .sessionId(clientInfoSPDto.getSessionId())
                .user(getUsername(clientInfoSPDto.getSessionId()))
                .intitutionCode(getInstitutionCode(clientInfoSPDto.getClientId()))
                .build();
    }

    public static ClientInfoResponseDto buildResponseFromCache(String sessionId) {
        return ClientInfoResponseDto.builder()
                .returnCode("DEFAULT_SUCCESS_CODE")
                .returnDescription("DEFAULT_SUCCESS_DESCRIPTION")
                .client(getClientId(sessionId))
                .sessionId(sessionId)
                .build();
    }

    private static String getClientId(String sessionId) {
        String clientId = "";
        if (Objects.nonNull(sessionId) && sessionId.length() >= 16) {
            clientId = sessionId.substring(0, 16);
        }
        return clientId;
    }

    private static String getUsername(String sessionId) {
        String username = "";
        if (Objects.nonNull(sessionId) && sessionId.length() >= 24) {
            username = sessionId.substring(16, 24).trim();
        }
        return username;
    }

    private static String getInstitutionCode(String client) {
        String intitutionCode = "";
        if (Objects.nonNull(client) && client.length() >= 4) {
            intitutionCode = client.substring(0, 4);
        }
        return intitutionCode;
    }


}

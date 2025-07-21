package cl.test.authorizer.service;


import cl.test.authorizer.dto.request.clientinfo.ClientInfoRequestDto;
import cl.test.authorizer.dto.response.clientinfo.ClientInfoResponseDto;

public interface ClientInfoService {

    ClientInfoResponseDto retrieveClientInfoFromUser(ClientInfoRequestDto request);

}

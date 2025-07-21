package cl.test.authorizer.service;


import cl.test.authorizer.dto.request.login.LoginRequestDto;
import cl.test.authorizer.dto.response.login.LoginResponseDto;

public interface LoginService {

    LoginResponseDto loginWebPage(LoginRequestDto request);

}

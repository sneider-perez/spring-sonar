package cl.test.authorizer.service.impl;

import cl.test.authorizer.dto.request.login.LoginRequestDto;
import cl.test.authorizer.dto.response.login.LoginResponseDto;
import cl.test.authorizer.mapper.LoginMapper;
import cl.test.authorizer.persistence.storeprocedure.LoginSPDto;
import cl.test.authorizer.repository.LoginRepository;
import cl.test.authorizer.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    @Override
    public LoginResponseDto loginWebPage(LoginRequestDto request) {
        LoginSPDto loginSPDto = loginRepository.loginWebPage(request);

        if (Objects.equals(0, loginSPDto.getReturnCode())) {
            return LoginMapper.buildResponse(loginSPDto);
        }

        throw buildErrorEntity(loginSPDto.getReturnCode());
    }

    private RuntimeException buildErrorEntity(Integer returnCode) {
        return new RuntimeException(returnCode + "");
    }

}

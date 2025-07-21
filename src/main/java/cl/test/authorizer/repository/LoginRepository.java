package cl.test.authorizer.repository;

import cl.test.authorizer.dto.request.login.LoginRequestDto;
import cl.test.authorizer.persistence.storeprocedure.LoginSPDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoginRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public LoginSPDto loginWebPage(LoginRequestDto request) {
        return null;
    }

}

package cl.test.authorizer.repository;

import cl.test.authorizer.dto.request.clientinfo.ClientInfoRequestDto;
import cl.test.authorizer.persistence.storeprocedure.ClientInfoSPDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClientInfoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ClientInfoSPDto retrieveClientInfoFromUser(ClientInfoRequestDto request) {
        return null;
    }

}

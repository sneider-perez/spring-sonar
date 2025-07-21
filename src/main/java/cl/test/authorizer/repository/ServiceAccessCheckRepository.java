package cl.test.authorizer.repository;

import cl.test.authorizer.persistence.storeprocedure.ServiceAccessCheckSPDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ServiceAccessCheckRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ServiceAccessCheckSPDto verifyAccess(String clientId, String sessionId, String serviceCode) {
        return null;
    }

}

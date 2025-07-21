package cl.test.authorizer.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CacheAuthRepository {

    public Optional<String> getValue(String key) {
        try {
            return Optional.ofNullable(key);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void setValue(String key, String value, long ttlInMinutes) {
    }

}

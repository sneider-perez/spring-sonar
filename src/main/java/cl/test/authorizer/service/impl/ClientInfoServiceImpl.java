package cl.test.authorizer.service.impl;

import cl.test.authorizer.dto.request.clientinfo.ClientInfoRequestDto;
import cl.test.authorizer.dto.response.clientinfo.ClientInfoResponseDto;
import cl.test.authorizer.mapper.ClientInfoMapper;
import cl.test.authorizer.persistence.storeprocedure.ClientInfoSPDto;
import cl.test.authorizer.repository.CacheAuthRepository;
import cl.test.authorizer.repository.ClientInfoRepository;
import cl.test.authorizer.service.ClientInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientInfoServiceImpl implements ClientInfoService {

    private static final String CACHE_KEY = "session::user::%s";
    private static final long TTL_MINUTES = 1440;

    private final CacheAuthRepository cacheAuthRepository;
    private final ClientInfoRepository clientInfoRepository;

    @Override
    public ClientInfoResponseDto retrieveClientInfoFromUser(ClientInfoRequestDto request) {
        String key = CACHE_KEY.formatted(request.getUsername());
        Optional<String> cacheSessionIdOpt = cacheAuthRepository.getValue(key);

        if (cacheSessionIdOpt.isPresent()) {
            Optional<String> cacheSessionId = decrypt(cacheSessionIdOpt.get());
            if (cacheSessionId.isPresent()) {
                return ClientInfoMapper.buildResponseFromCache(cacheSessionId.get());
            }
        }

        ClientInfoSPDto clientInfoSPDto = clientInfoRepository.retrieveClientInfoFromUser(request);
        validateSpInfo(clientInfoSPDto);

        Optional<String> encryptSessionId = encrypt(clientInfoSPDto.getSessionId());
        encryptSessionId.ifPresent(s -> cacheAuthRepository.setValue(key, s, TTL_MINUTES));
        return ClientInfoMapper.buildResponse(clientInfoSPDto);
    }

    private void validateSpInfo(ClientInfoSPDto clientInfoSPDto) {
        if (Objects.isNull(clientInfoSPDto) || Objects.isNull(clientInfoSPDto.getClientId())) {
            throw new RuntimeException("801");
        }
    }

    private Optional<String> decrypt(String clientIdEncrypted) {
        try {
            return Optional.of(clientIdEncrypted);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Optional<String> encrypt(String clientId) {
        try {
            return Optional.of(clientId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}

package cl.test.authorizer.service.impl;

import cl.test.authorizer.dto.request.serviceaccesscheck.ServiceAccessCheckRequestDto;
import cl.test.authorizer.dto.response.serviceaccesscheck.ServiceAccessCheckResponseDto;
import cl.test.authorizer.mapper.ServiceAccessCheckMapper;
import cl.test.authorizer.persistence.storeprocedure.ServiceAccessCheckSPDto;
import cl.test.authorizer.repository.CacheAuthRepository;
import cl.test.authorizer.repository.ServiceAccessCheckRepository;
import cl.test.authorizer.service.ServiceAccessCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceAccessCheckServiceImpl implements ServiceAccessCheckService {

    private static final String CACHE_KEY = "access::clientId::%s::service::%s";
    private static final long TTL_MINUTES = 60;

    private final CacheAuthRepository cacheAuthRepository;
    private final ServiceAccessCheckRepository serviceAccessCheckRepository;

    @Override
    public ServiceAccessCheckResponseDto verifyAccess(ServiceAccessCheckRequestDto request, String channel) {
        String cacheKey = buildCacheKey(request);
        boolean isCacheable = isCacheableChannel(channel);

        if (hasAccessInCache(isCacheable, cacheKey)) {
            return ServiceAccessCheckMapper.buildSuccessResponse();
        }

        ServiceAccessCheckSPDto result = getAccessResult(request);
        validateAccess(result);

        cacheAccessIfNeeded(isCacheable, cacheKey);

        return ServiceAccessCheckMapper.buildSuccessResponse();
    }

    private String buildCacheKey(ServiceAccessCheckRequestDto request) {
        return CACHE_KEY.formatted(request.getClientId(), request.getServiceCode());
    }

    private boolean isCacheableChannel(String channel) {
        return "C".equals(channel);
    }

    private boolean hasAccessInCache(boolean isCacheable, String cacheKey) {
        return isCacheable && cacheAuthRepository.getValue(cacheKey).isPresent();
    }

    private ServiceAccessCheckSPDto getAccessResult(ServiceAccessCheckRequestDto request) {
        return serviceAccessCheckRepository.verifyAccess(
                request.getClientId(),
                request.getSessionId(),
                request.getServiceCode()
        );
    }

    private void validateAccess(ServiceAccessCheckSPDto result) {
        if (!Integer.valueOf(0).equals(result.getReturnCode())) {
            throw buildErrorEntity(result.getReturnCode());
        }
    }

    private void cacheAccessIfNeeded(boolean isCacheable, String cacheKey) {
        if (isCacheable) {
            cacheAuthRepository.setValue(cacheKey, "1", TTL_MINUTES);
        }
    }

    private RuntimeException buildErrorEntity(Integer returnCode) {
        return new RuntimeException(returnCode + "");
    }


}

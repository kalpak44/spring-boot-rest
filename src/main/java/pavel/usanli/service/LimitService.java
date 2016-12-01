package pavel.usanli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pavel.usanli.service.exceptions.ConfigurationFailedException;
import pavel.usanli.service.interfaces.LimitServiceInterface;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by Pavel Usanli on 27.11.2016.
 */
@Service
public class LimitService implements LimitServiceInterface {
    @Value("${limit.timeRequests}")
    private int timeRequestsConfig;

    @Value("${limit.countRequests}")
    private int countRequestsConfig;

    @Autowired
    private HttpServletRequest context;

    private HashMap<String, RequestCache> cache = new HashMap();

    @Override
    public boolean hasRequestLocked() {
        final String locale = getLocale();
        final RequestCache requestCache = cache.get(locale);
        final long currentTimeMillis = System.currentTimeMillis();

        /*
        if (requestCache != null) {
            final int localeRequestCounter = requestCache.getCounter() + 1;
            if (!hasLockedByTimeLimit(requestCache) && !hasLockedByCountsLimit(requestCache)) {
                cache.put(locale, new RequestCache(requestCache.getCreatedAt(), localeRequestCounter));
                return true;
            } else if (!hasLockedByTimeLimit(requestCache) && hasLockedByCountsLimit(requestCache)) {
                cache.put(locale, new RequestCache(requestCache.getCreatedAt(), localeRequestCounter));
                return false;
            } else if (hasLockedByTimeLimit(requestCache) && !hasLockedByCountsLimit(requestCache)) {
                cache.put(locale, new RequestCache(currentTimeMillis, 1));
                return true;
            } else {
                cache.put(locale, new RequestCache(currentTimeMillis, 1));
                return true;
            }
        } else {
            cache.put(locale, new RequestCache(currentTimeMillis, 1));
            return true;
        }

*/
        if (requestCache != null) {
            final int localeRequestCounter = requestCache.getCounter() + 1;
            if (hasLockedByTimeLimit(requestCache)) {
                cache.put(locale, new RequestCache(currentTimeMillis, 1));
            } else {
                cache.put(locale, new RequestCache(requestCache.getCreatedAt(), localeRequestCounter));
            }
            return !hasLockedByCountsLimit(requestCache);
        } else {
            cache.put(locale, new RequestCache(currentTimeMillis, 1));
            return true;
        }

    }

    private boolean hasLockedByTimeLimit(final RequestCache requestCache) {
        validateTimeRequestsParameter();
        return (System.currentTimeMillis() - requestCache.getCreatedAt()) > timeRequestsConfig * 1000;
    }

    private boolean hasLockedByCountsLimit(final RequestCache requestCache) {
        validateCountRequestsParameter();
        return requestCache.getCounter() > countRequestsConfig;
    }

    private String getLocale() {
        return context.getLocale() != null ? context.getLocale().toString() : "lv";
    }

    private void validateTimeRequestsParameter() {
        if (timeRequestsConfig < 1) {
            throw new ConfigurationFailedException("${limit.timeRequests} in application.properties file must be a number GREATER than 1");
        }
    }

    private void validateCountRequestsParameter() {
        if (countRequestsConfig < 1) {
            throw new ConfigurationFailedException("${limit.countRequests} in application.properties file must be a number GREATER than 1");
        }
    }

    private class RequestCache {
        private long createdAt;
        private int counter;

        RequestCache(long createdAt, int counter) {
            this.createdAt = createdAt;
            this.counter = counter;
        }

        long getCreatedAt() {
            return createdAt;
        }

        int getCounter() {
            return counter;
        }
    }
}

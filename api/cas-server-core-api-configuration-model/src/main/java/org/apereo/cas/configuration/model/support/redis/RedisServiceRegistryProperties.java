package org.apereo.cas.configuration.model.support.redis;

import org.apereo.cas.configuration.support.RequiresModule;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serial;

/**
 * Configuration properties for Redis.
 *
 * @author serv
 * @since 5.2.0
 */
@RequiresModule(name = "cas-server-support-redis-service-registry")
@Getter
@Setter
@Accessors(chain = true)
public class RedisServiceRegistryProperties extends BaseRedisProperties {

    @Serial
    private static final long serialVersionUID = -9012996050439638782L;
}

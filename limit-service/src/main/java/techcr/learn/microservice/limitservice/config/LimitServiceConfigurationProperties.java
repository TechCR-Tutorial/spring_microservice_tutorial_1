package techcr.learn.microservice.limitservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("limits-service-property")
@Data
public class LimitServiceConfigurationProperties {
    private int minimum;
    private int maximum;
}

package techcr.learn.microservice.limitservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import techcr.learn.microservice.limitservice.config.LimitServiceConfigurationGitProperties;
import techcr.learn.microservice.limitservice.config.LimitServiceConfigurationProperties;
import techcr.learn.microservice.limitservice.model.LimitConfiguration;

@RestController
@RequestMapping("/limits")
public class LimitsConfigurationController {

    @Autowired
    private LimitServiceConfigurationProperties configurationProperties;

    @Autowired
    private LimitServiceConfigurationGitProperties configurationGitProperties;

    @GetMapping
    public LimitConfiguration getRetrieveFromGit() {
        return new LimitConfiguration(configurationGitProperties.getMinimum(), configurationGitProperties.getMaximum());
    }

    @GetMapping(path = "/props")
    public LimitConfiguration getRetrieveFromProperty() {
        return new LimitConfiguration(configurationProperties.getMinimum(), configurationProperties.getMaximum());
    }
}

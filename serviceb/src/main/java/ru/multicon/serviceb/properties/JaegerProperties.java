package ru.multicon.serviceb.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="jaeger")
@Slf4j
@Getter
@Setter
public class JaegerProperties {
    private Service service;
    private Sampler sampler;
    private Agent agent;

    @Setter
    @Getter
    public static class Service {
        private String name;
    }

    @Setter
    @Getter
    public static class Sampler {
        private String type;
        private Integer param;
        private Manager manager;
    }

    @Setter
    @Getter
    public static class Manager {
        private Host host ;
    }

    @Setter
    @Getter
    public static class Host {
        private String port;
    }

    @Setter
    @Getter
    public static class Agent {
        private String host;
        private Integer port;
    }
}

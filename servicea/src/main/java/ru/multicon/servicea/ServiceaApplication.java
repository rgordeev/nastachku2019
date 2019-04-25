package ru.multicon.servicea;

import io.jaegertracing.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import ru.multicon.servicea.properties.JaegerProperties;

import java.time.Duration;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(JaegerProperties.class)
public class ServiceaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceaApplication.class, args);
    }

    @Autowired
    private JaegerProperties jaegerProperties;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(2))
                .setReadTimeout(Duration.ofSeconds(2))
                .build();
    }

    @Bean
    public io.opentracing.Tracer jaegerTracer() {
        return new Configuration(jaegerProperties.getService().getName())
                .withSampler(new Configuration.SamplerConfiguration()
                        .withType(jaegerProperties.getSampler().getType())
                        .withParam(jaegerProperties.getSampler().getParam())
                        .withManagerHostPort(jaegerProperties.getSampler().getManager().getHost().getPort()))
                .withReporter(new Configuration.ReporterConfiguration()
                        .withSender(new Configuration.SenderConfiguration()
                                .withAgentHost(jaegerProperties.getAgent().getHost())
                                .withAgentPort(jaegerProperties.getAgent().getPort())
                        )
                ).getTracer();
    }
}

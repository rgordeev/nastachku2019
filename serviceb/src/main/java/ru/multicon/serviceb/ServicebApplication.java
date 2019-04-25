package ru.multicon.serviceb;

import io.jaegertracing.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import ru.multicon.serviceb.properties.JaegerProperties;

@SpringBootApplication
@EnableConfigurationProperties(JaegerProperties.class)
public class ServicebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicebApplication.class, args);
    }


    @Autowired
    private JaegerProperties jaegerProperties;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
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

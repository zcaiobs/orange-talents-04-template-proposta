package br.com.zupacademy.caio.proposta.util;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class HealthCheck implements HealthIndicator {
    @Override
    public Health health() {
        Map<String, Object> details = new HashMap<>();
        details.put("versão", "0.0.1-SNAPSHOT");
        details.put("descrição", "Api Proposta");
        details.put("endereço", "http://localhost:8080");

        return Health.status(Status.UP).withDetails(details).build();
    }
}

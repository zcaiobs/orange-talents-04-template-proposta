package br.com.zupacademy.caio.proposta.nova_proposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cartoes", url = "http://localhost:9999/api")
public interface CartoesClient {

    @GetMapping("/solicitacao")
    FeignResponse cartoes(FeignRequest feignRequest);
}

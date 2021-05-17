package br.com.zupacademy.caio.proposta.nova_proposta.cartao.aviso;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "enviarAviso", url = "http://localhost:8888/api/cartoes")
public interface EnviarAvisoViagem {

    @PostMapping("/{numeroCard}/avisos")
    ResponseEntity<AvisoResponse> enviar(@PathVariable String numeroCard, @RequestBody ViagemRequest viagemRequest);
}

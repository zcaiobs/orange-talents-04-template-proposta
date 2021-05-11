package br.com.zupacademy.caio.proposta.nova_proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "cartoes", url = "http://${CARTOES:localhost}:8888/api")
public interface ConsultarCartao {

    @GetMapping("/cartoes?idProposta={id}")
    ResponseEntity<CartaoRequest> consultar(@PathVariable Long id);
}

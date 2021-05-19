package br.com.zupacademy.caio.proposta.nova_proposta.cartao.carteira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "carteiras", url = "http://localhost:8888/api/cartoes")
public interface AssociarCarteira {

    @PostMapping("/{numeroCard}/carteiras")
    ResponseEntity<CarteiraResponse> solicitar(@PathVariable String numeroCard, @RequestBody CarteiraRequest carteiraRequest);
}

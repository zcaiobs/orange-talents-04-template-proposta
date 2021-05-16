package br.com.zupacademy.caio.proposta.nova_proposta.cartao.bloqueio;

import br.com.zupacademy.caio.proposta.util.SystemName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "informar-boqueio", url = "http://localhost:8888/api/")
public interface InformarBloqueio {

    @PostMapping("cartoes/{numeroCard}/bloqueios")
    ResponseEntity<BloqueioResponse> enviar(@PathVariable String numeroCard, @RequestBody SystemName systemName);
}

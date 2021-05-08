package br.com.zupacademy.caio.proposta.nova_proposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "financeira", url = "http://localhost:9999/api")
public interface ConsultarFinanceira {
    @GetMapping("/solicitacao")
    FinanceiraResponse validar(FinanceiraRequest financeiraRequest);
}

package br.com.zupacademy.caio.proposta.nova_proposta.financeira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "financeira", url = "http://localhost:9999/api")
public interface ConsultarFinanceira {
    @GetMapping("/solicitacao")
    FinanceiraResponse validar(FinanceiraRequest financeiraRequest);
}

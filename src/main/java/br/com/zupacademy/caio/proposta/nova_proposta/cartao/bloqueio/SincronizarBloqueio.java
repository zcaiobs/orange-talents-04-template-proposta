package br.com.zupacademy.caio.proposta.nova_proposta.cartao.bloqueio;

import br.com.zupacademy.caio.proposta.util.SystemName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SincronizarBloqueio {

    Logger log = LoggerFactory.getLogger(SincronizarBloqueio.class);
    InformarBloqueio informarBloqueio;

    SincronizarBloqueio(InformarBloqueio informarBloqueio) {
        this.informarBloqueio = informarBloqueio;
    }

    public boolean sincronizar(String numeroCard) {
        try {
            var result = informarBloqueio.enviar(numeroCard, new SystemName());
            log.info(Objects.requireNonNull(result.getBody()).getResultado());
            return Objects.requireNonNull(result.getBody()).getResultado().equals("BLOQUEADO");
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex.getCause());
            return false;
        }
    }
}

package br.com.zupacademy.caio.proposta.nova_proposta.cartao.aviso;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ViagemRequest {
    @NotBlank
    private final String destino;
    @NotNull @Future
    private final LocalDate termino;

    public String getDestino() {
        return destino;
    }

    public LocalDate getTermino() {
        return termino;
    }

    public ViagemRequest(String destino, LocalDate termino) {
        this.destino = destino;
        this.termino = termino;
    }

    public Viagem toViagem(Long idCartao, String ip, String userAgent) {
        return new Viagem(
                idCartao, this.destino, this.termino,
                LocalDateTime.now(), ip, userAgent
                );
    }
}

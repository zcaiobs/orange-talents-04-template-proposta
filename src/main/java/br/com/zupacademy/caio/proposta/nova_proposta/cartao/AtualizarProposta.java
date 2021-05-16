package br.com.zupacademy.caio.proposta.nova_proposta.cartao;

import br.com.zupacademy.caio.proposta.nova_proposta.proposta.Proposta;
import br.com.zupacademy.caio.proposta.nova_proposta.proposta.PropostaRepository;
import br.com.zupacademy.caio.proposta.nova_proposta.proposta.PropostaStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AtualizarProposta {

    Logger log = LoggerFactory.getLogger(AtualizarProposta.class);

    ConsultarCartao consultarCartao;
    PropostaRepository propostaRepository;

    AtualizarProposta(ConsultarCartao consultarCartao, PropostaRepository propostaRepository) {
        this.consultarCartao = consultarCartao;
        this.propostaRepository = propostaRepository;
    }

    @Scheduled(fixedDelay = 10000)
    public void consultarCartao() {
        var propostas = propostaRepository.findAllByCartao_NumeroIsNullAndStatusLike(PropostaStatus.ELEGIVEL)
                .stream().map(this::consultar)
                .collect(Collectors.toList());
        atualizarPropostas(propostas);
    }

    public void atualizarPropostas(List<Proposta> propostas) {
        if (!(propostas.isEmpty()) && propostas.get(0).getCartao() != null) {
             var result = propostaRepository.saveAll(propostas);
            log.info("Número de propostas atualizadas: {}", propostas.size());
        }
    }

    public Proposta consultar(Proposta proposta) {
        try {
            var numero = Objects.requireNonNull(consultarCartao.consultar(proposta.getId()).getBody()).getId();
            proposta.setCartao(new Cartao(numero, CartaoStatus.DESBLOQUEADO));
            return proposta;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex.getCause());
        }
        return proposta;
    }
}
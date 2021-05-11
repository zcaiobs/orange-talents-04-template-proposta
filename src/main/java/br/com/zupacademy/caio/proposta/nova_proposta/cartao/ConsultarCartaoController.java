package br.com.zupacademy.caio.proposta.nova_proposta.cartao;

import br.com.zupacademy.caio.proposta.nova_proposta.Proposta;
import br.com.zupacademy.caio.proposta.nova_proposta.PropostaRepository;
import br.com.zupacademy.caio.proposta.nova_proposta.PropostaStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/cartoes")
public class ConsultarCartaoController {

    Logger log = LoggerFactory.getLogger(ConsultarCartaoController.class);

    ConsultarCartao consultarCartao;
    PropostaRepository propostaRepository;

    ConsultarCartaoController(ConsultarCartao consultarCartao, PropostaRepository propostaRepository) {
        this.consultarCartao = consultarCartao;
        this.propostaRepository = propostaRepository;
    }

    @Scheduled(fixedDelay = 10000)
    public void consultarCartao() {
        var propostas = propostaRepository.findAllByCartaoIsNullAndAndStatusLike(PropostaStatus.ELEGIVEL)
                .stream().map(this::consultar)
                .collect(Collectors.toList());
        atualizarPropostas(propostas);
    }

    public void atualizarPropostas(List<Proposta> propostas) {
        if (!(propostas.isEmpty()) && propostas.get(0).getCartao() != null) {
            propostaRepository.saveAll(propostas);
            log.info("Número de propostas atualizadas: {}", propostas.size());
        }
    }

    public Proposta consultar(Proposta proposta) {
        try {
            proposta.setCartao(Objects.requireNonNull(consultarCartao.consultar(proposta.getId()).getBody()).getId());
            return proposta;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex.getCause());
        }
        return proposta;
    }
}

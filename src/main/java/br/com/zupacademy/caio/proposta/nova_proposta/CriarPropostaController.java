package br.com.zupacademy.caio.proposta.nova_proposta;

import br.com.zupacademy.caio.proposta.nova_proposta.financeira.ConsultarFinanceira;
import br.com.zupacademy.caio.proposta.nova_proposta.financeira.FinanceiraRequest;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/propostas")
public class CriarPropostaController {

    Logger log = LoggerFactory.getLogger(CriarPropostaController.class);

    PropostaRepository propostaRepository;
    ConsultarFinanceira consultarFinanceira;

    CriarPropostaController(PropostaRepository propostaRepository, ConsultarFinanceira consultarFinanceira) {
        this.propostaRepository = propostaRepository;
        this.consultarFinanceira = consultarFinanceira;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uri) {
        var id = propostaRepository.save(request.toProposta()).getId();
        var status = consultar(new FinanceiraRequest(request.getDocumento(), request.getNome(), id.toString()));
        propostaRepository.atualizarStatusById(id, status);
        log.info("Proposta registrada: N - {}", id);
        return ResponseEntity.created(uri.path("/api/propostas/{id}").buildAndExpand(id).toUri()).build();
    }

    public PropostaStatus consultar(FinanceiraRequest financeiraRequest) {
        try {
            var result = consultarFinanceira.validar(financeiraRequest);
            if (result.getResultadoSolicitacao().equals("SEM_RESTRICAO")) return PropostaStatus.ELEGIVEL;
        } catch (FeignException ex) {
            log.error(ex.getMessage(), ex.getCause());
            ex.contentUTF8();
        }
        return PropostaStatus.NAO_ELEGIVEL;
    }


}

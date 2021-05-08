package br.com.zupacademy.caio.proposta.nova_proposta;

import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/propostas")
public class CriarPropostaController {

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
        return ResponseEntity.created(uri.path("/api/propostas/{id}").buildAndExpand(id).toUri()).build();
    }

    public Status consultar(FinanceiraRequest financeiraRequest) {
        try {
            var result = consultarFinanceira.validar(financeiraRequest);
            if (result.getResultadoSolicitacao().equals("SEM_RESTRICAO")) return Status.ELEGIVEL;
        } catch (FeignException ex) {
            ex.contentUTF8();
            return Status.NAO_ELEGIVEL;
        }
        return Status.NAO_ELEGIVEL;
    }
}

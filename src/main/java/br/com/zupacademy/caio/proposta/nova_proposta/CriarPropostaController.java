package br.com.zupacademy.caio.proposta.nova_proposta;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/propostas")
public class CriarPropostaController {

    PropostaRepository propostaRepository;

    CriarPropostaController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uri) {
        return ResponseEntity.created(uri.path("/api/propostas/{id}")
                .buildAndExpand(propostaRepository.save(request.toProposta()).getId())
                .toUri()).build();
    }
}

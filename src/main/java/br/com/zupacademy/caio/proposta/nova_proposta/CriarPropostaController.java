package br.com.zupacademy.caio.proposta.nova_proposta;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/propostas")
public class CriarPropostaController {

    PropostaRepository propostaRepository;
    CartoesClient cartoesClient;

    CriarPropostaController(PropostaRepository propostaRepository, CartoesClient cartoesClient) {
        this.propostaRepository = propostaRepository;
        this.cartoesClient = cartoesClient;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> criar(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uri) {
        var result1 = propostaRepository.save(request.toProposta(cartoesClient)).getId();
        var result = cartoesClient.cartoes(new FeignRequest("ss", "ss", "ss"));
        var result2 =  ResponseEntity.created(uri.path("/api/propostas/{id}")
                .buildAndExpand("").toUri()).build();
        return ResponseEntity.ok().build();
    }
}

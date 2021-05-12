package br.com.zupacademy.caio.proposta.nova_proposta;

import br.com.zupacademy.caio.proposta.exception.NotFoundException;
import br.com.zupacademy.caio.proposta.nova_proposta.proposta.PropostaRepository;
import br.com.zupacademy.caio.proposta.nova_proposta.proposta.PropostaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/api/propostas")
public class ConsultarPropostaController {

    PropostaRepository propostaRepository;

    ConsultarPropostaController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> consultar(@PathVariable @Valid @Positive @NotNull Long id) {
        var proposta = propostaRepository.findById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(PropostaResponse.converter(proposta));
    }
}

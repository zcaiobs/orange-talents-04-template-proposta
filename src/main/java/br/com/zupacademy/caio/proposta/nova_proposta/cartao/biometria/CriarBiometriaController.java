package br.com.zupacademy.caio.proposta.nova_proposta.cartao.biometria;

import br.com.zupacademy.caio.proposta.nova_proposta.cartao.CartaoRepository;
import br.com.zupacademy.caio.proposta.validator.ExistsValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/biometrias")
public class CriarBiometriaController {

    Logger log = LoggerFactory.getLogger(CriarBiometriaController.class);

    CartaoRepository cartaoRepository;
    BiometriaRepository biometriaRepository;

    CriarBiometriaController(CartaoRepository cartaoRepository, BiometriaRepository biometriaRepository) {
        this.cartaoRepository = cartaoRepository;
        this.biometriaRepository = biometriaRepository;
    }

    @PostMapping("/{cartaoId}")
    public ResponseEntity<?> criar(@Valid @PathVariable @NotNull  Long cartaoId,
                                   @Valid @RequestBody BiometriaRequest biometriaRequest,
                                   UriComponentsBuilder uri) {

        var cartao = cartaoRepository.findById(cartaoId);

        if(cartao.isPresent()) {
            var biometria = biometriaRepository.save(biometriaRequest.toBiometria());
            var card = cartao.get();
            card.getBiometrias().add(biometria);
            cartaoRepository.save(card);
            log.info("Uma nova biometria foi criada. id - {}", biometria.getId());
            return ResponseEntity.created(uri.path("/api/biometrias/{id}").buildAndExpand(biometria.getId()).toUri()).build();
        }
        log.warn("Cartão não encontrado. {}", cartao);
        return ResponseEntity.notFound().build();
    }
}

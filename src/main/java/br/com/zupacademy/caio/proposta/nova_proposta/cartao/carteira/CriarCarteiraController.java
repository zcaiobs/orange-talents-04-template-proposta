package br.com.zupacademy.caio.proposta.nova_proposta.cartao.carteira;

import br.com.zupacademy.caio.proposta.nova_proposta.cartao.Cartao;
import br.com.zupacademy.caio.proposta.nova_proposta.cartao.CartaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/api/carteiras")
public class CriarCarteiraController {

    Logger log = LoggerFactory.getLogger(CriarCarteiraController.class);

    CartaoRepository cartaoRepository;
    AssociarCarteira associarCarteira;
    CarteiraRepository carteiraRepository;

    CriarCarteiraController(CartaoRepository cartaoRepository, AssociarCarteira associarCarteira,
                            CarteiraRepository carteiraRepository) {
        this.cartaoRepository = cartaoRepository;
        this.associarCarteira = associarCarteira;
        this.carteiraRepository = carteiraRepository;
    }

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> criar(@PathVariable Long idCartao, @Valid @RequestBody CarteiraRequest carteiraRequest,
                                   UriComponentsBuilder uri) {
        var cartao = cartaoRepository.findById(idCartao);
        if (cartao.isPresent()) {
            var card = cartao.get();
            var isValid = carteiraRepository.existsCarteira(carteiraRequest.getCarteira(), card);
            if (isValid.isEmpty()) {
                var id = sincronizarCarteira(card, carteiraRequest);
                if (id != null) {
                    return ResponseEntity.created(uri.path("/api/carteiras/{id}").buildAndExpand(id).toUri()).build();
                }
            }
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.notFound().build();
    }

    Long sincronizarCarteira(Cartao cartao, CarteiraRequest carteiraRequest) {
        try {
            var result = associarCarteira.solicitar(cartao.getNumero(), carteiraRequest);
            if (result.getStatusCode().is2xxSuccessful()) {
                return carteiraRepository
                        .save(carteiraRequest.toCarteira(Objects.requireNonNull(result.getBody()).getId(), cartao))
                        .getId();
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex.getCause());
        }
        return null;
    }
}

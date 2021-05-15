package br.com.zupacademy.caio.proposta.nova_proposta.cartao.bloqueio;

import br.com.zupacademy.caio.proposta.nova_proposta.cartao.Cartao;
import br.com.zupacademy.caio.proposta.nova_proposta.cartao.CartaoRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/api/bloqueios")
public class BloqueioController {

    CartaoRepository cartaoRepository;

    BloqueioController(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    @PutMapping("/{idCartao}")
    public ResponseEntity<?> bloquear(@PathVariable Long idCartao, @RequestHeader HttpHeaders header) {
        try {
            var ip = Objects.requireNonNull(header.getHost()).getHostString();
            var userAgent = Objects.requireNonNull(header.get("User-Agent")).get(0);
            var cartao = cartaoRepository.findById(idCartao);

            if (cartao.isPresent()) {
                cartaoRepository.save(lock(cartao.get(), new Bloqueio(ip, userAgent, new Date())));
                return ResponseEntity.ok().build();
            }
                return ResponseEntity.notFound().build();
        } catch (NullPointerException ex) {
                return ResponseEntity.badRequest().build();
        }
    }

    private Cartao lock(Cartao cartao, Bloqueio bloqueio) {
        if (cartao.getBloqueio() == null) {
            cartao.setBloqueio(bloqueio);
            return cartao;
        }
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "bloqueio");
    }
}

package br.com.zupacademy.caio.proposta.nova_proposta.cartao.bloqueio;

import br.com.zupacademy.caio.proposta.nova_proposta.cartao.Cartao;
import br.com.zupacademy.caio.proposta.nova_proposta.cartao.CartaoRepository;
import br.com.zupacademy.caio.proposta.nova_proposta.cartao.CartaoStatus;
import br.com.zupacademy.caio.proposta.util.SystemName;
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
    SincronizarBloqueio sincronizarBloqueio;

    BloqueioController(CartaoRepository cartaoRepository, SincronizarBloqueio sincronizarBloqueio) {
        this.cartaoRepository = cartaoRepository;
        this.sincronizarBloqueio = sincronizarBloqueio;
    }

    @PutMapping("/{idCartao}")
    public ResponseEntity<?> bloquear(@PathVariable Long idCartao, @RequestHeader HttpHeaders header) {
        try {
            var ip = Objects.requireNonNull(header.getHost()).getHostString();
            var userAgent = Objects.requireNonNull(header.get("User-Agent")).get(0);
            var cartao = cartaoRepository.findById(idCartao);

            if (cartao.isPresent()) {
                if (sincronizarBloqueio.sincronizar(cartao.get().getNumero())) {
                    cartaoRepository.save(lock(cartao.get(), new Bloqueio(ip, userAgent, new Date(), true)));
                    return ResponseEntity.ok().build();
                }
                return ResponseEntity.unprocessableEntity().build();
            }
                return ResponseEntity.notFound().build();
        } catch (NullPointerException ex) {
                return ResponseEntity.badRequest().build();
        }
    }

    private Cartao lock(Cartao cartao, Bloqueio bloqueio) {
        if (cartao.getBloqueio() == null) {
            cartao.setBloqueio(bloqueio);
            cartao.setStatus(CartaoStatus.BLOQUEADO);
            return cartao;
        }
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "bloqueio");
    }
}

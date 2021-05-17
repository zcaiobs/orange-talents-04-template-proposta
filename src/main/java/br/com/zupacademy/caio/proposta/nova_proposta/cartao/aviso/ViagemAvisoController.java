package br.com.zupacademy.caio.proposta.nova_proposta.cartao.aviso;

import br.com.zupacademy.caio.proposta.nova_proposta.cartao.CartaoRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/api/avisos")
public class ViagemAvisoController {

    ViagemRepository viagemRepository;
    CartaoRepository cartaoRepository;

    ViagemAvisoController(ViagemRepository viagemRepository, CartaoRepository cartaoRepository) {
        this.viagemRepository = viagemRepository;
        this.cartaoRepository = cartaoRepository;
    }

    @PostMapping("/viagem/{id}")
    public ResponseEntity<?> avisar(@PathVariable Long id,
                                    @Valid @RequestBody ViagemRequest viagemRequest,
                                    @RequestHeader HttpHeaders headers) {
        var cartao = cartaoRepository.findById(id);
        if (cartao.isPresent()) {
            try{
                var ip = Objects.requireNonNull(headers.getHost()).getHostString();
                var userAgent = Objects.requireNonNull(headers.get("User-Agent")).get(0);
                viagemRepository.save(viagemRequest.toViagem(cartao.get().getId(), ip, userAgent));
                return ResponseEntity.ok().build();
            }catch (NullPointerException ex) {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}

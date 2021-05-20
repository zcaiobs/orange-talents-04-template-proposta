package br.com.zupacademy.caio.proposta.util;

import org.springframework.security.crypto.encrypt.Encryptors;

public class MyEncryptors {
    public static String encode(String text) {
        return Encryptors.text("proposta", "574bed3ab0c9c7").encrypt(text);
    }

    public static String decode(String text) {
        return Encryptors.text("proposta", "574bed3ab0c9c7").decrypt(text);
    }
}

package br.com.zupacademy.caio.proposta.nova_proposta.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .antMatchers(HttpMethod.GET, "/api/propostas/**").hasAuthority("SCOPE_proposta")
                        .antMatchers(HttpMethod.GET, "/api/cartoes/**").hasAuthority("SCOPE_proposta")
                        .antMatchers(HttpMethod.POST, "/api/cartoes/**").hasAuthority("SCOPE_proposta")
                        .antMatchers(HttpMethod.POST, "/api/propostas/**").hasAuthority("SCOPE_proposta")
                        .anyRequest().permitAll()
        ).csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}

package db.start.reciclaalegre.config;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import db.start.reciclaalegre.model.Usuario;
import db.start.reciclaalegre.repository.UsuarioRepository;
import db.start.reciclaalegre.service.JwtService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    public JwtAuthFilter(JwtService jwtService, UsuarioRepository usuarioRepository) {
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
                
        String token = request.getHeader("Authorization"); //"pegamos" do header de Authorization onde deve estar o nosso token

        if (token != null && token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");//removendo o "Bearer " da string do token. Sim tem um espaço depois de Bearer

            String subject = jwtService.recuperarSubject(token); // leia-se subject = email
            Usuario usuario = (Usuario) usuarioRepository.findByEmail(subject) //entidade que implementa UserDetails
                    .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));//o certo seria lançar uma except no request

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                    usuario.getAuthorities()); //cria uma autenticação com os dados do usuario

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                SecurityContextHolder.getContext().setAuthentication(authentication); //insere essa autenticação no contexto de Segurança
            }
        }
        filterChain.doFilter(request, response); //agora pode serguir para o controller ou outros filtros
    }
}
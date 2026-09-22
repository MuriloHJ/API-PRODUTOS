package com.br.apiprodutos.config;

import com.br.apiprodutos.entity.ProdutoEntity;
import com.br.apiprodutos.entity.Role;
import com.br.apiprodutos.entity.User;
import com.br.apiprodutos.repository.ProdutoRepository;
import com.br.apiprodutos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

/** * Carga inicial de dados para popular o banco durante a inicialização da aplicação * */

@Configuration
@RequiredArgsConstructor
public class CargaDadosInicial implements CommandLineRunner
{
    private final ProdutoRepository produtoRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception
    {
        //popula apenas se a tabela produto estiver vazia
        if(produtoRepository.count() == 0)
        {
            List<ProdutoEntity> produtosIniciais = List.of(
                    ProdutoEntity.builder()
                            .nome("Notebook Dell Inspiron")
                            .preco(new BigDecimal("4500.00"))
                            .ativo(true)
                            .build(),
                            ProdutoEntity.builder()
                            .nome("Mouse Gamer")
                            .preco(new BigDecimal("150.00"))
                            .ativo(true)
                            .build(),
                             ProdutoEntity.builder()
                            .nome("Teclado Mecânico")
                            .preco(new BigDecimal("350.00"))
                            .ativo(true)
                            .build(),
                             ProdutoEntity.builder()
                            .nome("Monitor 29")
                            .preco(new BigDecimal("1250.00"))
                            .ativo(true)
                            .build(),
                             ProdutoEntity.builder()
                            .nome("Fone de ouvido bluetooth (descontinuado)")
                            .preco(new BigDecimal("200.00"))
                            .ativo(false)
                            .build()
            );

            produtoRepository.saveAll(produtosIniciais);
        }
        //popula apenas se a tabela user estiver vazia
        if(userRepository.count() == 0)
        {
            List<User> userIniciais = List.of(
                    User.builder()
                            .nome("Admin")
                            .email("admin@gmail.com")
                            .senha(encoder.encode("123@Mudar"))
                            .role(Role.ADMIN)
                            .build(),
                    User.builder()
                            .nome("UsuarioNormal")
                            .email("usuarioNormal@gmail.com")
                            .senha(encoder.encode("321@Manter"))
                            .role(Role.USER)
                            .build()
            );
            userRepository.saveAll(userIniciais);
        }


    }
}

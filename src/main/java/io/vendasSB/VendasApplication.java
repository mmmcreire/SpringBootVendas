package io.vendasSB;

import io.vendasSB.domain.entity.Cliente;
import io.vendasSB.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            System.out.println("###############################                      Salvando clientes");
            clientes.salvar(new Cliente("teste"));
            clientes.salvar(new Cliente("Outro Cliente"));

            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);
            System.out.println("");
            System.out.println("###############################                      Atualizando clientes");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " +atualizado.");
                clientes.atualizar(c);
            });

            todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("");
            System.out.println("###############################                      Buscando clientes contendo 'CLI' ");
            clientes.buscarPorNome("Cli").forEach(System.out::println);

            System.out.println("");
            System.out.println("###############################                      Deletando clientes");
            clientes.obterTodos().forEach(c -> {
                clientes.deletar(c);
            });

            System.out.println("");
            System.out.println("###############################                      Buscando clientes após delete");
            todosClientes = clientes.obterTodos();
            if(todosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado.");
            }else{
                todosClientes.forEach(System.out::println);
            }
            System.out.println("##############################################################");

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}

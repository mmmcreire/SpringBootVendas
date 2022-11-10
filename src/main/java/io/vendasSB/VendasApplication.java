package io.vendasSB;

import io.vendasSB.domain.entity.Cliente;
import io.vendasSB.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            System.out.println("Salvando clientes");
            clientes.save(new Cliente("Fulano"));
            clientes.save(new Cliente("Outro Cliente"));

            boolean existe = clientes.existsByNome("aaaa");
            System.out.println("existe um cliente com o nome aaaa? " + existe);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
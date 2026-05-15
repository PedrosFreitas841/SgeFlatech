package com.flatech.sge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CLASSE PRINCIPAL DO SISTEMA
 * Inicia a aplicação Spring Boot
 */
@SpringBootApplication
public class SgeApplication {
    public static void main(String[] args) {
        SpringApplication.run(SgeApplication.class, args);
        System.out.println("=================================");
        System.out.println("SGE Flatech rodando!");
        System.out.println("Acesse: http://localhost:8080");
        System.out.println("=================================");
    }
}

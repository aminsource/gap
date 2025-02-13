package ir.gapgoft.podicom;

import org.springframework.boot.SpringApplication;

public class TestPodicomApplication {

    public static void main(String[] args) {
        SpringApplication.from(PodicomApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}

package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.example.repository.ComputerRepairRequestRepository;
import org.example.repository.ComputerRepairedFormRepository;
import org.example.repository.jdbc.ComputerRepairRequestJdbcRepository;
import org.example.repository.jdbc.ComputerRepairedFormJdbcRepository;
import org.example.services.ComputerRepairServices;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class RepairShopConfig {
    @Bean
    Properties getProps() {

        Properties  properties = new Properties();
        try {
            System.out.println("Search bd.config in directory " + ((new File(".")).getAbsolutePath()));
            properties.load(new FileReader("bd.config"));
        }catch (IOException e){
            System.err.println("Configuration error " + e);
        }
        return properties;
    }

    @Bean
    ComputerRepairRequestRepository requestsRepo(){
        return new ComputerRepairRequestJdbcRepository(getProps());

    }

    @Bean
    ComputerRepairedFormRepository formsRepo(){
       return new ComputerRepairedFormJdbcRepository(getProps());

    }

    @Bean
    ComputerRepairServices services(){
        return new ComputerRepairServices(requestsRepo(),formsRepo());

    }

}

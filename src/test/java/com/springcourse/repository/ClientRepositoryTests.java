package com.springcourse.repository;

import com.springcourse.domain.Client;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ClientRepositoryTests {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @Order(1)
    public void saveTest(){
        Client client = new Client(1L, "Jhonatas", "43357205807", "Rua Leontina Augusta de Souza", "55", "19883548", "", "18996582687", null);

        Client createdClient = clientRepository.save(client);

        assertThat(createdClient.getId()).isEqualTo(1L);
    }

    @Test
    public void updateTest(){
        Client client = new Client(1L, "Jhonatas henrique", "43357205807", "Rua Leontina Augusta de Souza", "55", "19883548", "", "18996582687", null);

        Client createdClient = clientRepository.save(client);

        assertThat(createdClient.getName()).isEqualTo("Jhonatas henrique");
    }

    @Test
    public void getByIdTest(){
        Optional<Client> result =  clientRepository.findById(1L);
        Client client = result.get();

        assertThat(client.getIdentificationNumber()).isEqualTo("43357205807");
    }

    @Test
    public void findByIdentificationNumber(){
        Optional<Client> result =  clientRepository.findByIdentificationNumber("43357205807");
        Client client = result.get();

        assertThat(client.getCep()).isEqualTo("19883548");
    }

    @Test
    public void findAll(){
        List<Client> clients = clientRepository.findAll();

        assertThat(clients.size()).isEqualTo(1);
    }

}

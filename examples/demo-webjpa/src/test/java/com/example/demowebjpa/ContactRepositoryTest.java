package com.example.demowebjpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ContactRepositoryTest {

    @Autowired
    ContactRepository contactRepository;

    @Test
    public void findAllByLastName_filtersByLastName() {

        String lastName = "Solheim";
        List<Contact> byLastName = contactRepository.findAllByLastName(lastName);

        assertThat(byLastName.size()).isEqualTo(2);
        assertThat(byLastName).allMatch((it) -> it.getLastName().equals(lastName));
    }
}

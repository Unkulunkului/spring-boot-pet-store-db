package by.home.springbootpetstoredb.service;

import by.home.springbootpetstoredb.entity.Category;
import by.home.springbootpetstoredb.entity.Pet;
import by.home.springbootpetstoredb.entity.PetStatusEnum;
import by.home.springbootpetstoredb.entity.Tag;
import by.home.springbootpetstoredb.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PetServiceTest {

    @Autowired
    private PetRepository petRepository;

    private Pet pet;

    @BeforeEach
    void createPet(){
        Category category = new Category();
        category.setName("Categor");
        Tag tag1 = new Tag();
        tag1.setName("Tag");
        Tag tag2 = new Tag();
        tag2.setName("Tag");
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(tag1);
        tags.add(tag2);
        pet = new Pet(category, "Pet", tags, PetStatusEnum.AVAILABLE);
    }


    @Test
    void save() {
        Pet actual = petRepository.save(pet);
        assertEquals(pet, actual);
    }

    @Test
    void getById() {
        petRepository.save(pet);
        Pet actual = petRepository.getOne(pet.getId());
        assertEquals(pet, actual);
    }

    @Test
    void findAllByStatus() {
        Category category = new Category();
        category.setName("Categor");
        Tag tag1 = new Tag();
        tag1.setName("Tag");
        Tag tag2 = new Tag();
        tag2.setName("Tag");
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(tag1);
        tags.add(tag2);
        Pet pet2 = new Pet(category, "Pet", tags, PetStatusEnum.PENDING);
        petRepository.save(pet);
        petRepository.save(pet2);
        List<Pet> expected = new ArrayList<>();
        expected.add(pet);
        List<Pet> actual = petRepository.findAllByStatus(PetStatusEnum.AVAILABLE);
        assertEquals(expected, actual);
    }

}
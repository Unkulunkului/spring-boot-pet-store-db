package by.home.springbootpetstoredb.service;

import by.home.springbootpetstoredb.entity.Category;
import by.home.springbootpetstoredb.entity.Pet;
import by.home.springbootpetstoredb.entity.PetStatusEnum;
import by.home.springbootpetstoredb.entity.Tag;
import by.home.springbootpetstoredb.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PetServiceTest {

    @Autowired
    private PetRepository petRepository;

    @Test
    void save() {
        Category category = new Category();
        category.setName("Categor");
        Tag tag1 = new Tag();
        tag1.setName("Tag");
        Tag tag2 = new Tag();
        tag2.setName("Tag");
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(tag1);
        tags.add(tag2);
        Pet expected = new Pet(category, "Pet", tags, PetStatusEnum.AVAILABLE);
        Pet actual = petRepository.save(expected);
        assertEquals(expected, actual);
    }

    @Test
    void getById() {
        Category category = new Category();
        category.setName("Categor");
        Tag tag1 = new Tag();
        tag1.setName("Tag");
        Tag tag2 = new Tag();
        tag2.setName("Tag");
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(tag1);
        tags.add(tag2);
        Pet expected = new Pet(category, "Pet", tags, PetStatusEnum.AVAILABLE);
        petRepository.save(expected);
        Pet actual = petRepository.getOne(1L);
        assertEquals(expected, actual);
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
        Pet pet1 = new Pet(category, "Pet", tags, PetStatusEnum.AVAILABLE);
        Pet pet2 = new Pet(category, "Pet", tags, PetStatusEnum.PENDING);
        petRepository.save(pet1);
        petRepository.save(pet2);
        List<Pet> expected = new ArrayList<>();
        expected.add(pet1);
        List<Pet> actual = petRepository.findAllByStatus(PetStatusEnum.AVAILABLE);
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
    }

}
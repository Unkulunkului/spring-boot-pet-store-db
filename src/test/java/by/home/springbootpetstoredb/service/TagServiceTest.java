package by.home.springbootpetstoredb.service;

import by.home.springbootpetstoredb.entity.Tag;
import by.home.springbootpetstoredb.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TagServiceTest {

    @Autowired
    private TagRepository tagRepository;

    @Test
    void save() {
        Tag expected = new Tag();
        expected.setName("Tag");
        Tag actual = tagRepository.save(expected);
        assertEquals(expected, actual);
    }

    @Test
    void getById() {
        Tag expected = new Tag();
        expected.setName("Tag");
        tagRepository.save(expected);
        Tag actual = tagRepository.getOne(1L);
        assertEquals(expected, actual);
    }
}
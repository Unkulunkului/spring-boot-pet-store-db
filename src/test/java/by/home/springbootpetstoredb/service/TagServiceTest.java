package by.home.springbootpetstoredb.service;

import by.home.springbootpetstoredb.entity.Tag;
import by.home.springbootpetstoredb.repository.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TagServiceTest {

    @Autowired
    private TagRepository tagRepository;

    private Tag tag;

    @BeforeEach
    void createTag(){
        tag = new Tag();
        tag.setName("Tag");
    }

    @Test
    void save() {
        Tag actual = tagRepository.save(tag);
        assertEquals(tag, actual);
    }

    @Test
    void getById() {
        tagRepository.save(tag);
        Tag actual = tagRepository.getOne(tag.getId());
        assertEquals(tag, actual);
    }
}
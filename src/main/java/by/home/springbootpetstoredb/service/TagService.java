package by.home.springbootpetstoredb.service;

import by.home.springbootpetstoredb.entity.Tag;
import by.home.springbootpetstoredb.exception.NotFoundException;
import by.home.springbootpetstoredb.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public Tag save(Tag tag){
        log.info(tag+" was saved");
        return tagRepository.save(tag);
    }

    public Tag getById(long id){
        Optional<Tag> byId = tagRepository.findById(id);
        if(byId.isPresent()){
            log.info("Tag "+byId.get()+" was returned by id");
            return byId.get();
        }
        log.warn("Tag with id="+id+" doesn't exist");
        throw new NotFoundException("Tag with id="+id+" doesn't exist");
    }
}

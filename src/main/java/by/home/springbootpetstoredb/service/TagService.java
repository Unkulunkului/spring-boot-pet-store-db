package by.home.springbootpetstoredb.service;

import by.home.springbootpetstoredb.entity.Tag;
import by.home.springbootpetstoredb.exception.NotFoundException;
import by.home.springbootpetstoredb.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public Tag save(Tag tag){
        return tagRepository.save(tag);
    }

    public Tag getById(long id){
        Optional<Tag> byId = tagRepository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        throw new NotFoundException("Tag with id="+id+" doesn't exist");
    }
}

package by.home.springbootpetstoredb.resource;

import by.home.springbootpetstoredb.entity.Tag;
import by.home.springbootpetstoredb.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/tag")
public class TagResource {

    @Autowired
    private TagService tagService;

    @PostMapping
    public ResponseEntity<Tag> save(@Valid @RequestBody Tag tag){
        Tag save = tagService.save(tag);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }
}

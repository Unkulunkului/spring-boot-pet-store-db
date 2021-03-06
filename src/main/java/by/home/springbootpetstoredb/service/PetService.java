package by.home.springbootpetstoredb.service;

import by.home.springbootpetstoredb.entity.Pet;
import by.home.springbootpetstoredb.entity.PetStatusEnum;
import by.home.springbootpetstoredb.entity.Tag;


import by.home.springbootpetstoredb.exception.NotFoundException;
import by.home.springbootpetstoredb.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TagService tagService;

    @Transactional
    public Pet save(Pet pet){
        List<Tag> tags = updateTags(pet.getTags());
        pet.setTags(tags);
        return petRepository.save(pet);
    }

    private List<Tag> updateTags(List<Tag>tags){
        List<Tag> tagList = new ArrayList<>();
        for (Tag tag : tags) {
            Tag byId = tagService.getById(tag.getId());
            tagList.add(byId);
        }
        return tagList;
    }

    public void updatePet(Pet pet){
        long id = pet.getId();
        Pet byId = getById(id);
        pet.setId(id);
        pet.setTags(updateTags(pet.getTags()));
        save(pet);
    }

    public Pet getById(long id){
        Optional<Pet> byId = petRepository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        throw new NotFoundException("Pet doesn't exist");
    }


    public List<Pet> findAllByStatus(PetStatusEnum petStatusEnum){
        List<Pet> byStatus = petRepository.findAllByStatus(petStatusEnum);
        if (byStatus.size()!=0) {
            return byStatus;
        }
        throw new NotFoundException("Pet with "+petStatusEnum+" status doesn't exist");
    }

    public void deleteById(long id){
        if (getById(id) != null) {
            petRepository.deleteById(id);
        }
    }
}

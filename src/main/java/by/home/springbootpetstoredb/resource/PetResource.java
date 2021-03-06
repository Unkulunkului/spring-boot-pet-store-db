package by.home.springbootpetstoredb.resource;

import by.home.springbootpetstoredb.entity.Pet;
import by.home.springbootpetstoredb.entity.PetStatusEnum;
import by.home.springbootpetstoredb.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "pet")
public class PetResource {

    @Autowired
    private PetService petService;

    @PostMapping
    public ResponseEntity<Pet> save(@Valid @RequestBody Pet pet){
        petService.save(pet);
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> update(@Valid @RequestBody Pet pet){
        petService.updatePet(pet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Pet> getById(@PathVariable("id") long id){
        Pet byId = petService.getById(id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }


    @GetMapping(path = "/findAllByStatus")
    public ResponseEntity<List<Pet>> findAllByStatus(PetStatusEnum status){
        List<Pet> allByStatus = petService.findAllByStatus(status);
        return new ResponseEntity<>(allByStatus, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id){
        petService.deleteById(id);
        return new ResponseEntity<>("Delete was performed",HttpStatus.OK);
    }
}

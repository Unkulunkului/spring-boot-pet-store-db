package by.home.springbootpetstoredb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pet {

    public Pet(Category category, String name, List<Tag> tags, PetStatusEnum status) {
        this.category = category;
        this.name = name;
        this.tags = tags;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Category category;

    @Pattern(regexp = "[A-Z][a-z]{2,7}")
    private String name;
    @ManyToMany
    private List<Tag> tags;

    private PetStatusEnum status;


}

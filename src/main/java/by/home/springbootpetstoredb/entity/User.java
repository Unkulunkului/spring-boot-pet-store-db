package by.home.springbootpetstoredb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Pattern(regexp = "\\w{5,10}")
    private String userName;
    @Pattern(regexp = "[A-Z][a-z]{2,7}")
    private String firstName;
    @Pattern(regexp = "[A-Z][a-z]{2,7}")
    private String lastName;
    @Pattern(regexp = "\\S{3,10}@((gmail.com)|(mail.ru)|(yahoo.com)|(yandex.ru))",
            message = "@gmail.com or @mail.ru or @yahoo.com or @yandex.ru")
    private String email;
    @Pattern(regexp = "\\S{6,15}", message = "length from 6 to 15, don't use space")
    private String password;
    @ElementCollection
    private List<String> phone;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private UserRoleEnum role;
    private int userStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }
}

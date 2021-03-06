package by.home.springbootpetstoredb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

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
    @NotBlank
    @NotEmpty
    private String phone;
    private int userStatus;

}

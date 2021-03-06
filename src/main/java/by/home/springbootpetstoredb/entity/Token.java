package by.home.springbootpetstoredb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private String key;
    private UserRoleEnum userRoleEnum;

    public Token (long userId, UserRoleEnum userRoleEnum ,String key){
        this.userId = userId;
        this.userRoleEnum = userRoleEnum;
        this.key = key;
    }
}

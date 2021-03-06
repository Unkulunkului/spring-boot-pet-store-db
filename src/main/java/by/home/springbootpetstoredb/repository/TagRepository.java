package by.home.springbootpetstoredb.repository;

import by.home.springbootpetstoredb.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag getByName(String name);
    Tag readById(long id);
}

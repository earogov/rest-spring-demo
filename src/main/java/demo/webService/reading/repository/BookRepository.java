package demo.webService.reading.repository;

import demo.webService.reading.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select max(id) from Book")
    Long getMaxId();
}
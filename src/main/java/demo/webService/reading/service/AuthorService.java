package demo.webService.reading.service;

import demo.webService.reading.model.Author;
import org.springframework.dao.DataAccessException;

public interface AuthorService {

    Author findById(Long authorId) throws DataAccessException;
    void save(Author author) throws DataAccessException;
}

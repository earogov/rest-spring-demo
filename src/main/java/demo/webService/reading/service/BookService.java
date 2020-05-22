package demo.webService.reading.service;

import demo.webService.reading.model.Book;
import org.springframework.dao.DataAccessException;

public interface BookService {

    Book findById(Long bookId) throws DataAccessException;
    void save(Book book) throws DataAccessException;
}

package demo.webService.reading.service;

import demo.webService.reading.model.Book;
import demo.webService.reading.repository.BookRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    @NonNull
    protected final BookRepository bookRepository;

    @Override
    public Book findById(Long bookId) throws DataAccessException {
        return bookRepository.findById(bookId).orElse(null);
    }

    @Override
    public void save(Book book) throws DataAccessException {
        bookRepository.save(book);
    }
}

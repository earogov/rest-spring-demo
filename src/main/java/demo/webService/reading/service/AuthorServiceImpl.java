package demo.webService.reading.service;

import demo.webService.reading.model.Author;
import demo.webService.reading.repository.AuthorRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    @NonNull
    protected final AuthorRepository authorRepository;

    @Override
    public Author findById(Long authorId) throws DataAccessException {
        return authorRepository.findById(authorId).orElse(null);
    }

    @Override
    public void save(Author author) throws DataAccessException {
        authorRepository.save(author);
    }
}

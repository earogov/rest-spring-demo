package demo.webService.service;

import demo.webService.reading.model.Author;
import demo.webService.reading.model.Book;
import demo.webService.reading.repository.AuthorRepository;
import demo.webService.reading.service.AuthorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthorServiceTests {

    @Autowired
    protected AuthorService authorService;

    @Autowired
    protected AuthorRepository authorRepository;

    @Test
    @Transactional
    public void shouldFindAuthorById() {
        Author author = authorService.findById(2L);
        assertSame(2L, author.getId());
        assertSame(2, author.getBooks().size());
        assertEquals("Quarantine", author.sortedBooks().first().getTitle());
        Iterator<Book> iterator = author.sortedBooks().iterator();
        iterator.next();
        assertEquals("Permutation city", iterator.next().getTitle());
    }
}
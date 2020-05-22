package demo.webService.service;

import demo.webService.reading.model.Author;
import demo.webService.reading.model.Book;
import demo.webService.reading.repository.BookRepository;
import demo.webService.reading.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookServiceTests {

    @Autowired
    protected BookService bookService;

    @Autowired
    protected BookRepository bookRepository;

    @Test
    @Transactional
    public void shouldFindBookById() {
        Book book = bookService.findById(1L);
        assertSame(1L, book.getId());
        assertSame(1, book.getAuthors().size());
        assertEquals("Greg Egan", book.sortedAuthors().first().getFullName());
    }

    @Test
    @Transactional
    public void shouldInsertBookWithAuthor() {
        Author author = new Author();
        author.setFullName("Joshua Bloch");
        Set<Author> authors = new HashSet<>();
        authors.add(author);

        Book book = new Book();
        book.setIsbn("111-123-111");
        book.setTitle("Effective Java");
        book.setAuthors(authors);

        bookService.save(book);

        book = bookService.findById(bookRepository.getMaxId());
        assertEquals("111-123-111", book.getIsbn());
        assertEquals("Effective Java", book.getTitle());
        assertSame(1, book.getAuthors().size());
        assertEquals("Joshua Bloch", book.sortedAuthors().first().getFullName());
    }
}

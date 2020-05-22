package demo.webService.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.webService.reading.model.Author;
import demo.webService.reading.model.Book;
import demo.webService.reading.rest.BookRestController;
import demo.webService.reading.service.BookService;
import demo.webService.service.ApplicationTestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationTestConfig.class)
@WebAppConfiguration
public class BookRestControllerTests {

    @Autowired
    private BookRestController bookRestController;

    @MockBean
    private BookService bookService;

    private MockMvc mockMvc;

    private List<Book> books;

    @Before
    public void initBooks() {

        mockMvc = MockMvcBuilders.standaloneSetup(bookRestController).build();

        books = new ArrayList<>();

        Book book = new Book();
        book.setId(1L);
        book.setIsbn("111-222-333");
        book.setTitle("Effective Java");
        book.setDescription("Java programming guide");

        Author author1 = new Author();
        author1.setId(1L);
        author1.setFullName("Joshua Bloch");

        book.addAuthor(author1);

        Author author2 = new Author();
        author2.setId(2L);
        author2.setFullName("Coauthor");

        book.addAuthor(author2);

        books.add(book);
    }

    @Test
    @WithMockUser(roles="GUEST")
    public void getBookSuccess() throws Exception {
        given(bookService.findById(1L)).willReturn(books.get(0));
        mockMvc.perform(get("/books/1")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.isbn").value("111-222-333"))
                .andExpect(jsonPath("$.title").value("Effective Java"))
                .andExpect(jsonPath("$.authors[0].id").value("1"))
                .andExpect(jsonPath("$.authors[0].fullName").value("Joshua Bloch"))
                .andExpect(jsonPath("$.authors[1].id").value("2"))
                .andExpect(jsonPath("$.authors[1].fullName").value("Coauthor"));
    }

    @Test
    @WithMockUser(roles="GUEST")
    public void addBookSuccess() throws Exception {
        Book book = books.get(0);
        book.setId(null);
        ObjectMapper mapper = new ObjectMapper();
        String bookJson = mapper.writeValueAsString(book);
        mockMvc.perform(post("/books")
                .content(bookJson).accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }
}

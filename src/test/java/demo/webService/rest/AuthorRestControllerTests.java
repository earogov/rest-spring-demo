package demo.webService.rest;

import demo.webService.reading.model.Author;
import demo.webService.reading.model.Book;
import demo.webService.reading.rest.AuthorRestController;
import demo.webService.reading.service.AuthorService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationTestConfig.class)
@WebAppConfiguration
public class AuthorRestControllerTests {

    @Autowired
    private AuthorRestController authorRestController;

    @MockBean
    private AuthorService authorService;

    private MockMvc mockMvc;

    private List<Author> authors;

    @Before
    public void initAuthors() {

        mockMvc = MockMvcBuilders.standaloneSetup(authorRestController).build();

        authors = new ArrayList<>();

        Author author = new Author();
        author.setId(1L);
        author.setFullName("Joshua Bloch");

        Book book1 = new Book();
        book1.setId(1L);
        book1.setIsbn("111-222-333");
        book1.setTitle("Effective Java");
        book1.setDescription("Java programming guide");

        author.addBook(book1);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setIsbn("123-456-789");
        book2.setTitle("Effective Java 2nd Edition");
        book2.setDescription("Java programming guide 2nd Edition");

        author.addBook(book2);

        authors.add(author);
    }

    @Test
    @WithMockUser(roles="GUEST")
    public void getAuthorSuccess() throws Exception {
        given(authorService.findById(1L)).willReturn(authors.get(0));
        mockMvc.perform(get("/authors/1")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.fullName").value("Joshua Bloch"))
                .andExpect(jsonPath("$.books[0].isbn").value("111-222-333"))
                .andExpect(jsonPath("$.books[0].title").value("Effective Java"))
                .andExpect(jsonPath("$.books[1].isbn").value("123-456-789"))
                .andExpect(jsonPath("$.books[1].title").value("Effective Java 2nd Edition"));
    }
}

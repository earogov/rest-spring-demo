package demo.webService.reading.rest;

import demo.webService.reading.model.Author;
import demo.webService.reading.service.AuthorService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthorRestController {

    @NonNull
    protected final AuthorService authorService;

    @RequestMapping(value = "/authors/{authorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Author> getAuthor(@PathVariable("authorId") Long authorId) {

        return new ResponseEntity<>(authorService.findById(authorId), HttpStatus.OK);
    }
}
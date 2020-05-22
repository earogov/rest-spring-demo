package demo.webService.reading.rest;

import demo.webService.reading.model.Book;
import demo.webService.reading.service.BookService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    @NonNull
    protected final BookService bookService;

    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Book> getAuthor(@PathVariable("bookId") Long bookId) {

        return new ResponseEntity<>(bookService.findById(bookId), HttpStatus.OK);
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Book> addBook(
            @RequestBody @Valid Book book,
            BindingResult bindingResult,
            UriComponentsBuilder ucBuilder) {

        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if(bindingResult.hasErrors() || (book == null)){
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        bookService.save(book);
        headers.setLocation(ucBuilder.path("/books/{id}").buildAndExpand(book.getId()).toUri());
        return new ResponseEntity<>(book, headers, HttpStatus.CREATED);
    }
}
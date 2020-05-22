package demo.webService.reading.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
@Table(name = "authors")
@JsonSerialize(using = JacksonAuthorSerializer.class)
@NoArgsConstructor
public class Author implements Comparable<Author> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "readingSequence")
    @SequenceGenerator(name = "readingSequence", sequenceName = "READING_SEQUENCE", allocationSize = 1)
    @Getter @Setter
    private Long id;

    @NotEmpty
    @Column(name = "full_name", nullable = false)
    @Getter @Setter
    private String fullName;

    @ManyToMany(
            mappedBy = "authors",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
            )
    @Getter @Setter
    private Set<Book> books = new HashSet<>();

    public void addBook(Book book) {
        books.add(book);
        book.getAuthors().add(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.getAuthors().remove(this);
    }

    public void removeAllBooks() {
        for(Book book : new HashSet<>(books)) {
            removeBook(book);
        }
    }

    public SortedSet<Book> sortedBooks() {
        return new TreeSet<>(books);
    }

    public SortedSet<Book> sortedBooks(Comparator<Book> comparator) {
        TreeSet<Book> sorted = new TreeSet<>(comparator);
        sorted.addAll(books);
        return sorted;
    }

    @Override
    public int compareTo(Author author) {
        return Long.compare(id, author.getId());
    }
}
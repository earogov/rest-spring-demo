package demo.webService.reading.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
@Table(name = "books")
@JsonSerialize(using = JacksonBookSerializer.class)
@NoArgsConstructor
public class Book implements Comparable<Book> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "readingSequence")
    @SequenceGenerator(name = "readingSequence", sequenceName = "READING_SEQUENCE", allocationSize = 1)
    @Getter @Setter
    private Long id;

    @NotEmpty
    @Getter @Setter
    private String isbn;

    @NotEmpty
    @Getter @Setter
    private String title;

    @Getter @Setter
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_authors",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id")}
    )
    @Getter @Setter
    private Set<Author> authors = new HashSet<>();

    public void addAuthor(Author author) {
        authors.add(author);
        author.getBooks().add(this);
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
        author.getBooks().remove(this);
    }

    public void removeAllAuthors() {
        for(Author author : new HashSet<>(authors)) {
            removeAuthor(author);
        }
    }

    public SortedSet<Author> sortedAuthors() {
        return new TreeSet<>(authors);
    }

    public SortedSet<Author> sortedAuthors(Comparator<Author> comparator) {
        TreeSet<Author> sorted = new TreeSet<>(comparator);
        sorted.addAll(authors);
        return sorted;
    }

    @Override
    public int compareTo(Book book) {
        return Long.compare(id, book.getId());
    }
}
package demo.webService.reading.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class JacksonAuthorSerializer extends StdSerializer<Author> {

    private Boolean hideBooks;

    public JacksonAuthorSerializer() {
        this(Author.class);
    }

    public JacksonAuthorSerializer(Boolean hideBooks) {
        this(Author.class, hideBooks);
    }

    public JacksonAuthorSerializer(Class<Author> t) {
        super(t);
        this.hideBooks = false;
    }

    public JacksonAuthorSerializer(Class<Author> t, Boolean hideBooks) {
        super(t);
        this.hideBooks = hideBooks;
    }

    @Override
    public void serialize(Author author, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        if (author.getId() == null) {
            jgen.writeNullField("id");
        } else {
            jgen.writeNumberField("id", author.getId());
        }
        jgen.writeStringField("fullName", author.getFullName());

        if (!hideBooks) {
            JacksonBookSerializer bookSer = new JacksonBookSerializer(true);
            jgen.writeArrayFieldStart("books");
            for (Book book : author.sortedBooks()) {
                bookSer.serialize(book, jgen, provider);
            }
            jgen.writeEndArray();
        }
        jgen.writeEndObject();
    }
}
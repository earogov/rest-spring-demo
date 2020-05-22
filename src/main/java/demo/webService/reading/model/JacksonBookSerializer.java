package demo.webService.reading.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class JacksonBookSerializer extends StdSerializer<Book> {

    private Boolean hideAuthors;

    public JacksonBookSerializer() {
        this(Book.class);
    }

    public JacksonBookSerializer(Boolean hideAuthors) {
        this(Book.class, hideAuthors);
    }

    public JacksonBookSerializer(Class<Book> t) {
        super(t);
        this.hideAuthors = false;
    }

    public JacksonBookSerializer(Class<Book> t, Boolean hideAuthors) {
        super(t);
        this.hideAuthors = hideAuthors;
    }

    @Override
    public void serialize(Book book, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        if (book.getId() == null) {
            jgen.writeNullField("id");
        } else {
            jgen.writeNumberField("id", book.getId());
        }
        jgen.writeStringField("isbn", book.getIsbn());
        jgen.writeStringField("title", book.getTitle());
        jgen.writeStringField("description", book.getDescription());

        if (!hideAuthors) {
            JacksonAuthorSerializer authorSer = new JacksonAuthorSerializer(true);
            jgen.writeArrayFieldStart("authors");
            for (Author author : book.sortedAuthors()) {
                authorSer.serialize(author, jgen, provider);
            }
            jgen.writeEndArray();
        }
        jgen.writeEndObject();
    }
}

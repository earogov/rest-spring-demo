package demo.webService.model;

import demo.webService.reading.model.Book;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class ValidatorTests {

    private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
    }

    private <T> Boolean isViolated(String path, Set<ConstraintViolation<T>> violations) {
        boolean result = false;
        for (ConstraintViolation<T> v : violations) {
            if (v.getPropertyPath().toString().equals(path)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Test
    public void bookIsbnShouldBeNonempty() {

        Validator validator = createValidator();

        Book validBook = new Book();
        validBook.setIsbn("123-456-789");
        assertFalse(isViolated("isbn", validator.validate(validBook)));

        Book invalidBook = new Book();
        invalidBook.setTitle("ABC");
        assertTrue(isViolated("isbn", validator.validate(invalidBook)));
    }

    @Test
    public void bookTitleShouldBeNonempty() {

        Validator validator = createValidator();

        Book validBook = new Book();
        validBook.setTitle("ABC");
        assertFalse(isViolated("title", validator.validate(validBook)));

        Book invalidBook = new Book();
        invalidBook.setIsbn("123-456-789");
        assertTrue(isViolated("title", validator.validate(invalidBook)));
    }
}

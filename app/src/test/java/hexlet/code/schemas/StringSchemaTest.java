package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringSchemaTest {

    private Validator validator;
    private StringSchema schema;

    @BeforeEach
    public void createSchema() {
        validator = new Validator();
        schema = validator.string();
    }

    @Test
    public void test1() {

        boolean actual = schema.isValid("");
        boolean expected = true;

        assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void test2() {

        boolean actual = schema.isValid(null);
        boolean expected = true;

        assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void test3() {

        schema.required();
        boolean actual = schema.isValid(null);
        boolean expected = false;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test4() {

        schema.required();
        boolean actual = schema.isValid("");
        boolean expected = false;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test5() {

        boolean actual = schema.isValid("text");
        boolean expected = true;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test6() {

        boolean actual = schema.contains("te").isValid("text");
        boolean expected = true;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test7() {

        boolean actual = schema.contains("no").isValid("text");
        boolean expected = false;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test8() {

        boolean actual = schema.minLength(10).isValid("abc");
        boolean expected = false;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test9() {

        boolean actual = schema.minLength(3).isValid("abc");
        boolean expected = true;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void test10() {

        boolean actual = schema.minLength(10).minLength(3).isValid("abc");
        boolean expected = true;

        assertThat(actual).isEqualTo(expected);
    }

}

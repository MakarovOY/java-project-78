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
    public void testIsValidWithEmptyString() {

        boolean actual = schema.isValid("");
        boolean expected = true;

        assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void testIsValidWithNull() {

        boolean actual = schema.isValid(null);
        boolean expected = true;

        assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void testRequiredMethodWithNull() {

        schema.required();
        boolean actual = schema.isValid(null);
        boolean expected = false;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testRequiredMethodWithEmptyString() {

        schema.required();
        boolean actual = schema.isValid("");
        boolean expected = false;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testIsValidWithValue() {

        boolean actual = schema.isValid("text");
        boolean expected = true;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testContainsMethodWithMatch() {

        boolean actual = schema.contains("te").isValid("text");
        boolean expected = true;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testContainsMethodWithoutMatch() {

        boolean actual = schema.contains("no").isValid("text");
        boolean expected = false;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testMinLengthMethodWithShortestStringValue() {

        boolean actual = schema.minLength(10).isValid("abc");
        boolean expected = false;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testMinLengthMethodWithEnoughStringValue() {

        boolean actual = schema.minLength(3).isValid("abc");
        boolean expected = true;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testMinLengthMethodWithChangedValue() {

        boolean actual = schema.minLength(10).minLength(3).isValid("abc");
        boolean expected = true;

        assertThat(actual).isEqualTo(expected);
    }

}

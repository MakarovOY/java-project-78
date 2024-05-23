package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberSchemaTest {
    Validator validator;
    NumberSchema schema;

    @BeforeEach
    public void createNumberSchema() {
        validator = new Validator();
        schema = validator.number();
    }
    @Test
    public void test1() {

        boolean actual = schema.isValid(1);
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

        boolean actual = schema.positive().isValid(null);
        boolean expected = true;

        assertThat(actual).isEqualTo(expected);

    }
    @Test
    public void test5() {

        schema.required();
        boolean actual = schema.positive().isValid(10);
        boolean expected = true;

        assertThat(actual).isEqualTo(expected);

    }
    @Test
    public void test6() {

        schema.required();
        boolean actual = schema.positive().isValid(-10);
        boolean expected = false;

        assertThat(actual).isEqualTo(expected);

    }
    @Test
    public void test7() {

        schema.required();
        boolean actual = schema.positive().isValid(0);
        boolean expected = false;

        assertThat(actual).isEqualTo(expected);

    }
    @Test
    public void test8() {

        schema.required();
        boolean actual1 = schema.positive().range(3, 5).isValid(3);
        boolean actual2 = schema.positive().range(3, 5).isValid(4);
        boolean actual3 = schema.positive().range(3, 5).isValid(5);
        boolean expected = true;

        assertThat(actual1).isEqualTo(expected);
        assertThat(actual2).isEqualTo(expected);
        assertThat(actual3).isEqualTo(expected);

    }
    @Test
    public void test9() {

        schema.required();
        boolean actual1 = schema.positive().range(8, 10).isValid(6);
        boolean actual2 = schema.positive().range(3, 5).isValid(11);
        boolean expected = false;

        assertThat(actual1).isEqualTo(expected);
        assertThat(actual2).isEqualTo(expected);


    }
    @Test
    public void test10() {

        schema.required();
        boolean actual1 = schema.range(-5, -3).isValid(-3);
        boolean actual2 = schema.range(-5, -3).isValid(-4);
        boolean actual3 = schema.range(-5, -3).isValid(-5);
        boolean expected = true;

        assertThat(actual1).isEqualTo(expected);
        assertThat(actual2).isEqualTo(expected);
        assertThat(actual3).isEqualTo(expected);

    }
    @Test
    public void test11() {

        schema.required();
        boolean actual1 = schema.range(-3, -5).isValid(-2);
        boolean actual2 = schema.range(-3, -5).isValid(-6);

        boolean expected = false;

        assertThat(actual1).isEqualTo(expected);
        assertThat(actual2).isEqualTo(expected);


    }
    @Test
    public void test12() {

        schema.required();
        boolean actual1 = schema.positive().range(5, 3).isValid(4);
        boolean actual2 = schema.positive().range(5, 3).isValid(4);
        boolean actual3 = schema.positive().range(5, 3).isValid(5);
        boolean expected = false;

        assertThat(actual1).isEqualTo(expected);
        assertThat(actual2).isEqualTo(expected);
        assertThat(actual3).isEqualTo(expected);

    }

}

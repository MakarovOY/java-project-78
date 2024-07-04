package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

    private Validator v;
    @BeforeEach
    public void createValidator() {
        v = new Validator();
    }

    @Test
    public void mapTest() {
        var schema1 = v.map();
        assertThat(schema1.isValid(null)).isTrue();

        var schema2 = v.map();
        assertThat(schema2.required().isValid(null)).isFalse();

        var schema3 = v.map();
        assertThat(schema3.required().isValid(new HashMap<>())).isTrue();

        var schema4 = v.map();
        var data4 = new HashMap<String, String>();
        data4.put("key1", "value1");
        assertThat(schema4.required().isValid(data4)).isTrue();

        var schema5 = v.map();
        schema5.required();
        var data5 = new HashMap<String, Object>();
        data5.put("key1", "value1");
        assertThat(schema5.required().sizeof(2).isValid(data5)).isFalse();

        var schema6 = v.map();
        var data6 = new HashMap<String, Object>();
        data6.put("key1", "value1");
        data6.put("key2", "value2");
        assertThat(schema6.required().sizeof(2).isValid(data6));

        var schema7 = v.map();
        Map<String, BaseSchema<String>> schemas7 = new HashMap<>();
        schemas7.put("firstName", v.string().required());
        schemas7.put("lastName", v.string().required());
        Map<String, Object> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertThat(schema7.shape(schemas7).isValid(human1)).isTrue();

        var schema8 = v.map();
        Map<String, BaseSchema<String>> schemas8 = new HashMap<>();
        schemas8.put("firstName", v.string().required());
        schemas8.put("lastName", v.string().required().contains("ab"));
        Map<String, Object> human2 = new HashMap<>();
        human2.put("firstName", "Nik");
        human2.put("lastName", "Pic");
        assertThat(schema8.shape(schemas8).isValid(human2)).isFalse();
    }
    @Test
    public void numberTest() {
        var schema = v.number();
        assertThat(schema.isValid(1)).isTrue();

        var schema2 = v.number();
        assertThat(schema2.isValid(null)).isTrue();

        var schema3 = v.number();
        assertThat(schema3.required().isValid(null)).isFalse();

        var schema4 = v.number();
        assertThat(schema4.positive().isValid(null)).isTrue();

        var schema5 = v.number();
        assertThat(schema5.positive().isValid(10)).isTrue();

        var schema6 = v.number();
        assertThat(schema6.required().positive().isValid(-10));

        var schema7 = v.number();
        assertThat(schema7.required().positive().isValid(0)).isFalse();

        var schema8 = v.number();
        assertThat(schema8.positive().range(3, 5).isValid(3)).isTrue();
        assertThat(schema8.positive().range(3, 5).isValid(4)).isTrue();
        assertThat(schema8.positive().range(3, 5).isValid(5)).isTrue();

        var schema9 = v.number();
        assertThat(schema9.positive().range(8, 10).isValid(6)).isFalse();
        assertThat(schema9.positive().range(3, 5).isValid(11)).isFalse();

        var schema10 = v.number();
        assertThat(schema10.range(-5, -3).isValid(-3)).isTrue();
        assertThat(schema10.range(-5, -3).isValid(-4)).isTrue();
        assertThat(schema10.range(-5, -3).isValid(-5)).isTrue();

        var schema11 = v.number();
        assertThat(schema11.range(-3, -5).isValid(-2)).isFalse();
        assertThat(schema11.range(-3, -5).isValid(-6)).isFalse();

        var schema12 = v.number();
        assertThat(schema12.positive().range(5, 3).isValid(4)).isFalse();
        assertThat(schema12.positive().range(5, 3).isValid(4)).isFalse();
        assertThat(schema12.positive().range(5, 3).isValid(5)).isFalse();
    }
    @Test
    public void stringTest() {
        var schema1 = v.string();
        assertThat(schema1.isValid("")).isTrue();

        var schema2 = v.string();
        assertThat(schema2.isValid(null)).isTrue();

        var schema3 = v.string();
        assertThat(schema3.required().isValid(null)).isFalse();

        var schema4 = v.string();
        assertThat(schema4.required().isValid("")).isFalse();

        var schema5 = v.string();
        assertThat(schema5.isValid("text")).isTrue();

        var schema6 = v.string();
        assertThat(schema6.contains("te").isValid("text")).isTrue();

        var schema7 = v.string();
        assertThat(schema7.contains("no").isValid("text")).isFalse();

        var schema8 = v.string();
        assertThat(schema8.minLength(10).isValid("abc")).isFalse();

        var schema9 = v.string();
        assertThat(schema9.minLength(3).isValid("abc")).isTrue();

        var schema10 = v.string();
        assertThat(schema10.minLength(10).minLength(3).isValid("abc")).isTrue();
    }
}

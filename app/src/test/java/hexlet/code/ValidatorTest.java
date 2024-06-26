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
        boolean actual = schema1.isValid(null);
        boolean expected = true;
        assertThat(actual).isEqualTo(expected);

        var schema2 = v.map();
        schema2.required();
        boolean actual2 = schema2.isValid(null);
        boolean expected2 = false;
        assertThat(actual2).isEqualTo(expected2);

        var schema3 = v.map();
        schema3.required();
        boolean actual3 = schema3.isValid(new HashMap<>());
        boolean expected3 = true;
        assertThat(actual3).isEqualTo(expected3);

        var schema4 = v.map();
        schema4.required();
        var data4 = new HashMap<String, String>();
        data4.put("key1", "value1");
        boolean actual4 = schema4.isValid(data4);
        boolean expected4 = true;
        assertThat(actual4).isEqualTo(expected4);

        var schema5 = v.map();
        schema5.required();
        var data5 = new HashMap<String, Object>();
        data5.put("key1", "value1");
        boolean actual5 = schema5.sizeof(2).isValid(data5);
        boolean expected5 = false;
        assertThat(actual5).isEqualTo(expected5);

        var schema6 = v.map();
        schema6.required();
        var data6 = new HashMap<String, Object>();
        data6.put("key1", "value1");
        data6.put("key2", "value2");
        boolean actual6 = schema6.sizeof(2).isValid(data6);
        boolean expected6 = true;
        assertThat(actual6).isEqualTo(expected6);

        var schema7 = v.map();
        Map<String, BaseSchema<String>> schemas7 = new HashMap<>();
        schemas7.put("firstName", v.string().required());
        schemas7.put("lastName", v.string().required());
        Map<String, Object> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        schema7.shape(schemas7);
        boolean actual7 =  schema1.isValid(human1);
        boolean expected7 = true;
        assertThat(actual7).isEqualTo(expected7);

        var schema8 = v.map();
        Map<String, BaseSchema<String>> schemas8 = new HashMap<>();
        schemas8.put("firstName", v.string().required());
        schemas8.put("lastName", v.string().required().contains("ab"));
        Map<String, Object> human2 = new HashMap<>();
        human2.put("firstName", "Nik");
        human2.put("lastName", "Pic");
        schema8.shape(schemas8);
        boolean actual8 =  schema8.isValid(human2);
        boolean expected8 = false;
        assertThat(actual8).isEqualTo(expected8);
    }
    @Test
    public void numberTest() {

        var schema1 = v.number();
        boolean actual1 = schema1.isValid(1);
        boolean expected1 = true;
        assertThat(actual1).isEqualTo(expected1);

        var schema2 = v.number();
        boolean actual2 = schema2.isValid(null);
        boolean expected2 = true;
        assertThat(actual2).isEqualTo(expected2);

        var schema3 = v.number();
        schema3.required();
        boolean actual3 = schema3.isValid(null);
        boolean expected3 = false;
        assertThat(actual3).isEqualTo(expected3);

        var schema4 = v.number();
        boolean actual4 = schema4.positive().isValid(null);
        boolean expected4 = true;
        assertThat(actual4).isEqualTo(expected4);

        var schema5 = v.number();
        schema5.required();
        boolean actual5 = schema5.positive().isValid(10);
        boolean expected5 = true;
        assertThat(actual5).isEqualTo(expected5);

        var schema6 = v.number();
        schema6.required();
        boolean actual6 = schema6.positive().isValid(-10);
        boolean expected6 = false;
        assertThat(actual6).isEqualTo(expected6);

        var schema7 = v.number();
        schema7.required();
        boolean actual7 = schema7.positive().isValid(0);
        boolean expected7 = false;
        assertThat(actual7).isEqualTo(expected7);

        var schema8 = v.number();
        schema8.required();
        boolean actual81 = schema8.positive().range(3, 5).isValid(3);
        boolean actual82 = schema8.positive().range(3, 5).isValid(4);
        boolean actual83 = schema8.positive().range(3, 5).isValid(5);
        boolean expected8 = true;
        assertThat(actual81).isEqualTo(expected8);
        assertThat(actual82).isEqualTo(expected8);
        assertThat(actual83).isEqualTo(expected8);

        var schema9 = v.number();
        schema9.required();
        boolean actual91 = schema9.positive().range(8, 10).isValid(6);
        boolean actual92 = schema9.positive().range(3, 5).isValid(11);
        boolean expected9 = false;
        assertThat(actual91).isEqualTo(expected9);
        assertThat(actual92).isEqualTo(expected9);

        var schema10 = v.number();
        schema10.required();
        boolean actual101 = schema10.range(-5, -3).isValid(-3);
        boolean actual102 = schema10.range(-5, -3).isValid(-4);
        boolean actual103 = schema10.range(-5, -3).isValid(-5);
        boolean expected10 = true;
        assertThat(actual101).isEqualTo(expected10);
        assertThat(actual102).isEqualTo(expected10);
        assertThat(actual103).isEqualTo(expected10);

        var schema11 = v.number();
        schema11.required();
        boolean actual111 = schema11.range(-3, -5).isValid(-2);
        boolean actual112 = schema11.range(-3, -5).isValid(-6);
        boolean expected11 = false;
        assertThat(actual111).isEqualTo(expected11);
        assertThat(actual112).isEqualTo(expected11);

        var schema12 = v.number();
        schema12.required();
        boolean actual121 = schema12.positive().range(5, 3).isValid(4);
        boolean actual122 = schema12.positive().range(5, 3).isValid(4);
        boolean actual123 = schema12.positive().range(5, 3).isValid(5);
        boolean expected12 = false;
        assertThat(actual121).isEqualTo(expected12);
        assertThat(actual122).isEqualTo(expected12);
        assertThat(actual123).isEqualTo(expected12);
    }
    @Test
    public void stringTest() {

        var schema1 = v.string();
        boolean actual1 = schema1.isValid("");
        boolean expected1 = true;
        assertThat(actual1).isEqualTo(expected1);

        var schema2 = v.string();
        boolean actual2 = schema2.isValid(null);
        boolean expected2 = true;
        assertThat(actual2).isEqualTo(expected2);

        var schema3 = v.string();
        schema3.required();
        boolean actual3 = schema3.isValid(null);
        boolean expected3 = false;
        assertThat(actual3).isEqualTo(expected3);

        var schema4 = v.string();
        schema4.required();
        boolean actual4 = schema4.isValid("");
        boolean expected4 = false;
        assertThat(actual4).isEqualTo(expected4);

        var schema5 = v.string();
        boolean actual5 = schema5.isValid("text");
        boolean expected5 = true;
        assertThat(actual5).isEqualTo(expected5);

        var schema6 = v.string();
        boolean actual6 = schema6.contains("te").isValid("text");
        boolean expected6 = true;
        assertThat(actual6).isEqualTo(expected6);

        var schema7 = v.string();
        boolean actual7 = schema7.contains("no").isValid("text");
        boolean expected7 = false;
        assertThat(actual7).isEqualTo(expected7);

        var schema8 = v.string();
        boolean actual8 = schema8.minLength(10).isValid("abc");
        boolean expected8 = false;
        assertThat(actual8).isEqualTo(expected8);

        var schema9 = v.string();
        boolean actual9 = schema9.minLength(3).isValid("abc");
        boolean expected9 = true;
        assertThat(actual9).isEqualTo(expected9);

        var schema10 = v.string();
        boolean actual10 = schema10.minLength(10).minLength(3).isValid("abc");
        boolean expected10 = true;
        assertThat(actual10).isEqualTo(expected10);
    }
}

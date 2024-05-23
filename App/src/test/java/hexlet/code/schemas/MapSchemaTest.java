package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


class MapSchemaTest {
    Validator validator;
    MapSchema schema;

    @BeforeEach
    public void createMapSchema() {
        validator = new Validator();
        schema = validator.map();
    }
    @Test
    public void test1() {
        boolean actual = schema.isValid(null);
        boolean expected = true;
        assertThat(actual).isEqualTo(expected);

    }
    @Test
    public void test2() {
        schema.required();
        boolean actual = schema.isValid(null);
        boolean expected = false;
        assertThat(actual).isEqualTo(expected);


    }
    @Test
    public void test3() {
        schema.required();
        boolean actual = schema.isValid(new HashMap<>());
        boolean expected = true;
        assertThat(actual).isEqualTo(expected);

    }
    @Test
    public void test4() {
        schema.required();
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        boolean actual = schema.isValid(data);
        boolean expected = true;
        assertThat(actual).isEqualTo(expected);

    }
    @Test
    public void test5() {
        schema.required();
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        boolean actual = schema.sizeOf(2).isValid(data);
        boolean expected = false;
        assertThat(actual).isEqualTo(expected);

    }
    @Test
    public void test6() {
        schema.required();
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        boolean actual = schema.sizeOf(2).isValid(data);
        boolean expected = true;
        assertThat(actual).isEqualTo(expected);

    }
    @Test
    public void test7() {
        Validator validator1 = new Validator();
        MapSchema schema1 = validator.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator1.string().required());
        schemas.put("lastName", validator1.string().required());
        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");

        schema1.shape(schemas);

        boolean actual =  schema1.isValid(human1);
        boolean expected = true;
        assertThat(actual).isEqualTo(expected);


    }


}

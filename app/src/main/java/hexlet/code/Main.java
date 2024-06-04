package hexlet.code;


import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;


import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String[] args) {






        Validator validator = new Validator();
        MapSchema schema = validator.map();


        Map<String, BaseSchema<?>> schemas2 = new HashMap<>();
        schemas2.put("firstName", validator.string().required());
        schemas2.put("age", validator.number().positive());

        Map human1 = new HashMap<String, Object>();
        human1.put("firstName", "abc");
        human1.put("age", -20);

        System.out.println(schema.shape(schemas2).isValid(human1));



    }





}

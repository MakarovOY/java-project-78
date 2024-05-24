package hexlet.code;


import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;

import hexlet.code.schemas.StringSchema;

import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String[] args) {

        StringSchema stringSchema = new StringSchema();
        stringSchema.required();


       // var v = new Validator();

       // var schema = v.map();
        Validator validator = new Validator();
        MapSchema schema = validator.map();




        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required());

        schema.shape(schemas);

        System.out.println(schema);



//
//        System.out.println( stringSchema.isValid(null));
//
//        NumberSchema schema = new NumberSchema();
//
//
//
//        System.out.println(schema.positive().isValid(null));
//
//        schema.required();
//        System.out.println(schema.isValid(null));
//        System.out.println(schema.isValid(10));
//        schema.range(5,10);
//        System.out.println(schema.isValid(11));







    }





}

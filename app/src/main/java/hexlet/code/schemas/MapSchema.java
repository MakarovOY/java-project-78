package hexlet.code.schemas;

import java.util.Map;



public class MapSchema extends BaseSchema<Map<String, ?>> {
    //поля:

    private boolean requiredFlag;
    private boolean sizeOfFlag;
    private int size;
    private Map<String, BaseSchema<?>> schemas;

   // методы


    @Override
    public boolean isValid(Map<String, ?>  value) {

        if (requiredFlag && value == null) {

            return false;

        }
        if (value == null) {
            return true;
        }

        if (sizeOfFlag && value.size() != size) {
            return false;
        }


        if (schemas != null) {
            for (Map.Entry<String, BaseSchema<?>> entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                Object valueToCheck = value.get(key);

                if (!isValidSchema(schema, valueToCheck)) {
                    return false;
                }
            }
        }

        return true;
    }

    private <T> boolean isValidSchema(BaseSchema<T> schema, Object valueToCheck) {

        try {
            return schema.isValid((T) valueToCheck);
        } catch (ClassCastException e) {
            return false;
        }

    }

    @Override
    public MapSchema required() {
        this.requiredFlag = true;
        return this;
    }
    public MapSchema sizeof(int sizeValue) {
        this.size = sizeValue;
        this.sizeOfFlag = true;


        return this;
    }
    public MapSchema shape(Map<String, BaseSchema<?>> schemasValue) {
        this.schemas = schemasValue;
        return this;
    }


}

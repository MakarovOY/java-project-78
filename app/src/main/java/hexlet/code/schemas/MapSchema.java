package hexlet.code.schemas;

import java.util.Map;

@SuppressWarnings("HiddenField")

public class MapSchema extends BaseSchema<Map> {

    private boolean requiredFlag;
    private boolean sizeOfFlag;
    private int size;
    private Map<String, BaseSchema> schemas;



    @Override
    public boolean isValid(Map value) {

        if (requiredFlag && value == null) {

            return false;

        }

        if (sizeOfFlag && value.size() != size) {
            return false;
        }


        if (schemas != null) {
            var set = schemas.entrySet();
            for (var s: set) {

                // схема:
                var schema = s.getValue();

                // значение по ключу, которое надо проверить:
                var valueToCheck = value.get(s.getKey());

                // вызов метода у полученной схемы и передача значения на проверку:
                 if (!schema.isValid(valueToCheck)){
                     return false;
                 }

            }

        }

        return true;
    }

    @Override
    public BaseSchema required() {
        this.requiredFlag = true;
        return this;
    }
    public MapSchema sizeOf(int sizeValue) {
        this.size = sizeValue;
        this.sizeOfFlag = true;


        return this;
    }
    public MapSchema shape(Map<String, BaseSchema> schemasValue) {
        this.schemas = schemasValue;
        return this;
    }


}

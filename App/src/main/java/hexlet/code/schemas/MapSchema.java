package hexlet.code.schemas;

import java.util.Map;

@SuppressWarnings("HiddenField")

public class MapSchema extends BaseSchema<Map> {

    private boolean requiredFlag;
    private boolean sizeOfFlag;
    private int size;
    private Map<String, BaseSchema<String>> schemas;



    @Override
    boolean isValid(Map value) {

        if (requiredFlag && value == null) {

            return false;

        }

        if (sizeOfFlag && value.size() != size) {
            return false;
        }


        if (schemas != null) {
            var set = schemas.entrySet();
            for (var s: set) {
                var v = s.getValue();

                // значение по ключу:
                var x = (String) value.get(s.getKey());

                v.isValid(x);

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
    public MapSchema shape(Map<String, BaseSchema<String>> schemasValue) {
        this.schemas = schemasValue;
        return this;
    }
}

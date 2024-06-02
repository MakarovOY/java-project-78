package hexlet.code.schemas;

import java.util.Map;

@SuppressWarnings("HiddenField")

public class MapSchema extends BaseSchema<Map> {

    private boolean requiredFlag;
    private boolean sizeOfFlag;
    private int size;
    private Map<String, BaseSchema<?>> schemas;



    @Override
    public boolean isValid(Map  value) {

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

                if (!schema.isValid(valueToCheck)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public MapSchema required() {
        this.requiredFlag = true;
        return this;
    }
    public MapSchema sizeOf(int sizeValue) {
        this.size = sizeValue;
        this.sizeOfFlag = true;


        return this;
    }
    public MapSchema shape(Map<String, BaseSchema<?>> schemasValue) {
        this.schemas = schemasValue;
        return this;
    }


}

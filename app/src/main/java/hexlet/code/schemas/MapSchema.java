package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;


public final class MapSchema extends BaseSchema<Map<String, ?>> {
    public MapSchema sizeof(int sizeValue) {
        Predicate<Map<String, ?>> sizeof = v -> v.size() == sizeValue;
        addCheck("sizeof", sizeof);
        return this;
    }
    @Override
    public MapSchema required() {
        Predicate<Map<String, ?>> required = v -> v != null;
        addCheck("required", required);
        return this;
    }
    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemasValue) {
        var entries = schemasValue.entrySet();
        Predicate<Map<String, ?>> shape = v -> {
            for (var e : entries) {
                if (!e.getValue().isValid((T) v.get(e.getKey()))) {
                    return false;
                }
            }
            return true;
        };
        addCheck("shape", shape);
        return this;
    }

}

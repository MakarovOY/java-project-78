package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    /**
     *  d0nt touch it!
     */
    public  boolean isValid(T value) {
        for (Predicate<T> schemaToTest : checks.values()) {
            if (!schemaToTest.test(value)) {
                return false;
            }
        }
        return true;
    }
    public abstract BaseSchema<T> required();
}

package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema minLength(int minLengthValue) {
        Predicate<String> minLength = v -> minLengthValue <= v.length();
        addCheck("minLength", minLength);
        return this;
    }
    public StringSchema contains(String substringValue) {
        Predicate<String> contains = v -> v.contains(substringValue);
        addCheck("contains", contains);
        return this;
    }
    @Override public StringSchema required() {
        Predicate<String> required = v -> (v != null && v.length() > 0);
        addCheck("required", required);
        return this;
    }
}

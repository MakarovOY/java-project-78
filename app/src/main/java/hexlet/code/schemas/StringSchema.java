package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema minLength(int minLengthValue) {
        addCheck("minLength", v -> minLengthValue <= v.length());
        return this;
    }

    public StringSchema contains(String substringValue) {
        addCheck("contains", v -> v.contains(substringValue));
        return this;
    }

    @Override public StringSchema required() {
        addCheck("required", v -> v != null && v.length() > 0);
        return this;
    }
}

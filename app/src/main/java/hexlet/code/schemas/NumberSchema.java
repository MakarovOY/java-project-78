package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema positive() {
        addCheck("positive", v -> v == null || v > 0);
        return this;
    }

    public NumberSchema range(int minRangeValue, int maxRangeValue) {
        addCheck("range", v -> v >= minRangeValue && v <= maxRangeValue);
        return this;
    }

    @Override
    public NumberSchema required() {
        addCheck("required", v -> v != null);
        return this;
    }
}

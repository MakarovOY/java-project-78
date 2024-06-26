package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema positive() {
        Predicate<Integer> positive = v ->  {
            if (checks.get("required") == null && v == null) {
                return true;
            }
            if (v != null) {
                return v > 0;
            }
            return true;
        };
        addCheck("positive", positive);
        return this;
    }
    public NumberSchema range(int minRangeValue, int maxRangeValue) {
        Predicate<Integer> range = v -> (v >= minRangeValue && v <= maxRangeValue);
        addCheck("range", range);
        return this;
    }
    @Override
    public BaseSchema<Integer> required() {
        Predicate<Integer> required = v -> v != null;
        addCheck("required", required);
        return null;
    }
}

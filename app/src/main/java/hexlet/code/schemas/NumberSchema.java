package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema positive() {
        Predicate<Integer> positive = v ->  {
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
    public NumberSchema required() {
        Predicate<Integer> required = v -> v != null;
        addCheck("required", required);
        return this;
    }
}

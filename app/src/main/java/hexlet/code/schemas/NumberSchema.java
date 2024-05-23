package hexlet.code.schemas;

//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
@SuppressWarnings("HiddenField")
public class NumberSchema extends BaseSchema<Integer> {

    private int minRange;
    private int maxRange;

    private boolean flagRequired;
    private boolean flagPositiveNumber;




    public NumberSchema positive() {
        this.flagPositiveNumber = true;
        return this;
    }
    public NumberSchema range(int minRangeValue, int maxRangeValue) {
        this.minRange = minRangeValue;
        this.maxRange = maxRangeValue;

        return this;
    }


    @Override
    public BaseSchema required() {
        this.flagRequired = true;
        return this;
    }


    @Override
    public boolean isValid(Integer value) {

        if (maxRange != 0 && minRange != 0) {
            if (value < minRange || value > maxRange) {
                return false;
            }
        }
        if (value == null) {
            if (flagRequired) {
                return false;
            }
            if (flagPositiveNumber) {
                return true;
            }
        }

        if (flagPositiveNumber && value <= 0) {
            return false;
        }

        return true;
    }

}

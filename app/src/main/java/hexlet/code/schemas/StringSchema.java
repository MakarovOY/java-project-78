package hexlet.code.schemas;
@SuppressWarnings("HiddenField")
public class StringSchema extends BaseSchema<String> {
    private int minLength;
    private boolean flagRequired;
    private String substring;



    public StringSchema minLength(int minLengthVlue) {
        this.minLength = minLengthVlue;
        return this;
    }

    public StringSchema contains(String substringValue) {
        this.substring = substringValue;
        return this;
    }


    @Override
    public BaseSchema required() {

        flagRequired = true;


        return this;

    }
    @Override
    public boolean isValid(String value) {

        if (value != null) {

            if (substring != null && !value.contains(substring)) {

                return false;

            }

            if (minLength > 0 && value.length() < minLength) {

                return false;
            }
        }
        if (flagRequired) {

            return false;

        }

        return true;

    }


}

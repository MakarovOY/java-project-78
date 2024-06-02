package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    private int minLength;
    private boolean flagRequired;
    private String substring;



    public StringSchema minLength(int minLengthValue) {
        this.minLength = minLengthValue;
        return this;
    }

    public StringSchema contains(String substringValue) {
        this.substring = substringValue;
        return this;
    }


    @Override
    public StringSchema required() {

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

            return value != null && value.length() != 0 ? true : false;

        }


        return true;

    }


}

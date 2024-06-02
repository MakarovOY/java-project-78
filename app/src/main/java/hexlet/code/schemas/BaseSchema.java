package hexlet.code.schemas;

public abstract class BaseSchema<T> {

    public abstract boolean isValid(T value);
    public abstract BaseSchema<T> required();



}

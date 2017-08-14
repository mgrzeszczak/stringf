package github.com.mgrzeszczak.stringf;

public interface Formatter<T> {

    String format(T t);
    Class<? extends T> target();

}

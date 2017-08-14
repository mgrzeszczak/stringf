package github.com.mgrzeszczak.stringf;

final class Component<T> {

    private final Formatter<T> formatter;
    private final T component;

    private Component(T component, Formatter<T> formatter) {
        this.component = component;
        this.formatter = formatter;
    }

    private Component(T component) {
        this.component = component;
        this.formatter = null;
    }

    static <T> Component<T> of(T component, Formatter<T> formatter) {
        return new Component<T>(component, formatter);
    }

    static <T> Component<T> of(T component) {
        return new Component<T>(component);
    }

    String format(Formatter<T> global) {
        return formatter != null ? formatter.format(component) :
                global != null ? global.format(component) : component.toString();
    }

    Class<?> type() {
        return this.component.getClass();
    }

    boolean exactMatch(Formatter<?> formatter) {
        return component.getClass().equals(formatter.target());
    }

    boolean matches(Formatter<?> formatter) {
        return formatter.target().isAssignableFrom(component.getClass());
    }

}

package github.com.mgrzeszczak.stringf;

import github.com.mgrzeszczak.stringf.view.Appender;
import github.com.mgrzeszczak.stringf.view.FormatConsumer;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Stringf implements Appender {

    private final static String DEFAULT_SEPARATOR = " ";
    private final List<Component<?>> components;
    private final List<Formatter<?>> formatters;

    private Stringf() {
        components = new LinkedList<>();
        formatters = new LinkedList<>();
    }

    public static Appender stringf() {
        return new Stringf();
    }

    @Override
    public <T> Appender $(T var) {
        add(Component.of(var));
        return this;
    }

    @Override
    public <T> Appender $(T var, Formatter<T> format) {
        add(Component.of(var, format));
        return this;
    }

    @Override
    public <T extends Number> Appender $(T var, String pattern) {
        add(Component.of(var, new NumberFormatter(pattern)));
        return this;
    }

    @Override
    public <T extends Number> Appender $(T var, NumberFormat format) {
        return this;
    }

    @Override
    public Appender $(Date var, String pattern) {
        add(Component.of(var, new DateFormatter(pattern)));
        return this;
    }

    @Override
    public Appender $(Date var, DateFormat format) {
        add(Component.of(var, new DateFormatter(format)));
        return this;
    }

    @Override
    public Appender $(TemporalAccessor date, String pattern) {
        add(Component.of(date, new TemporalFormatter(pattern)));
        return this;
    }

    @Override
    public Appender $(TemporalAccessor date, DateTimeFormatter formatter) {
        add(Component.of(date, new TemporalFormatter(formatter)));
        return this;
    }

    @Override
    public FormatConsumer dates(DateFormat dateFormat) {
        this.formatters.add(new DateFormatter(dateFormat));
        return this;
    }

    @Override
    public FormatConsumer numbers(NumberFormat numberFormat) {
        this.formatters.add(new NumberFormatter(numberFormat));
        return this;
    }

    @Override
    public FormatConsumer temporals(DateTimeFormatter temporalFormat) {
        this.formatters.add(new TemporalFormatter(temporalFormat));
        return this;
    }

    @Override
    public FormatConsumer numbers(String pattern) {
        this.formatters.add(new NumberFormatter(pattern));
        return this;
    }

    @Override
    public FormatConsumer dates(String pattern) {
        this.formatters.add(new DateFormatter(pattern));
        return this;
    }

    @Override
    public FormatConsumer temporals(String pattern) {
        this.formatters.add(new TemporalFormatter(pattern));
        return this;
    }

    @Override
    public FormatConsumer integers(String pattern) {
        this.formatters.add(new NumberFormatter(pattern, Integer.class));
        this.formatters.add(new NumberFormatter(pattern, int.class));
        this.formatters.add(new NumberFormatter(pattern, Short.class));
        this.formatters.add(new NumberFormatter(pattern, short.class));
        this.formatters.add(new NumberFormatter(pattern, Long.class));
        this.formatters.add(new NumberFormatter(pattern, long.class));
        return this;
    }

    @Override
    public FormatConsumer decimals(String pattern) {
        this.formatters.add(new NumberFormatter(pattern, Double.class));
        this.formatters.add(new NumberFormatter(pattern, double.class));
        this.formatters.add(new NumberFormatter(pattern, Float.class));
        this.formatters.add(new NumberFormatter(pattern, float.class));
        return this;
    }

    @Override
    public FormatConsumer with(Formatter<?>... formatters) {
        this.formatters.addAll(Arrays.asList(formatters));
        return this;
    }

    @Override
    public String str() {
        return str(DEFAULT_SEPARATOR);
    }

    @Override
    public String str(String separator) {
        return join(components.stream(), separator);
    }

    private void add(Component<?> component) {
        components.add(component);
    }

    private String join(Stream<Component<?>> stream, String separator) {
        return stream.map(this::format)
                .collect(Collectors.joining(separator));
    }

    private String format(Component<?> component) {
        Formatter globalFormatter = formatters.stream()
                .filter(component::exactMatch)
                .findAny()
                .orElseGet(() -> getMatchingFormatter(component));
        return component.format(globalFormatter);
    }

    private Formatter<?> getMatchingFormatter(Component component) {
        return formatters.stream()
                .filter(component::matches)
                .findAny()
                .orElse(null);
    }
}

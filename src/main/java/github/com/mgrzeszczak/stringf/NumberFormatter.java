package github.com.mgrzeszczak.stringf;

import java.text.DecimalFormat;
import java.text.NumberFormat;

final class NumberFormatter implements Formatter<Number> {

    private final NumberFormat format;
    private final Class<? extends Number> targetClass;

    NumberFormatter(String format) {
        this.format = new DecimalFormat(format);
        this.targetClass = Number.class;
    }

    NumberFormatter(NumberFormat format) {
        this.format = format;
        this.targetClass = Number.class;
    }

    NumberFormatter(String format, Class<? extends Number> targetClass) {
        this.format = new DecimalFormat(format);
        this.targetClass = targetClass;
    }

    NumberFormatter(NumberFormat format, Class<? extends Number> targetClass) {
        this.format = format;
        this.targetClass = targetClass;
    }

    @Override
    public String format(Number number) {
        return format.format(number);
    }

    @Override
    public Class<? extends Number> target() {
        return targetClass;
    }
}

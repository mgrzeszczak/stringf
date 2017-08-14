package github.com.mgrzeszczak.stringf;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

final class TemporalFormatter implements Formatter<TemporalAccessor> {

    private final DateTimeFormatter formatter;

    TemporalFormatter(String pattern) {
        formatter = DateTimeFormatter.ofPattern(pattern);
    }

    TemporalFormatter(DateTimeFormatter formatter) {
        DecimalFormat format;
        this.formatter = formatter;
    }

    @Override
    public String format(TemporalAccessor temporalAccessor) {
        return formatter.format(temporalAccessor);
    }

    @Override
    public Class<TemporalAccessor> target() {
        return TemporalAccessor.class;
    }
}

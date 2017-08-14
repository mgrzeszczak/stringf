package github.com.mgrzeszczak.stringf;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

final class DateFormatter implements Formatter<Date> {

    private final DateFormat format;

    DateFormatter(DateFormat format) {
        this.format = format;
    }

    DateFormatter(String pattern) {
        this.format = new SimpleDateFormat(pattern);
    }

    @Override
    public String format(Date date) {
        return format.format(date);
    }

    @Override
    public Class<Date> target() {
        return Date.class;
    }

}

package github.com.mgrzeszczak.stringf.view;

import github.com.mgrzeszczak.stringf.Formatter;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;

public interface FormatConsumer extends Interpolator {

    FormatConsumer dates(DateFormat dateFormat);

    FormatConsumer numbers(NumberFormat numberFormat);

    FormatConsumer temporals(DateTimeFormatter temporalFormat);

    FormatConsumer dates(String pattern);

    FormatConsumer numbers(String pattern);

    FormatConsumer temporals(String pattern);

    FormatConsumer integers(String pattern);

    FormatConsumer decimals(String pattern);

    FormatConsumer with(Formatter<?>... formatters);

}

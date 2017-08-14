package github.com.mgrzeszczak.stringf.view;

import github.com.mgrzeszczak.stringf.Formatter;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public interface Appender extends FormatConsumer {

    <T> Appender $(T var);

    <T> Appender $(T var, Formatter<T> format);

    <T extends Number> Appender $(T var, String pattern);

    <T extends Number> Appender $(T var, NumberFormat format);

    Appender $(Date var, String pattern);

    Appender $(Date var, DateFormat format);

    Appender $(TemporalAccessor date, String pattern);

    Appender $(TemporalAccessor date, DateTimeFormatter formatter);

}

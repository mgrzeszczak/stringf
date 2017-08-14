package github.com.mgrzeszczak.stringf

import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime
import java.time.ZoneOffset

import static github.com.mgrzeszczak.stringf.Stringf.stringf

@Unroll
class StringfSpec extends Specification {

    private final static LocalDateTime LOCAL_DATETIME_1970_01_01 = LocalDateTime.of(1970, 1, 1, 0, 0);
    private final static Date DATE_1970_01_01 = Date.from(LOCAL_DATETIME_1970_01_01.toInstant(ZoneOffset.of("+0")));
    private final static int INT_123 = 123;
    private final static double DOUBLE_123 = 123d;
    private final static float FLOAT_123 = 123f;
    private final static long LONG_123 = 123l;
    private final static short SHORT_123 = 123;
    private final static String STR_HELLO = "hello";
    private final static String STR_WORLD = "world";

    def "should format expressions"() {
        expect:
            expression.str() == result
        where:
            expression                                                                                  || result
            stringf().$(STR_HELLO).$(STR_WORLD)                                                         || "hello world"
            stringf().$(DATE_1970_01_01, "yyyy-MM-dd")                                                  || "1970-01-01"
            stringf().$(SHORT_123)                                                                      || "123"
            stringf().$(INT_123)                                                                        || "123"
            stringf().$(LONG_123)                                                                       || "123"
            stringf().$(FLOAT_123)                                                                      || "123.0"
            stringf().$(DOUBLE_123)                                                                     || "123.0"
            stringf().$(SHORT_123, "###.000")                                                           || "123.000"
            stringf().$(INT_123, "###.000")                                                             || "123.000"
            stringf().$(LONG_123, "###.000")                                                            || "123.000"
            stringf().$(FLOAT_123, "###.000")                                                           || "123.000"
            stringf().$(DOUBLE_123, "###.000")                                                          || "123.000"
            stringf().$(SHORT_123).$(INT_123).$(LONG_123).$(FLOAT_123).$(DOUBLE_123).numbers("###.000") || "123.000 123.000 123.000 123.000 123.000"
            stringf().$(DATE_1970_01_01, "yyyy-MM-dd")                                                  || "1970-01-01"
            stringf().$(new Person("Jon", "Snow"))                                                      || "Jon Snow"
            stringf().$(new Person("Jon", "Snow"), new PersonFormatter())                               || "SNOW jon"

    }

}

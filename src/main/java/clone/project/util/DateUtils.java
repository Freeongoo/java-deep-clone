package clone.project.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Useful for convert Date to Local and vice versa
 */
public class DateUtils {

    public static final String DATE_FORMAT_ISO = "yyyy-MM-dd'T'HH:mm:ss"; // ISO 8601

	
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDateFromUTC(Date date) {
        return asDateFromUTC(asLocalDateTime(date));
    }

    public static Date asDateFromUTC(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDateFromUTC(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneOffset.UTC).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime getBeginCurrDay(LocalDateTime localDateTime) {
        return localDateTime.withHour(0).withMinute(0).withSecond(0);
    }

    public static LocalDateTime getBeginCurrDay(Date date) {
        return getBeginCurrDay(asLocalDateTime(date));
    }

    public static Date getBeginCurrDayAsDate(Date date) {
        return asDate(getBeginCurrDay(date));
    }

    public static LocalDateTime getEndCurrDay(LocalDateTime localDateTime) {
        return localDateTime.withHour(23).withMinute(59).withSecond(59);
    }

    public static LocalDateTime getEndCurrDay(Date date) {
        return getEndCurrDay(asLocalDateTime(date));
    }

    public static Date getEndCurrDayAsDate(Date date) {
        return asDate(getEndCurrDay(date));
    }

    public static LocalDateTime getBeginNextDay(LocalDateTime localDateTime) {
        return localDateTime.withHour(0).withMinute(0).withSecond(0).plusDays(1);
    }

    public static LocalDateTime getBeginNextDay(Date date) {
        return getBeginNextDay(asLocalDateTime(date));
    }

    public static LocalDateTime getBeginPreviousDay(Date date) {
        return asLocalDateTime(date).withHour(0).withMinute(0).withSecond(0).minusDays(1);
    }

    public static Date getBeginPreviousDayAsDate(Date date) {
        return asDate(getBeginPreviousDay(date));
    }

    public static Date getBeginNextDayAsDate(Date date) {
        return asDate(getBeginNextDay(date));
    }

    public static Date parse(String dateStr, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateStr);
    }

    public static Date parseISO(String dateStr) throws ParseException {
        return parse(dateStr, DATE_FORMAT_ISO);
    }

    public static Long getCountDaysBetween(Date dateFrom, Date dateTo) {
        if (dateFrom == null || dateTo == null) return null;

        LocalDate localFrom = DateUtils.asLocalDate(dateFrom);
        LocalDate localTo = DateUtils.asLocalDate(dateTo);

        return ChronoUnit.DAYS.between(localFrom, localTo);
    }
}

package ch.ricpr23.samples.database.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	public static LocalDateTime getLocalDateTime(long millis) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
	}
	
	public static LocalDate getLocalDate(Date date) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalDate addDays(LocalDate startDate, int numDays) {
		return startDate.plus(numDays, ChronoUnit.DAYS);
	}

	public static LocalDate addMonths(LocalDate startDate, int numMonths) {
		return startDate.plus(numMonths, ChronoUnit.MONTHS);
	}

	public static LocalDate getLocalDate(int year, int month, int day) {
		return LocalDate.of(year, month, day);
	}
	
	public static int getWeekOfYear(LocalDate date) {
		return date.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
	}
	
	public static boolean isNewDay(LocalDate d1, LocalDate d2) {
		return d2.getDayOfYear() != d1.getDayOfYear();
	}
	
	public static boolean isNewWeek(LocalDate d1, LocalDate d2) {
		return getWeekOfYear(d2) != getWeekOfYear(d1);
	}
	
	public static boolean isNewMonth(LocalDate d1, LocalDate d2) {
		return d2.getMonth() != d1.getMonth();
	}
	
	public static boolean isNewQuarter(LocalDate d1, LocalDate d2) {
		return d2.getMonth() != d1.getMonth() && 
				(d2.getMonth() == Month.JANUARY || d2.getMonth() == Month.APRIL || 
				 d2.getMonth() == Month.JULY || d2.getMonth() == Month.OCTOBER);
	}
	
	public static boolean isNewSemester(LocalDate d1, LocalDate d2) {
		return d2.getMonth() != d1.getMonth() && (d2.getMonth() == Month.JANUARY || d2.getMonth() == Month.JULY);
	}
	
	public static boolean isNewYear(LocalDate d1, LocalDate d2) {
		return d2.getYear() != d1.getYear() && d2.getMonth() == Month.JANUARY;
	}
}

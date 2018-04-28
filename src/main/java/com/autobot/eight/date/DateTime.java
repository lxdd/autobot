package com.autobot.eight.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoField;

public class DateTime {

	public static void main(String[] args) {
		localDateTime();
	}

	/**
	 * LocalDate该类的实例是一个不可变对象，它只提供了简单的日期，并不含当天的时间信息。另外，它也不附带任何与时区相关的信息。
	 * 你可以通过静态工厂方法of创建一个LocalDate实例。LocalDate实例提供了多种方法来 读取常用的值，比如年份、月份、星期几等，如下所示。
	 */
	public static void localDate() {

		LocalDate date = LocalDate.of(2014, 2, 18);
		int year = date.getYear();
		Month month = date.getMonth();
		int day = date.getDayOfMonth();
		DayOfWeek dw = date.getDayOfWeek();
		int len = date.lengthOfMonth();
		boolean leap = date.isLeapYear();

		// 使用工厂方法从系统时钟中获取当前的日期
		LocalDate today = LocalDate.now();

		// TemporalField是一个接口，它定义了如何访问temporal对象某个字段的值。
		// ChronoField枚举实现了这一接口，所以你可以很方便地使用get方法得到枚举元素的值
		int year1 = date.get(ChronoField.YEAR);
		int month1 = date.get(ChronoField.MONTH_OF_YEAR);
		int day1 = date.get(ChronoField.DAY_OF_MONTH);

	}

	/**
	 * 你可以使用of重载的 两个工厂方法创建LocalTime的实例。第一个重载函数接收小时和分钟，第二个重载函数同时还接收秒。
	 */
	private static void localTime() {

		LocalTime time = LocalTime.of(13, 45, 20);
		int hour = time.getHour();
		int minute = time.getMinute();
		int second = time.getSecond();
	}

	/**
	 * LocalDate和LocalTime都可以通过解析代表它们的字符串创建。使用静态方法parse;
	 * 你可以向parse方法传递一个DateTimeFormatter。该类的实例定义了如何格式化一个日
	 * 期或者时间对象。正如我们之前所介绍的，它是替换老版java.util.DateFormat的推荐替代 品。
	 */
	private static void parse() {

		LocalDate date = LocalDate.parse("2014-03-18");
		LocalTime time = LocalTime.parse("13:45:20");

	}

	/**
	 * 是LocalDate和LocalTime的合体。它同时表示了日期 和时间，但不带有时区信息，你可以直接创建，也可以通过合并日期和时间对象构造;
	 * 
	 * 注意，通过它们各自的atTime或者atDate方法，向LocalDate传递一个时间对象，或者向LocalTime传递一个日期对象的方式，你可以创建一个LocalDateTime对象。
	 * 你也可以使用toLocalDate或者toLocalTime方法，从LocalDateTime中提取LocalDate或者LocalTime 组件：
	 */
	private static void localDateTime() {

		LocalDate date = LocalDate.parse("2014-03-18");
		LocalTime time = LocalTime.parse("13:45:20");

		// 2014-03-18T13:45:20
		LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
		LocalDateTime dt2 = LocalDateTime.of(date, time);
		LocalDateTime dt3 = date.atTime(13, 45, 20);
		LocalDateTime dt4 = date.atTime(time);
		LocalDateTime dt5 = time.atDate(date);

		// 你也可以使用toLocalDate或者toLocalTime方法，从LocalDateTime中提取LocalDate或者LocalTime 组件
		LocalDate date1 = dt1.toLocalDate();
		LocalTime time1 = dt1.toLocalTime();

	}



}

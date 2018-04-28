package com.autobot.eight.optional;

import java.util.Optional;

public class OptionalDemo {

	public static void main(String[] args) {

		// Optional<Insurance> optOptional = Optional.ofNullable(new Insurance());
		// optOptional.map(Insurance::getName);
		//
		// Optional<Insurance> optInsurance = Optional.ofNullable(new Insurance());
		// Optional<String> name = optInsurance.map(Insurance::getName);
		//
//		Optional<Person> optPerson = Optional.of(new Person());
		// Optional<String> name =
		// optPerson.map(Person::getCar).map(Car::getInsurance).map(Insurance::getName);

//		Optional<Person> optOptional = Optional.ofNullable(new Person());
//		System.out.println(getCarInsuranceName(optPerson));
//
//		Insurance insurance = null;
		// if (insurance != null && "CambridgeInsurance".equals(insurance.getName())) {
		// System.out.println("ok");
		// }

		// Optional<Insurance> optInsurance = null;
		// optInsurance.filter(insurance ->
		// "CambridgeInsurance".equals(insurance.getName()))
		// .ifPresent(x -> System.out.println("ok"));
		
		Optional<String> someNull = Optional.ofNullable(null);
		
		System.out.println(someNull.isPresent());
		

	}

	// public String getInsuranceName(Person person) {
	// person.getCar().get().getInsurance().get().getName();
	// }

	/**
	 * 最便宜的保险公司
	 * 
	 * @param person
	 * @param car
	 * @return
	 */
	public Insurance findCheapestInsurance(Person person, Car car) {

		Insurance cheapestCompany = null;
		// 不同的保险公司提供的查询服务
		// 对比所有数据
		return cheapestCompany;
	}

	/**
	 * null-安全的版本
	 * 
	 * @param person
	 * @param car
	 * @return
	 */
	public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {

		if (person.isPresent() && car.isPresent()) {
			return Optional.of(findCheapestInsurance(person.get(), car.get()));
		} else {
			return Optional.empty();
		}
	}

	public Optional<Insurance> nullSafeFindCheapestInsurance1(Optional<Person> person, Optional<Car> car) {
		return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
	}

	/**
	 * 使用Optional获取car的Insurance名称； 如果Optional的结果值为空，设置默认值
	 * 
	 * @param person
	 * @return
	 */
	public static String getCarInsuranceName(Optional<Person> person) {
		return person.flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName).orElse("Unknown");
	}

	/**
	 * 将String转换为Integer，并返回一个Optional对象
	 * 
	 * @param s
	 * @return
	 */
	public static Optional<Integer> stringToInt(String s) {
		try {
			return Optional.of(Integer.parseInt(s));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}

	/**
	 * 返回一个字符串的长度
	 * 
	 * @param str
	 * @return
	 */
	public static int length(String str) {

		return Optional.ofNullable(str).map(String::length).orElse(0);

	}

}

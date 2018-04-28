package com.autobot.eight.optional;

import java.util.Optional;

/**
 * 人可能有车，也可能没有车，因此将这个字段声明为Optional
 * 
 * @author li_xiaodong
 * @date 2018年4月4日
 */
public class Person {

	private Optional<Car> car;

	public Optional<Car> getCar() {
		return car;
	}

	public void setCar(Optional<Car> car) {
		this.car = car;
	}

}

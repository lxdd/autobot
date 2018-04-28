package com.autobot.eight.optional;

import java.util.Optional;

/**
 * 车可能进行了保险，也可能没有保险，所以将这个字段声明为Optional
 * 
 * @author li_xiaodong
 * @date 2018年4月4日
 */
public class Car {

	private Optional<Insurance> insurance;

	public Optional<Insurance> getInsurance() {
		return insurance;
	}

	public void setInsurance(Optional<Insurance> insurance) {
		this.insurance = insurance;
	}

}

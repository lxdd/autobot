package com.autobot.eight.optional;

/**
 * 保险公司必须有名字
 * 
 * 我们看到insurance公司的名称被声明成String类型，而不是Optional<String>，这非常清楚地表明声明为insurance公司的类型必须提供公司名称。
 * 使用这种方式，一旦解引用insurance公司名称时发生NullPointerException，你就能非常确定地知道出错
 * 的原因，不再需要为其添加null的检查，因为null的检查只会掩盖问题，并未真正地修复问题。
 * insurance公司必须有个名字，所以，如果你遇到一个公司没有名称，你需要调查你的数据出了 什么问题，而不应该再添加一段代码，将这个问题隐藏。
 * 
 * @author li_xiaodong
 * @date 2018年4月4日
 */
public class Insurance {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

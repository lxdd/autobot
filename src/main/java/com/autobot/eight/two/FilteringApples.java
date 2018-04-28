package com.autobot.eight.two;

import java.util.*;

public class FilteringApples {

	/**
	 * @param args
	 */
	public static void main(String... args) {

		List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));

		// [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
		List<Apple> greenApples = filterApplesByColor(inventory, "green");
		System.out.println(greenApples);

		// [Apple{color='red', weight=120}]
		List<Apple> redApples = filterApplesByColor(inventory, "red");
		System.out.println(redApples);

		// [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
		List<Apple> greenApples2 = filter(inventory, new AppleColorPredicate());
		System.out.println(greenApples2);

		// [Apple{color='green', weight=155}]
		List<Apple> heavyApples = filter(inventory, new AppleWeightPredicate());
		System.out.println(heavyApples);

		// []
		List<Apple> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
		System.out.println(redAndHeavyApples);

		// 2.3.2 第五次尝试：使用匿名类
		// 但匿名类还是不够好。第一，它往往很笨重，因为它占用了很多空间;第二，很多程序员觉得它用起来很让人费解。
		List<Apple> redApples5 = filter(inventory, new ApplePredicate() {
			public boolean test(Apple apple) {
				return "red".equals(apple.getColor());
			}
		});
		System.out.println(redApples5);

		// 2.3.3 第六次尝试：使用 Lambda 表达式
		List<Apple> redApples6 = filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));
		
		//2.3.4 第七次尝试：将 List 类型抽象化
		List<Integer> numbers = Arrays.asList(new Integer(80),new Integer(20));
		List<Integer> evenNumbers7 = filter(numbers, (Integer i) -> i % 2 == 0);

	}

	// 2.1 应对不断变化的需求 begin

	/**
	 * 2.1.1 初试牛刀：筛选绿苹果 ... 突出显示的行就是筛选绿苹果所需的条件。但是现在农民改主意了，他还想要筛选红苹果。
	 * 你该怎么做呢？简单的解决办法就是复制这个方法，把名字改成filterRedApples，然后更改
	 * if条件来匹配红苹果。然而，要是农民想要筛选多种颜色：浅绿色、暗红色、黄色等，这种方法
	 * 就应付不了了。一个良好的原则是在编写类似的代码之后，尝试将其抽象化。
	 * 
	 * @param inventory
	 * @return
	 */
	public static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if ("green".equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}

	/**
	 * 2.1.2 再展身手：把颜色作为参数 给方法加一个参数，把颜色变成参数，这样就能灵活地适应变化了..
	 * 让我们把例子再弄得复杂一点儿。这位农民又跑回来和你说：“要是能区分 轻的苹果和重的苹果就太好了。重的苹果一般是重量大于150克。”
	 * 
	 * @param inventory
	 * @param color
	 * @return
	 */
	public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getColor().equals(color)) {
				result.add(apple);
			}
		}
		return result;
	}

	/**
	 * 2.1.2 用另一个参 数来应对不同的重量
	 * 
	 * 解决方案不错，但是请注意，你复制了大部分的代码来实现遍历库存，并对每个苹果应用筛 选条件。这有点儿令人失望，因为它打破了DRY（Don’t Repeat
	 * Yourself，不要重复自己）的软件 工程原则。如果你想要改变筛选遍历方式来提升性能呢？那就得修改所有方法的实现，而不是只
	 * 改一个。从工程工作量的角度来看，这代价太大了。
	 * 
	 * 你可以将颜色和重量结合为一个方法，称为filter。不过就算这样，你还是需要一种方式
	 * 来区分想要筛选哪个属性。你可以加上一个标志来区分对颜色和重量的查询（但绝不要这样做！ 我们很快会解释为什么）。
	 * 
	 * @param inventory
	 * @param weight
	 * @return
	 */
	public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;
	}

	/**
	 * 2.1.3 第三次尝试：对你能想到的每个属性做筛选
	 * 
	 * 一种把所有属性结合起来的笨拙尝试如下所示：
	 * 
	 * 这个解决方案再差不过了。首先，客户端代码看上去糟透了。true和false是什么意思？此
	 * 外，这个解决方案还是不能很好地应对变化的需求。如果这位农民要求你对苹果的不同属性做筛
	 * 选，比如大小、形状、产地等，又怎么办？而且，如果农民要求你组合属性，做更复杂的查询，
	 * 比如绿色的重苹果，又该怎么办？你会有好多个重复的filter方法，或一个巨大的非常复杂的
	 * 方法。到目前为止，你已经给filterApples方法加上了值（比如String、Integer或boolean）
	 * 的参数。这对于某些确定性问题可能还不错。但如今这种情况下，你需要一种更好的方式，来把
	 * 苹果的选择标准告诉你的filterApples方法。在下一节中，我们会介绍了如何利用行为参数化 实现这种灵活性。
	 * 
	 * @param inventory
	 * @param color
	 * @param weight
	 * @param flag
	 * @return
	 */
	public static List<Apple> filterApples(List<Apple> inventory, String color, int weight, boolean flag) {
		List<Apple> result = new ArrayList<Apple>();
		for (Apple apple : inventory) {
			if ((flag && apple.getColor().equals(color)) || (!flag && apple.getWeight() > weight)) {
				result.add(apple);
			}
		}
		return result;
	}

	// 2.1 应对不断变化的需求 end

	// 2.2 行为参数化 begin
	// 你在上一节中已经看到了，你需要一种比添加很多参数更好的方法来应对变化的需求。让
	// 我们后退一步来看看更高层次的抽象。一种可能的解决方案是对你的选择标准建模：你考虑的
	// 是苹果，需要根据Apple的某些属性（比如它是绿色的吗？重量超过150克吗？）来返回一个
	// boolean值。我们把它称为谓词（即一个返回boolean值的函数）。让我们定义一个接口来对选
	// 择标准建模：

	/**
	 * 解决方案是对你的选择标准建模：你考虑的 是苹果，需要根据Apple的某些属性（比如它是绿色的吗？重量超过150克吗？）来返回一个
	 * boolean值。我们把它称为谓词（即一个返回boolean值的函数）。让我们定义一个接口来对选 择标准建模
	 * 
	 * @author li_xiaodong
	 * @date 2018年4月9日
	 */
	interface ApplePredicate {
		public boolean test(Apple a);
	}

	// 现在你就可以用ApplePredicate的多个实现代表不同的选择标准了
	/**
	 * 
	 * 仅仅选出重的苹果
	 * 
	 * @author li_xiaodong
	 * @date 2018年4月9日
	 */
	static class AppleWeightPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return apple.getWeight() > 150;
		}
	}

	/**
	 * 仅仅选出绿苹果
	 * 
	 * @author li_xiaodong
	 * @date 2018年4月9日
	 */
	static class AppleColorPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return "green".equals(apple.getColor());
		}
	}

	static class AppleRedAndHeavyPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return "red".equals(apple.getColor()) && apple.getWeight() > 150;
		}
	}

	// 你可以把这些标准看作filter方法的不同行为。你刚做的这些和“策略设计模式”①相关，
	// 它让你定义一族算法，把它们封装起来（称为“策略”），然后在运行时选择一个算法。在这里，
	// 算法族就是ApplePredicate，不同的策略就是AppleHeavyWeightPredicate和AppleGreenColorPredicate。

	// 但是，该怎么利用ApplePredicate的不同实现呢？你需要filterApples方法接受
	// ApplePredicate对象，对Apple做条件测试。这就是行为参数化：让方法接受多种行为（或战
	// 略）作为参数，并在内部使用，来完成不同的行为。

	// 要在我们的例子中实现这一点，你要给filterApples方法添加一个参数，让它接受
	// ApplePredicate对象。这在软件工程上有很大好处：现在你把filterApples方法迭代集合的
	// 逻辑与你要应用到集合中每个元素的行为（这里是一个谓词）区分开了。

	/**
	 * 第四次尝试：根据抽象条件筛选
	 * 
	 * @param inventory
	 * @param p
	 * @return
	 */
	public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}

	// 2.3.4 第七次尝试：将 List 类型抽象化
	// 在通往抽象的路上，我们还可以更进一步。目前，filterApples方法还只适用于Apple。你还可以将List类型抽象化，从而超越你眼前要处理的问题：

	public interface Predicate<T> {
		boolean test(T t);
	}

	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> result = new ArrayList<>();
		for (T e : list) {
			if (p.test(e)) {
				result.add(e);
			}
		}
		return result;
	}

	public static class Apple {
		private int weight = 0;
		private String color = "";

		public Apple(int weight, String color) {
			this.weight = weight;
			this.color = color;
		}

		public Integer getWeight() {
			return weight;
		}

		public void setWeight(Integer weight) {
			this.weight = weight;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public String toString() {
			return "Apple{" + "color='" + color + '\'' + ", weight=" + weight + '}';
		}
	}

}
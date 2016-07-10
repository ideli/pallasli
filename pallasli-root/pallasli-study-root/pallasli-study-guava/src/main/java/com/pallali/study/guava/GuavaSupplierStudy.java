package com.pallali.study.guava;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Suppliers;

public class GuavaSupplierStudy {
	public static void main(String[] args) throws InterruptedException {
		// Suppliers.memorize()方法：
		SupplyCity sc = new SupplyCity();
		System.out.println(Suppliers.memoize(sc).get());
		System.out.println(Suppliers.memoize(sc).get());// 返回同一对象, 单例
		// Suppliers.memorizeWithExpiration()方法：
		sc = new SupplyCity(); // 超时再新建对象, 类似缓存
		Supplier<City2> supplier = (Supplier<City2>) Suppliers.memoizeWithExpiration(sc, 5, TimeUnit.SECONDS);
		City2 c = supplier.get();
		System.out.println(c);
		Thread.sleep(3000);
		c = supplier.get();
		System.out.println(c); // 与之前相等
		Thread.sleep(2000);
		c = supplier.get();
		System.out.println(c); // 与之前不等
		// Guava函数式编程基础，后面集合处理中，将体现得更强大。

	}
}

class SupplyCity implements com.google.common.base.Supplier<City2> {

	@Override
	public City2 get() {

		return null;
	}

}

class City2 {

}
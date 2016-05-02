package com.pallali.study.guava;

import java.math.BigInteger;
import java.math.RoundingMode;

import com.google.common.math.BigIntegerMath;
import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;

public class GuavaMathStudy {
	public static void main(String[] args) {
		int logFloor = LongMath.log2(232, RoundingMode.FLOOR);
		int mustNotOverflow = IntMath.checkedMultiply(4, 2);
		long quotient = LongMath.divide(6, 3, RoundingMode.UNNECESSARY);
		// fail fast on non-multiple of 3
		BigInteger nearestInteger = DoubleMath.roundToBigInteger(2.12,
				RoundingMode.HALF_EVEN);
		BigInteger sideLength = BigIntegerMath.sqrt(new BigInteger("2393279"),
				RoundingMode.CEILING);
		// 整数运算
		// Guava
		// Math主要处理三种整数类型：int、long和BigInteger。这三种类型的运算工具类分别叫做IntMath、LongMath和BigIntegerMath。
		//
		// 有溢出检查的运算
		//
		// Guava Math提供了若干有溢出检查的运算方法：结果溢出时，这些方法将快速失败而不是忽略溢出
		//
		// IntMath.checkedAdd LongMath.checkedAdd
		// IntMath.checkedSubtract LongMath.checkedSubtract
		// IntMath.checkedMultiply LongMath.checkedMultiply
		// IntMath.checkedPow LongMath.checkedPow
		// 1
		// IntMath.checkedAdd(Integer.MAX_VALUE, Integer.MAX_VALUE); // throws
		// ArithmeticException
		// 实数运算
		// IntMath、LongMath和BigIntegerMath提供了很多实数运算的方法，并把最终运算结果舍入成整数。这些方法接受一个java.math.RoundingMode枚举值作为舍入的模式：
		//
		// DOWN：向零方向舍入（去尾法）
		// UP：远离零方向舍入
		// FLOOR：向负无限大方向舍入
		// CEILING：向正无限大方向舍入
		// UNNECESSARY：不需要舍入，如果用此模式进行舍入，应直接抛出ArithmeticException
		// HALF_UP：向最近的整数舍入，其中x.5远离零方向舍入
		// HALF_DOWN：向最近的整数舍入，其中x.5向零方向舍入
		// HALF_EVEN：向最近的整数舍入，其中x.5向相邻的偶数舍入
		// 这些方法旨在提高代码的可读性，例如，divide(x, 3, CEILING)
		// 即使在快速阅读时也是清晰。此外，这些方法内部采用构建整数近似值再计算的实现，除了在构建sqrt（平方根）运算的初始近似值时有浮点运算，其他方法的运算全过程都是整数或位运算，因此性能上更好。
		//
		// 运算 IntMath LongMath BigIntegerMath
		// 除法 divide(int, int, RoundingMode) divide(long, long, RoundingMode)
		// divide(BigInteger, BigInteger, RoundingMode)
		// 2为底的对数 log2(int, RoundingMode) log2(long, RoundingMode)
		// log2(BigInteger, RoundingMode)
		// 10为底的对数 log10(int, RoundingMode) log10(long, RoundingMode)
		// log10(BigInteger, RoundingMode)
		// 平方根 sqrt(int, RoundingMode) sqrt(long, RoundingMode) sqrt(BigInteger,
		// RoundingMode)
		// 1
		// // returns 31622776601683793319988935444327185337195551393252
		// 2
		// BigIntegerMath.sqrt(BigInteger.TEN.pow(99), RoundingMode.HALF_EVEN);
		// 附加功能
		//
		// Guava还另外提供了一些有用的运算函数
		//
		// 运算 IntMath LongMath BigIntegerMath*
		// 最大公约数 gcd(int, int) gcd(long, long) BigInteger.gcd(BigInteger)
		// 取模 mod(int, int) mod(long, long) BigInteger.mod(BigInteger)
		// 取幂 pow(int, int) pow(long, int) BigInteger.pow(int)
		// 是否2的幂 isPowerOfTwo(int) isPowerOfTwo(long) isPowerOfTwo(BigInteger)
		// 阶乘* factorial(int) factorial(int) factorial(int)
		// 二项式系数* binomial(int, int) binomial(int, int) binomial(int, int)
		// *BigInteger的最大公约数和取模运算由JDK提供
		//
		// *阶乘和二项式系数的运算结果如果溢出，则返回MAX_VALUE
		//
		// 浮点数运算
		// JDK比较彻底地涵盖了浮点数运算，但Guava在DoubleMath类中也提供了一些有用的方法。
		//
		// isMathematicalInteger(double) 判断该浮点数是不是一个整数
		// roundToInt(double, RoundingMode) 舍入为int；对无限小数、溢出抛出异常
		// roundToLong(double, RoundingMode) 舍入为long；对无限小数、溢出抛出异常
		// roundToBigInteger(double, RoundingMode) 舍入为BigInteger；对无限小数抛出异常
		// log2(double, RoundingMode) 2的浮点对数，并且舍入为int，比JDK的Math.log(double) 更快
	}
}

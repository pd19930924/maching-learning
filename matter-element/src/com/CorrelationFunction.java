package com;

/**
 * 这个类中包含了关联计算函数
 * @author dong.pu
 *
 */
public class CorrelationFunction {
	
	/**
	 * 这是距计算函数
	 * @param value 要计算的值
	 * @param interval 对应的区间
	 * @return 计算得到的关联度
	 */
	public double getDistance(double value, double[] interval) {
		double result = 0;   //关联度结果
		double a = interval[0];
		double b = interval[1];
		result = Math.abs(value - (a + b)/2) - (b - a)/2;   //关联度计算公式
		return result;
	}
	
	/**
	 * 这是模值的计算
	 * @param interval 对应的区间
	 * @return 区间的模值
	 */
	public double getModule(double interval[]) {
		double a = interval[0];
		double b = interval[1];
		return Math.abs(b-a);
	}
}

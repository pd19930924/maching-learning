package com;

/**
 * ������а����˹������㺯��
 * @author dong.pu
 *
 */
public class CorrelationFunction {
	
	/**
	 * ���Ǿ���㺯��
	 * @param value Ҫ�����ֵ
	 * @param interval ��Ӧ������
	 * @return ����õ��Ĺ�����
	 */
	public double getDistance(double value, double[] interval) {
		double result = 0;   //�����Ƚ��
		double a = interval[0];
		double b = interval[1];
		result = Math.abs(value - (a + b)/2) - (b - a)/2;   //�����ȼ��㹫ʽ
		return result;
	}
	
	/**
	 * ����ģֵ�ļ���
	 * @param interval ��Ӧ������
	 * @return �����ģֵ
	 */
	public double getModule(double interval[]) {
		double a = interval[0];
		double b = interval[1];
		return Math.abs(b-a);
	}
}

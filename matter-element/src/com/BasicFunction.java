package com;

public class BasicFunction {

	private int best_location;  //�������������λ��
	
	/**
	 * @param values ����
	 * @return �����е����ֵ
	 */
	public double getMax(double values[]){
		double maxValue = -Double.MAX_VALUE;
		best_location = 0;
		int length = values.length;
		for(int i = 0; i < length; i++){
			if(values[i] > maxValue){
				maxValue = values[i];
				best_location = i;
			}
		}
		//System.out.println("���ֵ="+maxValue);
		//System.out.println("���Ž��="+best_location);
		return maxValue;
	}
	
	/**
	 * @return ��������λ��
	 */
	public int getLocation(){
		return this.best_location;
	}
	
}

package com;

public class BasicFunction {

	private int best_location;  //这是数组的最优位置
	
	/**
	 * @param values 数组
	 * @return 数组中的最大值
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
		//System.out.println("最大值="+maxValue);
		//System.out.println("最优结果="+best_location);
		return maxValue;
	}
	
	/**
	 * @return 返回最优位置
	 */
	public int getLocation(){
		return this.best_location;
	}
	
}

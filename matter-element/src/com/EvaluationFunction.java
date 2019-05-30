package com;

/**
 * 两个评价函数
 * @author dong.pu
 *
 */
public class EvaluationFunction {
	
	private CorrelationFunction correlationFunction;
	private int best_location;
	
	public EvaluationFunction(){
		correlationFunction = new CorrelationFunction();
		best_location = 0;
	}
	/**
	 * 当数值越小结果越好时对应的评价函数
	 * @param value 数值
	 * @param intervals 对应的所有区间
	 * @return 最优关联度
	 */
	public double[] valueIsSmall(double value,double intervals[][], double intervalLength){
		int length = intervals.length;  //区间个数
		double[] correlationList = new double[length];  //value对应每个区间的关联程度
		//计算当前数值与每一个区间的关联度
		for(int i = 0; i < length; i++){
			double a = intervals[i][0];  //区间左侧
			double b = intervals[i][1];  //区间右侧
			//在区间外
			if(value < a || value > b){
				correlationList[i] = -getRho(value, intervals[i], intervalLength); 
			}else{
				//在区间内
				double middleValue = (a+b)/2;  //计算中间位置
				//在区间靠左侧的部分
				if(value >= a && value <= middleValue){
					correlationList[i] = 1 + getRho(value, intervals[i]); 
				}else{
					//在区间靠右侧的部分
					correlationList[i] = -getRho(value, intervals[i]); 
				}
			}
		}
		return correlationList;
	}
	
	/**
	 * 当数值越大结果越好时对应的评价函数
	 * @param value 数值
	 * @param intervals 对应的所有区间
	 * @return 最优关联度
	 */
	public double[] valueIsBig(double value,double intervals[][], double intervalLength){
		int length = intervals.length;  //区间个数
		double[] correlationList = new double[length];  //value对应每个区间的关联程度
		double[] best_interval = new double[2];    //最优区间，也就是当前value值包含在哪个区间内
		for(int i = 0; i < intervals.length; i++) {
			double a = intervals[i][0];  //区间左侧
			double b = intervals[i][1];  //区间右侧
			if(value >= a && value < b) {
				best_interval = intervals[i];
				break;
			}
		}

		//计算当前数值与每一个区间的关联度
		for(int i = 0; i < length; i++){
			double a = intervals[i][0];  //区间左侧
			double b = intervals[i][1];  //区间右侧
			//在区间外
			if(value < a || value > b){
				correlationList[i] = -getRho(value, intervals[i], intervalLength); 
			}else{
				//如果是在边界上，那么关联度结果就是0
				if(value == a || value == b) {
					correlationList[i] = 0;
				}else {
					//在区间内
					double middleValue = (a+b)/2;  //计算中间位置
					//在区间靠左侧的部分
					if(value >= a && value <= middleValue){
						correlationList[i] = -getRho(value, intervals[i]); 
					}else{
						//在区间靠右侧的部分
						correlationList[i] = 1 + getRho(value, intervals[i]); 
					}					
				}

			}
		}
		return correlationList;
	}
	
	/**
	 * 返回公式 Rho(X0j, Xji)/|Xji|的值，用于计算
	 * @param value 数值
	 * @param interval 当前区间
	 * @return 计算结果
	 */
	public double getRho(double value, double interval[]){
		double Rho = 0.0;
		Rho = correlationFunction.getDistance(value, interval);
		Rho = Rho/correlationFunction.getModule(interval);
		return Rho;
	}
	
	/**
	 * 返回公式 Rho(X0j, Xji)/|Xqi|的值，用于计算
	 * @param value 数值
	 * @param interval 当前区间
	 * @return 计算结果
	 */
	public double getRho(double value, double interval[], double intervalLength){
		double Rho = 0.0;
		Rho = correlationFunction.getDistance(value, interval);
		Rho = Rho/intervalLength;
		return Rho;
	}
}

package com;


public class Main {
	
	private static int first_year = 1999;
	
	public static void main(String[ ] args) {
		double datas[][] = {{9.3, 89, 2.3, 0.91, 0.48},
				{9.1, 82, 4.5, 0.87, 0.45},
				{8.7, 75, 7.8, 0.80, 0.43},
				{8.5, 72, 9.1, 0.76, 0.41},
				{8.3, 68, 12.0, 0.73, 0.40},
				{8.0, 60, 13.2, 0.69, 0.39},
				{7.8, 55, 14.7, 0.67, 0.37}};
		
		double W[][] = {{0.2210,0.2673,0.1857,0.2019,0.1241},
				{0.2157, 0.2751, 0.1865, 0.1988, 0.1319},
				{0.2217, 0.2613, 0.1894, 0.1994, 0.1382},
				{0.2095, 0.2614, 0.1886, 0.2012, 0.1393},
				{0.2064, 0.2564, 0.1945, 0.2003, 0.1423},
				{0.2042, 0.2623, 0.1894, 0.2004, 0.1437},
				{0.2017, 0.2675, 0.1878, 0.1969, 0.1461}
		};
		
		//EvaluationFunction_test();
		
		
		MatterElement matterElement = new MatterElement();		
		
		for(int i = 0; i < datas.length ;i++) {
			int year = first_year+i;
			print(year+"年的数据");
			//print("************程序开始运行************");
			matterElement.proceed(datas[i], W[i]);  //开始运行程序
			int evaluation = matterElement.getEvaluation();
			evaluation += 1;
			print("评价结果 = "+evaluation);			
			print("************"+year+"年的数据计算结束************");
		}
		
		System.out.println();
		System.out.println("************以下是预测内容************");
		Prediction pre = new Prediction(2006, datas[5], W);
		print("************"+2006+"年的数据预测************");
		double[] W1 = pre.getW();
		matterElement.proceed(datas[5], W1);
		int evaluation = matterElement.getEvaluation();
		evaluation += 1;
		print("评价结果 = "+evaluation);			
	}
	
	private static void CorrelationFunction_test(){
		double[] interval = {4,0};
		double value = 2;
		CorrelationFunction cf = new CorrelationFunction();
		double result = cf.getDistance(value, interval);
		double module = cf.getModule(interval);
		print("distance = " ,result);
		print("module = ", module);
	}
	
	private static void BasicFunction_test(){
		BasicFunction bf = new BasicFunction();
		double[] interval = {1,13,5,1,2,5,1,7,9,10,2};
		double maxValue = bf.getMax(interval);
		double best_location = bf.getLocation();
		print("maxValue = ",maxValue);
		print("best_location = ", best_location);
	}
	
	private static void EvaluationFunction_test(){
		EvaluationFunction ef = new EvaluationFunction();
		double value = 9.3;
		double[] interval = {7,8.5};
		double[][] intervals = {{8.5,10},{7,8.5}};
		double res = ef.getRho(value, interval);
		print("getRho = ", res);
		double[] valueIsSmall = ef.valueIsSmall(value, intervals, 8);
		print("value is small = ", 0);
		printMatrix(valueIsSmall);
		value = 8.7;
		intervals = new double[][]{{8.5,10},{7,8.5},{5.5, 7}, {4, 5.5},{2, 4}};
		double[] valueIsBig = ef.valueIsBig(value, intervals, 8);
		print("value is big = ", 0);
		printMatrix(valueIsBig);
	}
		
	
	public static void print(String str){
		System.out.println(str);
	}
	
	public static void print(String str,double value){
		System.out.println(str + value);
	}
	
	public static void printMatrix(double A[]) {
		for(int i = 0 ; i < A.length; i++) {
			String str = String.format("%.4f", A[i]);
			System.out.print(str + " ");
		}
	}
	
	public static void printMatrix(double A[][]) {
		for(int i = 0; i < A.length; i++) {
			printMatrix(A[i]);
			System.out.println();
		}
	}
	
	public static void printMatrix(int A[]) {
		for(int i = 0; i < A.length; i++) {
			System.out.print(A[i] + " ");
		}
	}
}

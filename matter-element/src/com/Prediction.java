package com;

public class Prediction {

	private int year;   //年份
	private double data[] = new double[5];   //数据
	private double W[] = new double[5];   //权重
	
	private double W_init[][];  //初始权重
	
	public Prediction(int year, double data[], double W_init[][]) {
		this.year = year;
		this.data = data;
		this.W_init = W_init;
		getW_RQI();
		getW_PCI();
		getW_RD();
		getW_SSI();
		getW_SFC();
	}
	
	//这个量一直在增加，所以计算一个增量
	private void getW_RQI() {
		double add_value = 0;
		for(int i = 0; i < W_init.length - 1; i++) {
			add_value += W_init[i+1][0] - W_init[i][0];
		}
		add_value = add_value/(W_init.length-1);
		
		//当前时间到2005年的差，乘增加的量，再除2005年的数据
		W[0] = add_value*(year - 2005) + W_init[W_init.length-1][0];
	}
	
	private void getW_PCI() {
		double avg_value = 0;
		for(int i = 0; i < W_init.length; i++) {
			avg_value += W_init[i][1];
		}
		avg_value = avg_value/W_init.length;
		
		W[1] = avg_value;
	}
	
	private void getW_RD() {
		double avg_value = 0;
		for(int i = 0; i < W_init.length; i++) {
			avg_value += W_init[i][2];
		}
		avg_value = avg_value/W_init.length;
		
		W[2] = avg_value;
	}

	private void getW_SSI() {
		double avg_value = 0;
		for(int i = 0; i < W_init.length; i++) {
			avg_value += W_init[i][3];
		}
		avg_value = avg_value/W_init.length;
		
		W[3] = avg_value;
	}
	
	private void getW_SFC() {
		double add_value = 0;
		for(int i = 0; i < W_init.length - 1; i++) {
			add_value += W_init[i+1][4] - W_init[i][4];
		}
		add_value = add_value/(W_init.length-1);
		
		//当前时间到2005年的差，乘增加的量，再除2005年的数据
		W[4] = add_value*(year - 2005) + W_init[W_init.length-1][4];
	}
	
	public double[] getW() {
		System.out.println(year + "年的预测权重");
		printMatrix(W);
		System.out.println();
		return this.W;
	}
	
	private void printMatrix(double A[]) {
		for(int i = 0 ; i < A.length; i++) {
			String str = String.format("%.4f", A[i]);
			System.out.print(str + " ");
		}
	}
	
}

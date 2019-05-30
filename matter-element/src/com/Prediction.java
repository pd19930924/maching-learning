package com;

public class Prediction {

	private int year;   //���
	private double data[] = new double[5];   //����
	private double W[] = new double[5];   //Ȩ��
	
	private double W_init[][];  //��ʼȨ��
	
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
	
	//�����һֱ�����ӣ����Լ���һ������
	private void getW_RQI() {
		double add_value = 0;
		for(int i = 0; i < W_init.length - 1; i++) {
			add_value += W_init[i+1][0] - W_init[i][0];
		}
		add_value = add_value/(W_init.length-1);
		
		//��ǰʱ�䵽2005��Ĳ�����ӵ������ٳ�2005�������
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
		
		//��ǰʱ�䵽2005��Ĳ�����ӵ������ٳ�2005�������
		W[4] = add_value*(year - 2005) + W_init[W_init.length-1][4];
	}
	
	public double[] getW() {
		System.out.println(year + "���Ԥ��Ȩ��");
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

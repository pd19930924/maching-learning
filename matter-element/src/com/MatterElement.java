package com;

public class MatterElement {
	
	private EvaluationFunction evaluationFunction;   //���ۺ���
	private BasicFunction basicFunction;  //��������
	
	private int evaluation;  //��ǰ���ݵ����۽��
	
	private double[] data;    //��ʼ����
	
	//������Ԫ����һ����ά�����ʾ��һ����ά�����а���6��һά����
	//һά�����ǰһ�����ǵ�ǰ���ۣ������дβ��Ӧ��ȡֵ��Χ
	//���磬{2.0,4.0}��һ��[2.0,4.0)�����䣬ǰ�����
	//RQI�ľ�����Ԫ��
	private double[][] RQI = {{8.5,10},{7.0,8.5},{5.5,7.0},{4.0,5.5},{2.0,4.0}};
	private double RQILength = 10-2;  //RQI����ĳ���
	//PCI�ľ�����Ԫ��
	private double[][] PCI = {{85,100},{70,85},{55,70},{40,55},{30,40}};
	private double PCILength = 100-30; //PCI����ĳ���
	//RD�ľ�����Ԫ��
	private double[][] RD = {{0,5},{5,10},{10,15},{15,20},{20,35}};
	private double RDLength = 35 - 0;  //RD����ĳ���
	//SSI�ľ�����Ԫ��
	private double[][] SSI = {{1.2,1.5},{1.0,1.2},{0.8,1.0},{0.6,0.8},{0.4,0.6}};
	private double SSILength = 1.5-0.4;  //SSI����ĳ���
	//SFC�ľ�����Ԫ��
	private double[][] SFC = {{0.5,0.7},{0.4,0.5},{0.3,0.4},{0.2,0.3},{0.1,0.2}};
	private double SFCLength = 0.7-0.2;  //SFC����ĳ���
	
	//�����Ǹ��ڵ�����Ӧ��Ȩֵ��Ĭ��ֵ��Ϊ0.2
	private double w_RQI = 0.2;
	private double w_PCI = 0.2;
	private double w_RD = 0.2;
	private double w_SSI = 0.2;
	private double w_SFC = 0.2;
	/**
	 * @param datas Ҫ��������ݣ��б�ʾ��i�����ԣ��б�ʾ��j������
	 */
	public MatterElement(){
		this.evaluationFunction = new EvaluationFunction();
		this.basicFunction = new BasicFunction();
		this.evaluation = 0;  
	}
	

	/**
	 * ���Ǿ�����Ԫ��
	 * @return ���۽����0��ʾ�ţ�1��ʾ����2��ʾ�У�3��ʾ�Σ�4��ʾ��
	 */
	public int getEvaluation() {	
		return this.evaluation;
	}
	
	public void proceed(double[] data, double[] W) {
		this.data = data;
		this.w_RQI = W[0];
		this.w_PCI = W[1];
		this.w_RD = W[2];
		this.w_SSI = W[3];
		this.w_SFC = W[4];
		getJ();
		//print(this.evaluation +"", true);
	}
	
	/**
	 * ����Jֵ(·���ȼ�����)
	 * @param data ��������
	 * @param index ��ǰ�ǵ�i������
	 * @return ����·������
	 */
	private double getJ(){
		double J = 0;
		int length = data.length;  
		//RQI�Ĺ����Ƚ��
		double[] correlation_RQI = evaluationFunction.valueIsBig(data[0], RQI, this.RQILength);
		//PCI�Ĺ����Ƚ��
		double[] correlation_PCI = evaluationFunction.valueIsBig(data[1], PCI, this.PCILength);
		//RD�Ĺ����Ƚ��
		double[] correlation_RD = evaluationFunction.valueIsSmall(data[2], RD, this.RDLength);
		//SSI�Ĺ����Ƚ��
		double[] correlation_SSI = evaluationFunction.valueIsBig(data[3], SSI, this.SSILength);
		//SFC�Ĺ����Ƚ��
		double[] correlation_SFC = evaluationFunction.valueIsBig(data[4], SFC, this.SFCLength);

		double resJ[] = new double[length];  //·��data��Ӧ��ͬ�ȼ��Ĺ�����
		for(int i = 0; i < length; i++){
			double sum = 0;    //sum��w*correlation[i]��ֵ
			int j = i + 1;   //Ϊ������ʾ����0��ʼ
			print("************�ȼ�"+ j + "��Ӧ�Ĺ�������************",true);
			printDecimal("RQI�Ĺ�������Ϊ = ",correlation_RQI[i]);
			printDecimal("PCI�Ĺ������� = ",correlation_PCI[i]);
			printDecimal("RD�Ĺ������� = ",correlation_RD[i]);
			printDecimal("SSI�Ĺ������� = ",correlation_SSI[i]);
			printDecimal("SFC�Ĺ������� = ",correlation_SFC[i]);
			sum = w_RQI*correlation_RQI[i] + w_PCI*correlation_PCI[i] + w_RD*correlation_RD[i]
					+ w_SSI*correlation_SSI[i] + w_SFC*correlation_SFC[i];
			//sum = (correlation_RQI[i] + correlation_PCI[i] + correlation_RD[i]
			//		+ correlation_SSI[i] + correlation_SFC[i]) * 0.2;
			resJ[i] = sum;
			//print("sum="+sum, true);
		}
		
		print("�������ļ���������",true);
		printDecimal("�� = ",resJ[0]);
		printDecimal("�� = ",resJ[1]);
		printDecimal("�� = ",resJ[2]);
		printDecimal("�� = ",resJ[3]);
		printDecimal("�� = ",resJ[4]);
		//print(resJ);
		
		J = basicFunction.getMax(resJ);
		this.evaluation = basicFunction.getLocation();
		//��ȡ��ǰ�����е����ֵ
		return J;
	}
	
	private void printDecimal(String str, double value) {
		String result = String.format("%.4f", value);
		System.out.println(str + result);
	}
	
	private void print(String str, boolean needNewLines) {
		if(needNewLines) {
			System.out.println(str);			
		}else {
			System.out.print(str);		
		}
	}

	private void print(double Matrix[]){
		for(int i = 0; i < Matrix.length; i++) {
			print(Matrix[i]+" ", false);
		}
		print("",true);
	}
}

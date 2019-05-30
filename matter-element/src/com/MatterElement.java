package com;

public class MatterElement {
	
	private EvaluationFunction evaluationFunction;   //评价函数
	private BasicFunction basicFunction;  //基础函数
	
	private int evaluation;  //当前数据的评价结果
	
	private double[] data;    //初始数据
	
	//经典物元域用一个二维数组表示，一个二维数组中包含6个一维数组
	//一维数组里，前一个数是当前评价（优良中次差）对应的取值范围
	//例如，{2.0,4.0}是一个[2.0,4.0)的区间，前开后闭
	//RQI的经典物元域
	private double[][] RQI = {{8.5,10},{7.0,8.5},{5.5,7.0},{4.0,5.5},{2.0,4.0}};
	private double RQILength = 10-2;  //RQI区间的长度
	//PCI的经典物元域
	private double[][] PCI = {{85,100},{70,85},{55,70},{40,55},{30,40}};
	private double PCILength = 100-30; //PCI区间的长度
	//RD的经典物元域
	private double[][] RD = {{0,5},{5,10},{10,15},{15,20},{20,35}};
	private double RDLength = 35 - 0;  //RD区间的长度
	//SSI的经典物元域
	private double[][] SSI = {{1.2,1.5},{1.0,1.2},{0.8,1.0},{0.6,0.8},{0.4,0.6}};
	private double SSILength = 1.5-0.4;  //SSI区间的长度
	//SFC的经典物元域
	private double[][] SFC = {{0.5,0.7},{0.4,0.5},{0.3,0.4},{0.2,0.3},{0.1,0.2}};
	private double SFCLength = 0.7-0.2;  //SFC区间的长度
	
	//下面是各节点欲对应的权值，默认值都为0.2
	private double w_RQI = 0.2;
	private double w_PCI = 0.2;
	private double w_RD = 0.2;
	private double w_SSI = 0.2;
	private double w_SFC = 0.2;
	/**
	 * @param datas 要处理的数据，行表示第i个属性，列表示第j个数据
	 */
	public MatterElement(){
		this.evaluationFunction = new EvaluationFunction();
		this.basicFunction = new BasicFunction();
		this.evaluation = 0;  
	}
	

	/**
	 * 这是经典物元域
	 * @return 评价结果，0表示优，1表示良，2表示中，3表示次，4表示差
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
	 * 计算J值(路况等级评定)
	 * @param data 单个数据
	 * @param index 当前是第i个数据
	 * @return 返回路况评级
	 */
	private double getJ(){
		double J = 0;
		int length = data.length;  
		//RQI的关联度结果
		double[] correlation_RQI = evaluationFunction.valueIsBig(data[0], RQI, this.RQILength);
		//PCI的关联度结果
		double[] correlation_PCI = evaluationFunction.valueIsBig(data[1], PCI, this.PCILength);
		//RD的关联度结果
		double[] correlation_RD = evaluationFunction.valueIsSmall(data[2], RD, this.RDLength);
		//SSI的关联度结果
		double[] correlation_SSI = evaluationFunction.valueIsBig(data[3], SSI, this.SSILength);
		//SFC的关联度结果
		double[] correlation_SFC = evaluationFunction.valueIsBig(data[4], SFC, this.SFCLength);

		double resJ[] = new double[length];  //路段data对应不同等级的关联度
		for(int i = 0; i < length; i++){
			double sum = 0;    //sum是w*correlation[i]的值
			int j = i + 1;   //为了让显示不从0开始
			print("************等级"+ j + "对应的关联函数************",true);
			printDecimal("RQI的关联函数为 = ",correlation_RQI[i]);
			printDecimal("PCI的关联函数 = ",correlation_PCI[i]);
			printDecimal("RD的关联函数 = ",correlation_RD[i]);
			printDecimal("SSI的关联函数 = ",correlation_SSI[i]);
			printDecimal("SFC的关联函数 = ",correlation_SFC[i]);
			sum = w_RQI*correlation_RQI[i] + w_PCI*correlation_PCI[i] + w_RD*correlation_RD[i]
					+ w_SSI*correlation_SSI[i] + w_SFC*correlation_SFC[i];
			//sum = (correlation_RQI[i] + correlation_PCI[i] + correlation_RD[i]
			//		+ correlation_SSI[i] + correlation_SFC[i]) * 0.2;
			resJ[i] = sum;
			//print("sum="+sum, true);
		}
		
		print("五种类别的计算结果如下",true);
		printDecimal("优 = ",resJ[0]);
		printDecimal("良 = ",resJ[1]);
		printDecimal("中 = ",resJ[2]);
		printDecimal("次 = ",resJ[3]);
		printDecimal("差 = ",resJ[4]);
		//print(resJ);
		
		J = basicFunction.getMax(resJ);
		this.evaluation = basicFunction.getLocation();
		//获取当前数组中的最大值
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

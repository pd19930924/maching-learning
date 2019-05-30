package com;

/**
 * �������ۺ���
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
	 * ����ֵԽС���Խ��ʱ��Ӧ�����ۺ���
	 * @param value ��ֵ
	 * @param intervals ��Ӧ����������
	 * @return ���Ź�����
	 */
	public double[] valueIsSmall(double value,double intervals[][], double intervalLength){
		int length = intervals.length;  //�������
		double[] correlationList = new double[length];  //value��Ӧÿ������Ĺ����̶�
		//���㵱ǰ��ֵ��ÿһ������Ĺ�����
		for(int i = 0; i < length; i++){
			double a = intervals[i][0];  //�������
			double b = intervals[i][1];  //�����Ҳ�
			//��������
			if(value < a || value > b){
				correlationList[i] = -getRho(value, intervals[i], intervalLength); 
			}else{
				//��������
				double middleValue = (a+b)/2;  //�����м�λ��
				//�����俿���Ĳ���
				if(value >= a && value <= middleValue){
					correlationList[i] = 1 + getRho(value, intervals[i]); 
				}else{
					//�����俿�Ҳ�Ĳ���
					correlationList[i] = -getRho(value, intervals[i]); 
				}
			}
		}
		return correlationList;
	}
	
	/**
	 * ����ֵԽ����Խ��ʱ��Ӧ�����ۺ���
	 * @param value ��ֵ
	 * @param intervals ��Ӧ����������
	 * @return ���Ź�����
	 */
	public double[] valueIsBig(double value,double intervals[][], double intervalLength){
		int length = intervals.length;  //�������
		double[] correlationList = new double[length];  //value��Ӧÿ������Ĺ����̶�
		double[] best_interval = new double[2];    //�������䣬Ҳ���ǵ�ǰvalueֵ�������ĸ�������
		for(int i = 0; i < intervals.length; i++) {
			double a = intervals[i][0];  //�������
			double b = intervals[i][1];  //�����Ҳ�
			if(value >= a && value < b) {
				best_interval = intervals[i];
				break;
			}
		}

		//���㵱ǰ��ֵ��ÿһ������Ĺ�����
		for(int i = 0; i < length; i++){
			double a = intervals[i][0];  //�������
			double b = intervals[i][1];  //�����Ҳ�
			//��������
			if(value < a || value > b){
				correlationList[i] = -getRho(value, intervals[i], intervalLength); 
			}else{
				//������ڱ߽��ϣ���ô�����Ƚ������0
				if(value == a || value == b) {
					correlationList[i] = 0;
				}else {
					//��������
					double middleValue = (a+b)/2;  //�����м�λ��
					//�����俿���Ĳ���
					if(value >= a && value <= middleValue){
						correlationList[i] = -getRho(value, intervals[i]); 
					}else{
						//�����俿�Ҳ�Ĳ���
						correlationList[i] = 1 + getRho(value, intervals[i]); 
					}					
				}

			}
		}
		return correlationList;
	}
	
	/**
	 * ���ع�ʽ Rho(X0j, Xji)/|Xji|��ֵ�����ڼ���
	 * @param value ��ֵ
	 * @param interval ��ǰ����
	 * @return ������
	 */
	public double getRho(double value, double interval[]){
		double Rho = 0.0;
		Rho = correlationFunction.getDistance(value, interval);
		Rho = Rho/correlationFunction.getModule(interval);
		return Rho;
	}
	
	/**
	 * ���ع�ʽ Rho(X0j, Xji)/|Xqi|��ֵ�����ڼ���
	 * @param value ��ֵ
	 * @param interval ��ǰ����
	 * @return ������
	 */
	public double getRho(double value, double interval[], double intervalLength){
		double Rho = 0.0;
		Rho = correlationFunction.getDistance(value, interval);
		Rho = Rho/intervalLength;
		return Rho;
	}
}

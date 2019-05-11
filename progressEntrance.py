# -*- coding: utf-8 -*-
"""
Created on Tue May  7 15:52:26 2019

@author: dong.pu

要run的话，在这里run就好啦
"""

import Hierarchical_clustering
import resultValidate
import txtOperator
import boostTrapper
import KNN


datas,Labels = txtOperator.getDatas("datas.txt")  #获取归一化后的数据
trainDatas, trainResults, testDatas, testResults = boostTrapper.makeTrainAndTest(datas, Labels)   #这里是采用自助法对样本进行分类
#在Hierarchical_clustering中，如果将输入替换为trainDatas，那么就是题目所需要的结果，但是这样的聚类结果误差就比较大
#如果题目的需求是将所有数据放入层聚类中，那么输入datas即可
classification,trainDatas,trainLabels = Hierarchical_clustering.init(datas)  #获取聚类结果，最终结果存储在progress文件夹下的txt文档中
resultValidate.init(datas,classification,Labels)  #聚类结果的验证,这里可以注释掉，没什么作用,只是为了方便查询一下处理结果
KNN.init(trainDatas, trainLabels,testDatas, testResults, 3) #这里就是利用KNN打标签了





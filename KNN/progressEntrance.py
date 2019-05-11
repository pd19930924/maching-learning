# -*- coding: utf-8 -*-
"""
Created on Tue May  7 15:52:26 2019

@author: dong.pu

要run的话，在这里run就好啦
"""

import txtOperator
import boostTrapper
import KNN
import resultValidate

#获取标签集不同元素的个数
def getLabelsCount(labels):
    labelsCount = {}
    for label in labels:
        key = label
        labelsCount.setdefault(key, labelsCount.get(key))
    print(len(labelsCount))
    return len(labelsCount)

datas,Labels = txtOperator.getDatas("datas.txt")  #获取归一化后的数据
trainDatas, trainResults, testDatas, testResults = boostTrapper.makeTrainAndTest(datas, Labels)   #这里是采用自助法对样本进行分类
labelsCount = getLabelsCount(Labels)
KNN_labels = KNN.init(trainDatas, Labels, testDatas, testResults, labelsCount) #这里就是利用KNN打标签了
resultValidate.init(KNN_labels,Labels)  #KNN计算结果的验证




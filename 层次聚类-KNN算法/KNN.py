# -*- coding: utf-8 -*-
"""
Created on Wed May  8 12:28:33 2019

@author: dong.pu

KNN算法进行分类
"""

import math
import numpy as np

#计算两个数据之间的距离
def getDistance(data1, data2):
    distance = 0. #计算得到的距离
    #计算每个属性的距离值
    for i in range(len(data1)):
        #attr是attribute的缩写，表示data中的每个属性
        attr1 = data1[i]
        attr2 = data2[i]
        distance += math.pow(attr1-attr2,2)
    return math.sqrt(distance)

#统计标签，然后返回当前值对应的标签
def getLabel(sortDistIndex, trainLabels, LabelCount):
    classificationAccount = [0]*LabelCount  #分类结果这里只考虑分类为3的情形
    for index in sortDistIndex:
        #print(trainLabels[index])
        #print(trainLabels[index])
        classificationAccount[trainLabels[index]-1] +=1
    #print(classificationAccount[0],classificationAccount[1],classificationAccount[2])
    classificationAccount = np.array(classificationAccount)
    #print(np.argmax(classificationAccount))
    return np.argmax(classificationAccount)+1
#获取测试数据
def init(trainDatas, trainLabels, testDatas, testLabels, LabelCount):
    
    print("********************使用KNN打标签********************")
    K = 15   #K采用奇数的原因是防止出现标签个数相等的情况
    for testData in testDatas:
        distances = []  #data数据与所有训练数据的距离差
        for trainData in trainDatas:
            distances.append(getDistance(testData, trainData))
        #采用argsort进行升序排序，返回下标
        distances = np.array(distances) #转变为矩阵
        sortDistIndex = distances.argsort()   #获取下标
        #截取最后的K个元素
        sortDistIndexLength = len(sortDistIndex)
        #截取最后K个元素
        sortDistIndex = sortDistIndex[sortDistIndexLength - 1 - K: sortDistIndexLength - 1]
        label = getLabel(sortDistIndex, trainLabels, LabelCount)
        print("数据",testData,"的标签为",label)
        
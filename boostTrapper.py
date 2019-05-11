# -*- coding: utf-8 -*-
"""
Created on Wed May  8 10:57:06 2019

@author: dong.pu
"""

#自助法
import random

#生成训练集和测试集
#@param datas 测试数据，不包含标签
#@param results 测试数据对应的标签
def makeTrainAndTest(datas, results):
    #print(random.randint(0,3))
    tempDatas = datas.copy()  #复制数组
    tempResults = results.copy() 
    
    trainDatas = []  #训练数据，不包含标签
    trainResults = []  #训练数据，标签
    #这里返回分开的训练集和测试
    for i in range(int(len(datas)*0.8)):
        index = random.randint(0, len(tempDatas)-1)  #获取随机的数据位置
        trainDatas.append(tempDatas[index])
        trainResults.append(tempResults[index])
        #根据位置删除该元素
        del(tempDatas[index : index+1])
        del(tempResults[index : index+1])
    testDatas = tempDatas  #测试数据，不包含标签
    testResults = tempResults  #测试数据，标签
    
    #这里返回了所有结果，但是在实际中，我们只用到了trainDatas和trainResults
    return trainDatas, trainResults, testDatas, testResults


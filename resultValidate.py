# -*- coding: utf-8 -*-
"""
Created on Tue May  7 21:26:46 2019

@author: duang

这个部分主要用于结果的验证
"""


#为了方便数据显示
#@count 标签的数量
#@i 第i个分类集
def showValidateResult(count, i):
    print("在分类集",i,"中:")
    print("原标签为1的数据个数=",count[0])
    print("原标签为2的数据个数=",count[1])
    print("原标签为3的数据个数=",count[2])
    
#这个函数用来测试标签对应的数量
def setCount(count, actualOutput):
    if(actualOutput == 1):
        count[0]+=1
    if(actualOutput == 2):
        count[1]+=1
    if(actualOutput == 3):
        count[2]+=1
    return count

def init(datas, clusterResults, actualOutputs):
    #总共有三个类别，但是我们不知道三个分类具体怎样和实际输出相对应
    #例如，原来标号为1的，在我们这里的标签可能是3
    #下面的count是一个3维数组，分别表示实际标签i在三个经过层次聚类方法得到的标签中，对应的数据数量
    #举个例子，count1中，count1[0]表示1标签出现的数量，count2[0]表示2标签出现的数量，count3[0]表示3标签出现的数量
    count1 = [0]*3
    count2 = [0]*3
    count3 = [0]*3
    if(len(clusterResults)!=3):
        print("分类的数量不对=",len(clusterResults))
        print("分类的数量不对，请重新设置allowedMaxValue所对应的最大值")
        return
    #总共有三个类别
    cluster1 = clusterResults[0]
    cluster2 = clusterResults[1]
    cluster3 = clusterResults[2]
    for i in range(len(datas)):
        if(datas[i] in cluster1):
            count1 = setCount(count1, actualOutputs[i])
            #print(1)
        if(datas[i] in cluster2):
            count2 = setCount(count2, actualOutputs[i])
            #print(2)
        if(datas[i] in cluster3):
            count3 = setCount(count3, actualOutputs[i])
            #print(3)
    print("********************验证结果如下********************")
    showValidateResult(count1, 1)
    showValidateResult(count2, 2)
    showValidateResult(count3, 3)
    #print("正确的个数=",rightCount)
    #print("错误分类的个数=",len(actualOutputs)-rightCount)
    #print("正确率=",rightCount/len(actualOutputs))
  
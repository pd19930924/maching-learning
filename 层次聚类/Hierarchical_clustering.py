# -*- coding: utf-8 -*-
"""
Created on Tue May  7 11:05:27 2019

@author: dong.pu
"""

import numpy as np
import Linkage
import txtOperator

#初始化所有的元素，形成一个数据，就是一个类别
#@param datas 输入的数据
def init(datas):
    global classification   #声明一下，classification是全局变量
    global globalMinDistance  #这里是全局最小距离，用于判断循环是否还要继续
    #这里引入一个classification的原因是
    #在修改的时候不会影响到原数据
    classification = [] #这是总的集合
    for data in datas:
        #下面的data变为[data]，表示data成了一个单独的集合
        classification.append([data])
   
    globalMinDistance = 0  #最大距离设为无穷大
    
    i = 0 #这个u的作用是
    #允许的全局最小距离，如果超过这个值，说明分类已经基本完成，不需要继续细分
    allowedMinValue = 1 #这个值如果设置为i，那么就相当于取消这个条件，直接确定分为几类了
	#如果只有一个集合，那么就没有合并的意义了
    while((globalMinDistance < allowedMinValue) and (len(classification) > 3)):
        print("********************这是第",i,"次聚类********************")
        clustering()
        print("********************聚类结果********************")
        #print("分类结果=",classification)
        txtOperator.putInTxt(classification, i+1)
        print("全局最小距离=",globalMinDistance)
        print("********************第",i,"次聚类完成********************")
        i+=1
    print("标签数量=",len(classification))
    txtOperator.putInTxt(classification, -1)
    #这里把标签的数量也返回去，因为现在采用了随机抽取样本训练，所以产生的聚类结果不一定是三类
    
    #下面这段代码其实就是为了方便之后KNN计算，没有什么作用
    trainDatas = []  #这里我们将已经生成的带类标志的链表修改一下，本来是三维数组，但是我们这里改成二维数组
    labels = []  #这个是给我们已经生成的数据重新加标签
    #这表示我们现在有多少个类
    for i in range(len(classification)):
        #这表示一个类下有多少个数据
        for j in range(len(classification[i])):
            attrs = []
            #这表示数据所包含的属性有多少个
            for k in range(len(classification[i][j])):
                attrs.append(classification[i][j][k])
            labels.append(i)
            trainDatas.append(attrs)
    
    #最后有三个返回值，第一个是我们经过聚类得到的一个集合类，第二个是我们的训练数据（KNN用），第三个就是训练数据对应的标签（KNN用）
    return classification,trainDatas,labels

            
def clustering():
    global globalMinDistance
    count = len(classification)  #表示这里的元素个数
    #建立distance矩阵
    distances = [([float("inf")]*count) for i in range(count)]
    #这里要建立一个距离矩阵，用来保存所有的距离
    for i in range(count-1):
        for j in range(i+1,count):
            #表示点集合i到集合j的距离
            distances[i][j]= Linkage.groupAverage(classification[i],classification[j])
            distances[j][i]=distances[i][j]
    mergeSets(distances)
    dis = np.array(distances)  #这里要更新最小距离,用到numpy里的min函数
    globalMinDistance = np.min(dis)


#合并最近的两个集合
#基于距离合并集合
def mergeSets(distances):
    global classification
    
    #寻找整个distance里最短的距离及其横纵坐标，表示需要将第i个和第j个集合合并
    minDis = float("inf")
    loc1 = 0   #集合1的位置
    loc2 = 0   #集合2的位置
    for i in range(len(distances)):
        for j in range(len(distances[i])):
            dis = distances[i][j]
            #如果当前位置更优，更新位置
            if(dis < minDis):
                minDis = dis
                loc1 = i
                loc2 = j

    new_classification = mergeData(classification[loc1], classification[loc2])

    del(classification[loc2])    
    del(classification[loc1])

    classification.append(new_classification)
    #classification = new_classification

#合并数据，这里是因为前面采用的都是list，因此这里要对数据进行逐一合并
def mergeData(set1, set2):
    mergeList = []
    for data in set1:
        mergeList.append(data)
    for data in set2:
        mergeList.append(data)
    return mergeList
            






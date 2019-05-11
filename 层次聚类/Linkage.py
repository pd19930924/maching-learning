# -*- coding: utf-8 -*-
"""
Created on Tue May  7 11:32:04 2019

@author: dong.pu

这一部分的功能就是存储所有的Linkage，因为Linkage算法有数种，为方便进行调试，选择最合适的不同类之间的距离计算方法
"""
import math

#这是获得不同类集中所有元素之间，两两之间最近的距离
#@param set1 第一个类
#@param set2 第二个类
#@return 返回set1和set2的距离
def singleLinkage(set1, set2):
    minDistance = float("inf")   #距离最小值
    #求解最小距离
    for data1 in set1:
        for data2 in set2:
            distance = getDistance(data1, data2)
            if(distance < minDistance):
                minDistance = distance
    #print(minDistance)
    return minDistance
            
def completeLinkage(set1, set2): 
    maxDistance = -1   #距离最大值
    #求解最小距离
    for data1 in set1:
        for data2 in set2:
            distance = getDistance(data1, data2)
            if(distance > maxDistance):
                maxDistance = distance
    #print(minDistance)
    return maxDistance

def groupAverage(set1, set2):
    #求解距离均值
    distance = 0
    for data1 in set1:
        for data2 in set2:
            distance += getDistance(data1, data2)
    avgDis = distance/(len(set1)*len(set2))
    return avgDis

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
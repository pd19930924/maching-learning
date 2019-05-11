# -*- coding: utf-8 -*-
"""
Created on Tue May  7 15:52:26 2019

@author: dong.pu

这个函数主要用于txt文件(data.txt)文件的相关操作
"""

import Hierarchical_clustering

#为了将我们的数据拉到同一个数量级，这里必须要进行归一化
#@param value 我们的数据
#@param maxValue 要除的最大值
def normalization(value, maxValue):
    return value/maxValue

#读取txt文件
txt = open("datas.txt","r")

datas = []
results = []
for line in txt:
    string = line
    string = string.split("\t")
    result = string.pop()  #去掉最后一个元素，因为最后一个元素是分类结果
    result = result.split("\n")  #去掉后面的\n
    result = int(result[0])
    results.append(result)
    string = list(map(float, string))
    
    #总共有7个属性
    attr1 = normalization(string[0], 20) 
    attr2 = normalization(string[1], 20) 
    attr3 = string[2] 
    attr4 = normalization(string[3], 7)
    attr5 = normalization(string[4], 4) 
    attr6 = normalization(string[5], 8) 
    attr7 = normalization(string[6], 7)
    #print([attr1,attr2,attr3,attr4,attr5,attr6,attr7])
    datas.append([attr1,attr2,attr3,attr4,attr5,attr6,attr7])

txt.close()

Hierarchical_clustering.init(datas)




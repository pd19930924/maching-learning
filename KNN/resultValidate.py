# -*- coding: utf-8 -*-
"""
Created on Tue May  7 21:26:46 2019

@author: duang

这个部分主要用于结果的验证，判断当前标签分类是否正确
"""

#标签按照1,2,3,4,5写
def init(KNN_labels, actualOutputs):
    
    rightCount = 0   #分类正确的数目
    wrongCount = 0   #分类错误的数目
    
    for i in range(len(KNN_labels)):
        if(KNN_labels[i] == actualOutputs[i]):
            rightCount += 1
        else: wrongCount += 1
        
    print("********************验证结果如下********************")
    print("正确的个数=",rightCount)
    print("错误分类的个数=",wrongCount)
    print("正确率=",rightCount/(rightCount + wrongCount))
  
# -*- coding: utf-8 -*-
"""
Created on Tue May  7 23:19:53 2019

@author: duang

一个文本文档读取写入的文件，这个没有看的必要
"""

#为了将我们的数据拉到同一个数量级，这里必须要进行归一化
#@param value 我们的数据
#@param maxValue 要除的最大值
def normalization(value, maxValue):
    return value/maxValue

#获取属性矩阵，这里要根据实际情况进行修改
#@param string
#@return 返回一个数据，数据包含了7个属性
def getAttr(string):
    #总共有7个属性
    attr1 = normalization(string[0], 20) 
    attr2 = normalization(string[1], 20) 
    attr3 = string[2] 
    attr4 = normalization(string[3], 8)
    attr5 = normalization(string[4], 4) 
    attr6 = normalization(string[5], 8) 
    attr7 = normalization(string[6], 7)
    return [attr1,attr2,attr3,attr4,attr5,attr6,attr7]

#获取txt中的数据
def getDatas(path):
    #读取txt文件
    txt = open(path,"r")
    datas = []
    results = []
    for line in txt:
        string = line
        string = string.split("\t")
        result = string.pop()  #去掉最后一个元素，因为最后一个元素是分类结果
        result = result.split("\n")  #去掉后面的\n
        result = int(result[0])
        results.append(result)
        attrs = list(map(float, string))
        #如果已经归一化过了，删掉下面这句即可
        attrs = getAttr(attrs)
        datas.append(attrs)
    txt.close()
    return datas, results

#将数据放入txt中
#@param datas 要存放的txt数据
#@param time 表示这是第times次聚类,这是要生成相应的文件名
def putInTxt(datas, time):
    #文件保存在progress文件夹下
    #time = -1的情况下为输出结果，txt的名称会变
    if(time > 0):
        path = "progress"+ "\\" + "progress" + str(time) + ".txt"
    if(time == -1):
        path = "progress"+ "\\" + "result.txt"
    file = open(path,"w")
    #这表示我们现在有多少个类
    for i in range(len(datas)):
        #这表示一个类下有多少个数据
        for j in range(len(datas[i])):
            #这表示数据所包含的属性有多少个
            attrs = ""  #字符串
            for k in range(len(datas[i][j])):
                num = floatToString(datas[i][j][k])
                attrs = attrs + num + "\t"
            attrs = attrs + str(i+1) + "\n"   #这里加上标签位置和换行符
            file.write(attrs)
    file.close()

#将float型的数字转换为string类型
#如果数字因为缺0而导致长度变短，为了美观，这里要补上0
#@param num 字符串型的
def floatToString(data):
    num = round(data, 3)  #这里保留了三位小数
    num = str(num)
    if(len(num) < 5):
        diff = 5 - len(num)   #差的0的个数
        while(diff > 0):
            num = num + "0"
            diff = diff - 1
    return num
    
    
    
    
    
    
    
    
    
    
    
    
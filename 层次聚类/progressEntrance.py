# -*- coding: utf-8 -*-
"""
Created on Tue May  7 15:52:26 2019

@author: dong.pu

要run的话，在这里run就好啦
"""

import Hierarchical_clustering
import resultValidate
import txtOperator


datas,Labels = txtOperator.getDatas("datas.txt")  #获取归一化后的数据
classification,trainDatas,trainLabels = Hierarchical_clustering.init(datas)  #获取聚类结果，最终结果存储在progress文件夹下的txt文档中
resultValidate.init(datas,classification,Labels)  #聚类结果的验证,这里可以注释掉，没什么作用,只是为了方便查询一下处理结果





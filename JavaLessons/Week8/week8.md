# CS102A/CS107 Week 8
(Based on the slides from Prof.Yepang LIU(刘烨庞))        

## 1. 理论课内容框架
- 字符编码
- 字符串对象的构建与内存机制 **考点**
- 字符串对象的常用方法
- 字符串不可变性，字符串循环拼接优化与StringBuilder，StringBuffer的使用
- 基本数据类型及其包装类

### 1.1 字符编码

常用的字符编码有以下几种：（了解即可，并非考试重点）        
1. ASCII: 最原始的编码，1个byte，8个bit，共128个字符；
2. Unicode: 统一字符集，3个byte，24个bit，0x000000~0x10FFFF，属于编码扩充，共1100K+种字符，是Java默认的编码方式
3. 万国码: UTF32(定长) UTF16(变长) UTF8(变长) 本质是对Unicode的编码方式
4. GBK: 汉字内码扩展规范，确定对中文的编码

For more details, please refer to EE411: 信息论与编码

指定编码打印字符：```s.getBytes("UTF-8");```

在线学习网站：http://tool.chinaz.com/tools/unicode.aspx

### 1.2 字符串对象的构建与内存机制 考点
### 1.3 字符串对象的常用方法
### 1.4 字符串不可变性，字符串循环拼接优化与StringBuilder，StringBuffer的使用
### 1.5 基本数据类型及其包装类

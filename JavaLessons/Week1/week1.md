# CS102A/CS107 Week 1
(Based on the slides from Prof.Yepang LIU(刘烨庞))        

## Java让人拥有快乐，也会带来折磨~

Practice makes perfect!      

No Pain, No Gain!

- [X] 讲课思想：理论和实验部分两手抓————知识点梳理部分侧重理论（第一个环节），实验作业答疑部分侧重实验（第二个环节）
- [X] 讲课计划：2小时=1+1（知识点前后关联梳理（不多于1小时）+自由提问/答疑辅导（不少于1小时））
- [X] 答疑原则：关于Java课程的什么都能问，但是就是不能让我帮你debug！Debug是你自己的任务，与我无关！

## Part 0. 理念与初心
### 对同学们的期望：
- [X] 有过硬的关于Java语言的理论知识的基础水准；
- [X] 有优雅的规范代码书写能力；
- [X] 有十足的debug的耐心；
- [X] 有最基本的礼貌和品德；
- [X] 有清晰的思维和良好的沟通能力；
- [X] 有对于各种可能情形的周全考虑思维；
- [X] 有自行构建极端测试用例的程序测试能力；
- [X] 有针对错误情形的bug修复能力；
- [X] 有一定的代码阅读能力，能够对于程序一眼看出代码的逻辑与行为；
- [X] 有足够的通过搜索引擎获取知识的能力；

### 希望传达给同学们的理念：
- [X] 把手上简单的事情做好比尝试困难的事情更加重要；
- [X] 生活不止有眼前的苟且，更要有诗和远方；
- [X] 规则中要有人性，人性中要有规则

## Part 1. 理论课内容

1. CS102A 课程Overview

课程周次：每周1节理论 + 1节实验      

课程内容总体架构一分为四：     

- Introduction, Data Type, Control Statement, Array     
初识编程，了解数据类型，了解计算机指令与行为   
- Procedural Programming, OOP Introduction, Strings and Wrapper Class      
初步接触类与对象，完成由面向过程到面向对象的转换     
- Inheritance, Polymorphism, GUI, Generic Classes, Generic Collection      
高级面向对象编程，如继承，多态，泛型等等       
- Exception Handing      
异常处理，在工程中重要但并非课程考核内容

课程评分：

- 理论课签到 10%     
- 实验课签到 10%      
- OJ编程作业 30%      
- GUI项目 20%
- 期末笔试 30%     

课程特点 -> 我的建议：    
- 基本不调分 -> 不要对调分有任何念想       
- 签到部分一般去了就会直接满分 -> 尽量不要缺勤
- 作业部分会比较让人头秃，特别是无编程基础的同学 -> 将穿插课程作业讲解
- GUI项目需要强大的自学能力 -> 只能送给大家一句话：因为我不会，所以我才会
- 期末考试有cheating paper，考题形式灵活，种类多样 -> 有期末考试考前复习课

2. Chapter 1: Introduction to Computer, Internet abd Webs

- 硬件，软件
- 冯诺依曼体系结构 -> **期末不考，但是1+3的笔试有可能会考**
- 计算机组成结构： I, O, Memory, ALU ,CPU, Secondary Storage Unit     
（for more details, please refer to CS202: 计算机组成原理）
- 语言级别：机器码》》汇编》》高级语言
- 编译型与解释型语言的区别，编译器和解释器 -> **1+3面试有可能会考（张煜群老师当时就考了我这个2333）**
- Software, Internet, WWB
- Java编译运行步骤 -> **1+3面试有可能会考**     
.java》》.class》》.class文件转字节码然后放入内存》》字节码安全性检查》》放入JVM中运行
- 重要概念：IDE JDK JRE JVM -> **期末考试有可能会考**
- 程序错误类型有3种：     
- [ ] Syntax（语法错误）-> eg. 一不小心少打了一个括号
- [ ] Runtime（运行时错误） -> eg. 1/0 空指针异常
- [ ] Logic（逻辑错误） -> 头脑没想好就下手写出的bugs

# CS102A/CS107 Week 1
(Based on the slides from Prof.Yepang LIU(刘烨庞))        

## Java让人拥有快乐，也会带来折磨~

Practice makes perfect!      

No Pain, No Gain!

如果你在我的实验课班级：

- 可以做: 
- [X] 在理论课后，询问理论课知识点如何理解
- [X] 在理论课后，询问理论课每周的知识点总结讲义
- [X] 在实验课上，询问理论课Quiz题目的解答
- [X] 在实验课上，询问实验课练习的题目的题意
- [X] 在实验课上，询问实验课练习题目的思路
- [X] 询问作业题目的题意
- [X] 询问作业题目的思路
- [X] 在DDL后，对我写的标程代码的解读
- [X] 询问project的要求，以及可能的评分点
- [X] 询问project基础功能的实现思路
- [X] 询问期末考试的考点
- [X] 询问期末考试的复习资料

- 不能做：
- [ ] 在理论课quiz的时候，询问理论课Quiz的题意理解以及解答 **（理论课quiz有助于帮助理解知识点）**
- [ ] 在任何时候，你不做练习就拿过来让我通融记录lab课练习 **（我可以帮你放松一点检查要求，但你要有对练习题最起码的尊重）**
- [ ] 抄袭其他同学的代码 **（要遵守计系的规定）**
- [ ] 让我帮你debug，如询问诸如此类问题：我这代码怎么不能通过OJ？等等 **（帮着debug会剥夺大家的思考权利）**
- [ ] 让我指导你做project，以及让我帮你project debug **（project只能自力更生）**
- [ ] 询问project任何技术问题，如怎么加按钮？怎么加个组件？怎么加背景音乐？ **（这考验大家的自学能力）**
- [ ] argue project的评分，或者课后要求看project的评分表 **（project不接受任何分数argue）**
- [ ] 询问期末考试会考哪些题 **（我又不出期末考试试卷）**
 
## 1. 理论课内容框架
- CS102A 课程Overview
- Introduction to Computer, Internet abd Webs

### 1.1 CS102A 课程Overview

课程周次：每周1节理论 + 1节实验      

课程内容总体架构一分为四：     

- Introduction, Data Type, Control Statement, Array     
初识编程，了解数据类型，了解计算机指令与行为   
- Procedural Programming, OOP Introduction, Strings and Wrapper Class      
初步接触类与对象，完成由面向过程到面向对象的转换     
- Inheritance, Polymorphism, GUI, Generic Classes, Generic Collection      
高级面向对象编程，如继承，多态，泛型等等       
- Exception Handling      
异常处理，在工程中重要但并非课程考核内容

课程评分（可能有所变动）：

- 理论课签到 10%     
- 实验课签到 10%      
- OJ编程作业 30%      
- GUI项目 20%
- 期末笔试 30%     

2022春季学期课程评分：
- 理论课Quiz 5%     
- 实验课练习检查 5%      
- OJ编程作业 30%      
- GUI项目 20%
- 期末笔试 40%   

课程特点 -> 我的建议：    
- 基本不调分 -> 不要对调分有任何念想       
- 签到部分一般去了就会直接满分 -> 尽量不要缺勤
- 作业部分会比较让人头秃，特别是无编程基础的同学 -> 将穿插课程作业讲解
- GUI项目需要强大的自学能力 -> 只能送给大家一句话：因为我不会，所以我才会
- 期末考试有cheating paper（现在好像取消了），考题形式灵活，种类多样 -> 大家期末考试加油

### 1.2 Introduction to Computer, Internet abd Webs

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

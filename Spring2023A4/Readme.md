# Explanation to A4

这个作业是想让我们实现一个简易版的宝可梦对战系统。这个作业属实有一些折磨，不过还好我已经把大部分可能有坑的地方都挖掘了出来。这个explanation列出我之前踩过的所有的坑。

更新时间：2023-04-18

## 重要建议！

1. 请务必以OJ的题面描述为准，PDF的描述极有可能已经过期！
2. 多读题面，多认真读，可以减少很多犯错的可能！

## A4Q1 Skill

【坑1】`setPP`方法对于非法值需要强制约束至上下限区间内：

- 当传入的`PP`是小于0时，设置`this.PP`为0
- 当传入的`PP`是大于`maxPP`时，设置`this.PP`为`maxPP`

【坑2】`Type`类型是定义在`Skill`类内部的`enum`枚举类型，而且还不能是`private`

## A4Q2 Pokemon

【坑3】构造器方法，传入的`Skill... skills`可变参数列表，需要将原本的每个`skill`做**满PP值**的复制。

如以下写法无法通过oj：

```java
// WA1:
public Pokemon(String name, int maxhp, int attack, int speed,, Skill... skills){
    this.skills <- ArrayList Initializer
    for s in skills:
    	this.skills.add(s)
}
```

复制的时候，每一个`Skill`需要复制为满`PP`：

```java
// AC:
public Pokemon(String name, int maxhp, int attack, int speed,, Skill... skills){
    this.skills <- ArrayList Initializer
    for s in skills:
    	s' <- copy(s)
        setFullPP(s')
    	this.skills.add(s')
}
```

【坑4】`setHP`方法对于非法值需要强制约束至上下限区间内：

- 当传入的HP是小于0时，设置this.HP为0
- 当传入的HP是大于`maxPP`时，设置`this.HP`为`maxHP`

【坑5】`useSkillTo`方法也有上下限约束，约束方法见坑4。其中`Attack`类型的`Skill`的效果需要得到`Power`的加成。

## A4Q3 Trainer

【坑6】构造方法中，加入`pokemons`的规则与`addPokemon`相同：上限为6且无重名。这个部分无需考虑坑3中的复制问题。

【坑7】构造方法中，需要使用`summon()`方法对`this.activatePokemon`做初始化。

【坑8】两个`summon()`方法中，如果没有可以设置为`activatePokemon`的对象，那么在返回`null`之前**不需要**将`activatePokemon`设置为`null`！

## A4Q4 BattleField

【坑9】需要根据传入的字符串查询相对应的`Skill`对象。

【坑10】构造方法中，两个`trainer`需要各自发动一次`summon()`。

【坑11】`battle`方法中，首先比较速度的大小决定谁先出招。后出手者如果被先出手者击杀，那么后出手者不能使用技能。

【坑12】`battle`方法中，出招之前需要检查`PP`够不够用。发动技能后，需要减损相应的`PP`。

【坑13】`battle`方法中，需要根据**技能的类型**确定`useSkillTo`方法的`target`：`Heal`类型的`target`是发动者自己，`Attack`类型的`target`是发动者的对手

【坑14】`battle`方法中，双方出手后，如果有某一方的`pokemon`阵亡，那么需要`summon()`来替换。双方出手后，`turn`需要变化。

【坑15】输出的时候，`turn`输出的实际上应该是`turn - 1`
# 约束编程(Constraint Programming) - 2

## Class Diagram
上一周我们完成了初版Class Diagram的设计
![](img/class-diagram.png)

## Implementation
我们建立了与Class Diagram相对应的以及OptaPlanner所需要的Java Classes:
- 其中与Class Diagram相对应的有 (与OptaPlanner系统没有直接关联的Entity之后会将作为Abstract Classes以方便Future Implementation)
  - `Base.java` -> Base Entity (OptaPlanner@PlanningVariable)
  - `Route.java` -> Route Entity (OptaPlanner@PlanningSolution)
  - `Turbine.java` -> Turbine Entity (OptaPlanner@PlanningEntity)
  - `Part.java` -> Part Entity
  - `Technician.java` -> Technician Entity
  - `Vessel.java` -> Vessel Entity
- OptaPlanner所需部件:
  - `App.java` -> main file / executable file
  - `ScoreCalculator.java` -> 用于计算分数 (hardscore and softscore)
<br><br>

后来, 根据设计的方便性, 我们添加了一个Concrete Class与一个Interface
- `Location.java` -> Location Entity
- `Standstill.java` -> Standstill Interface用来表示状态, 船只的每一次停下都算是一个状态

## Score Calculator

### Version 1
我们的目标是让第一个版本尽可能的简单, 使用最少的约束条件先使代码可以运行<br>
以下是我们暂定的分数系统
- Soft Score
  - Distance
- Hard Score
  - Capacity

## Input

- Base
  - id
  - Location
- Vessel
  - id
  - capacity
  - base
- Turbine
  - id
  - location
  - demand
  - nextTurbine
  - vessel


## Output
```txt
hardscore(0);
softscore(-947);
vessel 001: base b0 -> turbine 006 -> turbine 002 -> turbine 001 -> turbine 003 -> turbine 005
vessel 002: base b0 -> turbine 008 -> turbine 002 -> turbine 007
vessel 003: base b0 -> turbine 004
```
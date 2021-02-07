# Source Code



## JSON FILES
json does not support comments, but I wrote some becasue this is a README.

#### INPUT
```json
{
    "scheduledCycle" : "5.0",   // 调度周期
    "salaries" : [ "3000.0", "3205.0", "3500.0" ],    // 技工日工资
    "terminal" : {     // 码头, ISSUE: 是否需要[], 表示多个码头?
        "x_axis" : "2245000.0",    // 码头坐标X
        "y_axis" : "434600.0",    // 码头坐标Y
        "workers" : [ "1.0", "2.0", "3.0", "4.0", "5.0" ],    // 码头每天技工分布
        "oneLevelWorkers" : [ "12.0", "12.0", "12.0", "12.0", "12.0"],
        "twoLevelWorkers" : [ "12.0", "12.0", "12.0", "12.0", "12.0"],
        "thirdLevelWorkers" : [ "12.0", "12.0", "12.0", "12.0", "12.0"]
    },
    "windFactory" : {    // 风电场, ISSUE: 是否需要[], 表示多个风电场?
        "furnaces" : [    // 风机
            {
                "number" : "0.0",    // 风机编号
                "x_axis" : "2278055.0",
                "y_axis" : "456571.0"
            }, ...
        ],
        "repairFurnaces" : [    // 需要维修的风机编号
            {
                "number" : "1.0",
                "maintainTime" : "4.0",
                "oneLevelWorker" : "2.0",
                "twoLevelWorker" : "0.0",
                "threeLevelWorker" : "1.0",
                "accessoryWeight" : "700.0",
                "needShip" : "0.0",
                "latestMaintainTime" : "3.0",
                "punishmentCost" : "62400.0"
            }, ...
        ]
    },
    "ships" : [    // 船
        {
            "capacity" : "3900.0",
            "carryPersonal" : "12.0",
            "oilFee" : "2240.0",
            "spreed" : "32.0"
        }, ...
    ],
    "additionalInfos" : [
        {    // 
            "transferTime" : "0.25",    // 技工转移时间
            "impi" : "1.0",    // 船的天气窗口
            "availableTime4Ship1" : "6.0",
            "availableTime4Ship2" : "6.0",
            "availableTime4Ship3" : "6.0"
        },
        ...
    ]
};
```

#### OUTPUT
```json
{
    "scheduledCycle" : "5.0",
    "salaries" : [ "3000.0", "3205.0", "3500.0" ],
    "terminal" : {    // ISSUE: 同INPUT, 是否需要[], 表示多个码头?
        "workers" : [ "1.0", "2.0", "3.0", "4.0", "5.0" ],
        "oneLevelWorkers" : [ "12.0", "12.0", "12.0", "12.0", "12.0"],
        "twoLevelWorkers" : [ "12.0", "12.0", "12.0", "12.0", "12.0"],
        "thirdLevelWorkers" : [ "12.0", "12.0", "12.0", "12.0", "12.0"]
    },
    "windFactory" : {    // ISSUE: 同INPUT, 是否需要[]? 表示多个windfarm
        "furnaces" : [
            {
                "number" : "0.0",
                "x_axis" : "2278055.0",
                "y_axis" : "456571.0"
            }, ...
        ],
        "repairFurnaces" : [
            {
                "number" : "1.0",
                "maintainTime" : "4.0",
                "oneLevelWorker" : "2.0",
                "twoLevelWorker" : "0.0",
                "threeLevelWorker" : "1.0",
                "accessoryWeight" : "700.0",
                "needShip" : "0.0",
                "latestMaintainTime" : "3.0",
                "punishmentCost" : "62400.0"
            }, ...
        ]
    },
    "ships" : [    // ISSUE: 怎么表达base里面有N个X船?
        {
            // ISSUE: 同INPUT, 是否加上ID?
            "capacity" : "3900.0",
            "carryPersonal" : "12.0",
            "oilFee" : "2240.0",
            "spreed" : "32.0"
        }, ...
    ],
    "additionalInfos" : [
        {
            "transferTime" : "0.25",
            "impi" : "1.0"
        },
        ...
    ]
};
```
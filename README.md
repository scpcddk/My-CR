# ⚔️ Clash Royale Backend Server (Java Engine)

本项目是一个基于 Java 语言独立开发的《皇室战争》核心战斗与游戏逻辑服务器。旨在摆脱传统“学生管理系统”的枯燥 CRUD，深入死磕游戏开发中的**强实时、高并发、状态机驱动**等核心后端工程痛点。

---

## 🚀 当前核心开发阶段：W3 - 核心战斗引擎地基

目前项目已完成大厂级工程包结构规范（领域驱动设计），正在攻克单机控制台版（Console）的核心战斗循环（Game Loop）。

### 🎯 MVP（最小可行性产品）第一期目标
- [x] **架构地基**：完成 `model`、`engine`、`constant`、`player` 的解耦设计。
- [ ] **实时心跳驱动**：手写 `GameLoop` 线程，实现 30Hz（每秒30次Tick）的战场逻辑刷新。
- [ ] **多对多自动寻路**：基于欧几里得距离公式，实现场上多实体（如小骷髅、王子）自动锁定最近敌方目标。
- [ ] **数值与升级系统**：设计 `Player` 资产账户（金币/宝石），并基于数学指数公式动态计算卡牌升级属性。

---

## 📂 项目包结构规范說明
```test
my-clash-royale-server/
├── .gitignore                      # Git 忽略文件（排除 .idea、.iml 等本地垃圾文件）
├── README.md                       # 项目的产品说明书与开发进度表
├── my-clash-royale-server.iml      # IDEA 项目配置文件
└── src/
└── main/
└── java/                   # 代码根目录（在 IDEA 中呈现蓝色）
└── com/
└── neudoc/
└── clashroyale/
│
├── Main.java              # 🚀 整个服务器的启动入口（包含 main 方法）
│
├── constant/              # 📌 【常量与配置域】
│   ├── Team.java          # 枚举：划分阵营（BLUE-蓝方玩家，RED-红方电脑/对手）
│   ├── EntityState.java   # 枚举：士兵状态机（IDLE-空闲，WALKING-寻路，ATTACKING-攻击，DEAD-死亡）
│   ├── ConfigManager.java # 配置中心：用数学公式计算卡牌升级消耗、动态属性提升
|   └── GameConstants.java # 静态常量类：包含游戏核心参数（如帧率、战场尺寸、圣水回复等）
│
├── model/                 # 🃏 【面向对象数据模型域】
│   ├── GameEntity.java    # 所有战场实体的老祖宗（抽象父类，包含基本坐标、血量、阵营、状态等）
│   ├── Soldier.java       # 士兵实体类（继承 GameEntity，如王子、皮卡，有移动速度、攻击 CD、圣水消耗）
│   ├── Tower.java         # 建筑实体类（继承 GameEntity，如国王塔、公主塔，速度为 0，无法移动）
│   ├── Spell.java         # 法术实体类（继承 GameEntity，如火球、滚木，具备范围伤害、击退或持续时间属性）
|   ├── Card.java          # 卡牌数据类（不可移动）：存储卡牌名称、圣水消耗、类型及对应的生成参数
│   └── Deck.java          # 卡组管理器：持有固定的 8 张卡牌列表，维护当前出牌索引，支持循环抽牌，并可在对局开始时重置索引
│
├── engine/                # 🧠 【核心战斗引擎大脑】
│   ├── BattleField.java   # 战场管理器：肚子里装一个 ArrayList<GameEntity>，负责实体的出生、死亡清理、多对多距离扫描
|   ├── ElixirManager.Java # 圣水管理器：负责管理玩家的圣水自动恢复、上限控制以及出兵扣除
|   ├── UnitCreator.java   # 单位创建器：通过阵营和坐标生成实体
│   └── GameLoop.java      # 时间轮心跳循环：开辟独立线程，死守 30Hz（每秒30次Tick）节拍，驱动 BattleField 刷新
│
├── player/                # 🪙 【玩家核心资产域】
│   └── Player.java        # 玩家账户类：存储玩家ID、金币、宝石、国王塔等级，以及个人卡组（哈希表存储卡牌名与等级/碎片数）
│
├──factory/                # 📦 【设计模式应用】
|   └── UnitFactory        # 工厂模式（Factory Pattern）：核心战斗单位的生命周期与属性初始化均由 UnitFactory统一接管
|
└── util/                  # 🛠️ 【算法工具箱】
└── MathUtil.java      # 数学工具类：提供静态方法，计算两点间欧几里得距离、圆形法术范围碰撞检测（AABB/包围盒检测）
```
# ⚔️ Clash Royale Java 后端 12 周完整任务计划（增强版）

---

## 📅 第 1 周：面向对象建模与阵营划分 ✅ 已完成

| 天 | 今日目标 | 技术点 | 依赖关系 | 自动验证 | 输出示例 |
|---|---------|--------|---------|---------|---------|
| D1 | 项目骨架 & Git 初始化 | .gitignore, git init | — | 项目可导入 IDEA | — |
| D2 | 枚举定义 | `Team.java`, `EntityState.java` | — | 编译通过 | `Team.BLUE`, `EntityState.IDLE` |
| D3 | 实体父类 | `GameEntity.java` (抽象类) | D2 | 构造器可用 | `name, hp, x, y, team, state` |
| D4 | 士兵与塔子类 | `Soldier.java`, `Tower.java` | D3 | super 调用正确 | `new Soldier(...)` |
| D5 | 数学工具 | `MathUtil.java` (距离计算) | — | 静态方法测试 | `getDistance(0,0,3,4) = 5.0` |
| D6-7 | Linux 基础 + 继承复盘 | 终端操作 | — | 还原包路径 | — |

---

## 📅 第 2 周：卡组数据结构与工厂模式 ✅ 已完成

| 天 | 今日目标 | 技术点 | 依赖关系 | 自动验证 | 输出示例 |
|---|---------|--------|---------|---------|---------|
| D8 | 卡牌数据类 | `Card.java` (不可变) | — | getter 测试 | `cardName, elixirCost, type` |
| D9 | 卡组循环队列 | `Deck.java` (List + Queue) | D8 | 8 张牌循环 | `hand=[A,B,C,D], pool=[E,F,G,H]` |
| D10 | 工厂方法 | `UnitFactory.java` | D4, D8 | 静态方法测试 | `createPrince(...)` |
| D11 | 玩家资产类 | `Player.java` | D9 | 金币/宝石默认值 | `playerId, gold=1000, gems=100` |
| D12 | Linux 进程管理 | ps, top, kill | — | 可管理 Java 进程 | — |
| D13-14 | 联调测试 | Main 调用 Player→Deck→Card | D9-D11 | 洗牌无 Bug | — |

---

## 📅 第 3 周：核心战斗引擎与圣水系统 ✅ 已完成

| 天 | 今日目标 | 技术点 | 依赖关系 | 自动验证 | 输出示例 |
|---|---------|--------|---------|---------|---------|
| D15 | 圣水管理器 | `ElixirManager.java` | — | 恢复/扣费测试 | `elixir=5.0, maxElixir=10.0` |
| D16 | 函数式接口 | `UnitCreator.java` | D4 | 编译通过 | `create(team, x, y)` |
| D17 | 战场与注册表 | `BattleField.java` (Map) | D10, D15, D16 | deployCard 走注册表 | `cardRegistry.put(...)` |
| D18 | 雷达索敌算法 | `findClosestEnemy()` | D5, D17 | 打印最近敌人 | `targetId=202, distance=3.4` |
| D19 | Linux Vim + curl | 编辑配置，调 API | — | curl 返回 200 | — |
| D20-21 | 圣水 & 部署联调 | D15→D17→D10 全链路 | D15-D18 | 圣水扣费正确 | — |

---

## 📅 第 4 周：30Hz 主循环与 Git 规范 ✅ 已完成

| 天 | 今日目标 | 技术点 | 依赖关系 | 自动验证 | 输出示例 |
|---|---------|--------|---------|---------|---------|
| D22 | 游戏时钟 | `GameLoop.java` (虚拟线程) | — | 10 FPS 日志 | `Thread.ofVirtual().start(this::loop)` |
| D23 | 绑定战场刷新 | GameLoop→BattleField.update() | D17, D22 | 每帧驱动全场 | `update() called` |
| D24 | 状态机日志 | 状态流转打印 | D23 | 行走→攻击→死亡 | `WALKING → ATTACKING → DEAD` |
| D25 | Git 分支管理 | feature 分支，Commit 规范 | — | 提交历史清晰 | — |
| D26 | Python 极简入门 | test_api.py 解析 JSON | — | 成功接收并打印 | — |
| D27-28 | 阶段冻结 & 推送 | 代码推送到 GitHub | D25 | 私有仓库可访问 | — |

---

## 📅 第 5 周：核心战斗循环完善 & 卡牌部署强化

| 天 | 今日目标 | 技术点 | 依赖关系 | 自动验证 | 输出示例 |
|---|---------|--------|---------|---------|---------|
| D29 | 调整 GameLoop → 30Hz 心跳 | 虚拟线程、sleep 补偿、帧率统计 | D22 | 打印每秒 Tick ≈ 30 | `{"tick":30,"fps":30}` |
| D30 | BattleField 实体刷新优化 | 遍历 → 移动 → 攻击 → 死亡判定 | D23→D29 | 每帧坐标/血量日志 | `{"entityId":101,"x":3.2,"y":5.0,"hp":150}` |
| D31 | 卡牌部署完整链路验证 | `GameLoop → update → Deck.playCard() → ElixirManager.consume()` | D9,D15,D17,D29 | JUnit：出牌后实体+1，圣水扣费 | `{"card":"Prince","success":true}` |
| D32 | UnitCreator 扩展 | 方法引用、Lambda | D16 | 新增兵种零报错 | `{"entityType":"Archer","hp":50}` |
| D33 | 战场索敌精调 | MathUtil + 距离阈值 | D17,D18 | 锁定最近敌人日志 | `{"soldierId":101,"targetId":202,"distance":3.4}` |
| D34 | 多回合卡组循环压力测试 | 连续出 16 张牌 | D9,D11 | JUnit：牌序循环正确 | `["Prince","Archer","Prince","Archer",...]` |
| D35 | 小结 & 代码整理 | 消除重复逻辑、添加注释 | 全部 | 手动 + log 检查 | 控制台输出稳定 |

---

## 📅 第 6 周：法术系统 & 配置管理器

| 天 | 今日目标 | 技术点 | 依赖关系 | 自动验证 | 输出示例 |
|---|---------|--------|---------|---------|---------|
| D36 | Spell 基础模型 | Spell.java 继承 GameEntity | D3 | JUnit：属性赋值正确 | `{"spell":"Fireball","damage":50,"radius":2.0}` |
| D37 | 法术部署逻辑 | BattleField.deploySpell() | D17,D36 | 周围士兵血量扣减 | `{"spellId":301,"targets":[101,102],"damage":50}` |
| D38 | 配置管理器初步 | ConfigManager.java (JSON) | D8,D36 | 读取 JSON 值正确 | `{"Prince":{"hp":300,"attack":50}}` |
| D39 | 数据驱动卡牌升级 | ConfigManager + Player | D11,D38 | 升级后属性符合配置 | `{"Prince":{"hp":350,"attack":60}}` |
| D40 | 圣水管理器优化 | 帧级恢复 & 上限保护 | D15 | 随 Tick 变化日志 | `{"playerId":1,"elixir":5.5}` |
| D41 | 控制台命令交互 | Scanner + 线程实时输入 | D17,D9,D15 | 自动触发脚本 | `{"action":"spawn","card":"Prince","success":true}` |
| D42 | 小结 & Demo | 2~3 兵 + 法术战斗演示 | 全部 | 日志截图 | ASCII 战斗日志 |

---

## 📅 第 7 周：多实体寻路 & AI 基础

| 天 | 今日目标 | 技术点 | 依赖关系 | 自动验证 | 输出示例 |
|---|---------|--------|---------|---------|---------|
| D43 | 多实体自动寻路 | Soldier.moveTowards(target) | D4,D17 | 每帧坐标变化 | `{"soldierId":101,"path":[[0,0],[1,0],[2,0]]}` |
| D44 | 距离锁定敌人 | MathUtil.getDistance | D5 | 最近敌人 ID | `{"targetId":202,"distance":3.4}` |
| D45 | 多目标攻击判定 | BattleField.update() 范围判定 | D17 | JUnit：血量变化 | `{"attackerId":101,"targets":[202],"damage":50}` |
| D46 | 法术 AoE 范围判定 | MathUtil.rangeCheck / AABB | D5,D36 | 受伤列表 | `{"spellId":301,"targets":[101,102]}` |
| D47 | AI 决策基础 | if-else 状态机 | D17,D11 | 决策日志 | `{"aiPlayer":2,"action":"deploy","cardIndex":0}` |
| D48 | 状态机完善 | EntityState + switch | D2,D17 | 状态变化日志 | `{"entityId":101,"state":"ATTACKING"}` |
| D49 | 小结 & 重构 | 优化 BattleField.update() | 全部 | JUnit：无并发异常 | 日志稳定 |

---

## 📅 第 8 周：多玩家 & 回合战斗整合

| 天 | 今日目标 | 技术点 | 依赖关系 | 自动验证 | 输出示例 |
|---|---------|--------|---------|---------|---------|
| D50 | 双玩家战场初始化 | Player + Deck + BattleField | D9,D11,D17 | 双方手牌、圣水输出 | `{"player1":{"hand":["Prince"]},"player2":{"hand":["Archer"]}}` |
| D51 | 多玩家出牌同步 | deployCard(Player) | D17,D9,D15 | JUnit：圣水独立扣费 | `{"player1":{"card":"Prince","success":true},"player2":{...}}` |
| D52 | 回合战斗逻辑完善 | Tick + 出牌 + 法术 | D22,D17,D36 | 回合日志 | ASCII 战场状态表 |
| D53 | 自动清理死亡实体 | removeDeadEntities | D17 | 30 秒无残留 | `{"removed":[101,102]}` |
| D54 | 回合输出状态优化 | printStatus() | D17 | 每帧坐标血量状态 | `{"tick":1,"entities":[...]}` |
| D55 | 单回合战斗录像 | 文本战斗日志 | D17,D11 | 日志文件 | ASCII / JSON |
| D56 | 小结 & 重构 | 类职责清晰 | 全部 | 战斗循环稳定 | 完整战斗状态输出 |

---

## 📅 第 9 周：多线程 & 并发调度

| 天 | 今日目标 | 技术点 | 依赖关系 | 自动验证 | 输出示例 |
|---|---------|--------|---------|---------|---------|
| D57 | Player 管理器初始化 | PlayerRegistry | D11 | 多玩家注册/取出 | `{"players":["p1","p2"]}` |
| D58 | 并发调度基础 | ThreadPoolExecutor / 虚拟线程 | D22 | 多玩家不阻塞 | `{"activeThreads":3}` |
| D59 | 并发出牌安全 | synchronized / Concurrent 集合 | D9,D17 | playCard() 多线程安全 | 16 张牌无丢失 |
| D60 | AI 对手自动出牌 | 简单策略：血量最低优先 | D17 | 自动投放日志 | `{"aiPlayer":2,"action":"deploy"}` |
| D61 | BattleField 多线程刷新 | Tick 分配线程池 | D17,D58 | 打印线程名 + Tick ID | `Thread-1, tick=42` |
| D62 | 并发问题调试 | ConcurrentModificationException | D61 | 运行 1 分钟无异常 | 日志稳定 |
| D63 | 小结 & Demo | 多线程战斗演示 | 全部 | 1~2 分钟无崩溃 | 打印多线程状态 |

---

## 📅 第 10 周：Java ↔ Python 推理 / API 联动

| 天 | 今日目标 | 技术点 | 依赖关系 | 自动验证 | 输出示例 |
|---|---------|--------|---------|---------|---------|
| D64 | Python AI 脚本基础 | test_api.py，伤害预测 | D26 | Python 返回 JSON | `{"advised_target":101}` |
| D65 | Java 调用 Python | ProcessBuilder / Runtime.exec | D64 | 成功启动脚本 | — |
| D66 | 跨语言数据传递 | JSON / stdin/stdout (BufferedReader/BufferedWriter) | D65 | 实体状态→决策回传 | `{"entityId":101,"hp":100} → {"action":"attack"}` |
| D67 | AI 推理逻辑 | Python 返回攻击目标 | D66 | 战场按结果执行 | `"attackerId":101 → target 202` |
| D68 | Serverless API 模拟 | HTTP REST 调用 | D67 | 返回 200 + JSON | `{"action":"deploy","card":"Prince"}` |
| D69 | 异步调用优化 | CompletableFuture / Future | D68 | 不卡 Tick | 异步日志 |
| D70 | 小结 & Demo | AI 决策战斗演示 | 全部 | 决策与动作日志 | AI 决策 + 战场执行 |

---

## 📅 第 11 周：AIOps 智能监控模块

| 天 | 今日目标 | 技术点 | 依赖关系 | 自动验证 | 输出示例 |
|---|---------|--------|---------|---------|---------|
| D71 | CPU / 内存监控 | OperatingSystemMXBean | — | 打印 JVM 资源 | `{"cpu":45,"memory":"512MB"}` |
| D72 | 战场负载计算 | 实体数 → Tick 耗时 | D17,D22 | 帧耗时 vs 实体数 | `{"entities":15,"frameTime":12ms}` |
| D73 | AI 优化建议 | Python/API 返回建议 | D64 | 控制台打印建议 | `"建议减少最大线程数"` |
| D74 | 自动调节出兵速率 | 按 AI 建议调整 spawn 频率 | D73 | 圣水消耗合理 | `{"spawnRate":2}` |
| D75 | 历史战斗数据记录 | log / CSV | D17 | 保存每回合状态 | `logs/battle_001.csv` |
| D76 | 数据分析 → 可视化 | ASCII 图或 CSV 图表 | D75 | 实体分布趋势 | ASCII 柱状图 |
| D77 | 小结 & Demo | AI 智能监控演示 | 全部 | 建议 + 战斗动态 | 监控仪表盘日志 |

---

## 📅 第 12 周：定稿 & 工程化收尾

| 天 | 今日目标 | 技术点 | 依赖关系 | 自动验证 | 输出示例 |
|---|---------|--------|---------|---------|---------|
| D78 | 全盘代码大扫除 | 清理 System.out，统一日志框架 | 全部 | IDEA 零黄色警告 | — |
| D79 | README 整理 | 项目结构 + 操作说明 + 调度拓扑图 | — | README 可指导运行 | Markdown + 架构图 |
| D80 | 录制部署演示 | OBS / 屏幕录制 | 全部 | 战斗+出牌+AI 决策 | 3 分钟视频 |
| D81 | GitHub 仓库整理 | 标签、Commit、Release | D25 | 提交历史干净 | v1.0.0 Release |
| D82 | 最终 BUG 修复 | 全面回归测试 | 全部 | 战斗/法术/卡牌无异常 | — |
| D83 | 简历亮点提炼 | 项目经验总结 | — | 可写入简历 | 项目模块描述 |
| D84 | 项目复盘总结 | 技术回顾 + 个人学习心得 | 全部 | 学习成果文档 | W1-W12 总结 |

---

> **执行提示**
> - 每周末写 200~300 字进度总结，方便导师跟进。
> - 每完成一个模块，保留控制台日志作为验证证据。
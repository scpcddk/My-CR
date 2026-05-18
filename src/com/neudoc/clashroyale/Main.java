package com.neudoc.clashroyale;

import com.neudoc.clashroyale.constant.Team;
import com.neudoc.clashroyale.engine.BattleField;
import com.neudoc.clashroyale.engine.GameLoop;
import com.neudoc.clashroyale.factory.UnitFactory;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("==================================================");
        System.out.println("🏰 《皇室战争》后端物理引擎 V21.0 - 跑马灯对决正式启动 🏰");
        System.out.println("==================================================");

        // 1. 铺设战场
        BattleField battleField = new BattleField();

        // 2. 使用工厂模式【一键投放】战斗单位，告别繁琐的赋值操作！
        System.out.println("\n--- [准备就绪] 正在通过 UnitFactory 向战场投放首批战斗单位 ---");

        // 蓝方：一位王子，出生在 (0, 0)
        battleField.addEntity(UnitFactory.createPrincessTower(Team.BLUE, 0.0, 0.0));
        // 红方：一位弓箭手，出生在 (8, 0)；一座公主塔，坐落在 (12, 1)
        battleField.addEntity(UnitFactory.createArcher(Team.RED, 8.0, 0.0));
        battleField.addEntity(UnitFactory.createPrincessTower(Team.RED, 12.0, 1.0));

        System.out.println("---------------------------------------------------\n");

        // 3. 轰鸣吧！启动引擎时钟！
        GameLoop gameLoop = new GameLoop(battleField);
        gameLoop.start();

        // 4. 【观战席】主线程在这里静静看他们厮杀 15 秒钟
        Thread.sleep(15000);

        // 5. 战事休兵
        gameLoop.stop();
        System.out.println("\n==================================================");
        System.out.println("🏰 战局终了。感谢主宰观战！ 🏰");
        System.out.println("==================================================");
    }
}

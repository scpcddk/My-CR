package com.neudoc.clashroyale;

import com.neudoc.clashroyale.constant.Team;
import com.neudoc.clashroyale.engine.BattleField;
import com.neudoc.clashroyale.engine.GameLoop;
import com.neudoc.clashroyale.factory.UnitFactory;
import com.neudoc.clashroyale.model.Card;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("==================================================");
        System.out.println(" 🏰 《皇室战争》后端物理引擎 V3.0 - 圣水消费时代 🏰 ");
        System.out.println("==================================================");

        // 1. 铺设战场
        BattleField battleField = new BattleField();

        // 2. 使用工厂模式【一键投放】战斗单位，告别繁琐的赋值操作！
        System.out.println("\n--- [准备就绪] 正在通过 UnitFactory 向战场投放首批战斗单位 ---");

        System.out.println("---------------------------------------------------\n");

        // 3. 轰鸣吧！启动引擎时钟！
        GameLoop gameLoop = new GameLoop(battleField);

        // 红方公主塔开局就在场上（建筑不消耗圣水）
        battleField.addEntity(UnitFactory.createPrincessTower(Team.RED,12.0,1.0));

        gameLoop.start();

        // ✅ 新增开始 ================================================

        // 手牌模板
        Card princeCard = new Card("王子卡牌",5,"SOLDIER");
        Card archerCard = new Card("弓箭手卡牌", 3, "SOLDIER");

        System.out.println("\n--- \uD83C\uDFAE 玩家【实时对局模拟】正式开始 ---");

        // 开局下王子（5费，刚好够）
        battleField.deployCard(Team.BLUE, princeCard, 0.0, 0.0);

        // 立刻下弓箭手（圣水已空，失败）
        battleField.deployCard(Team.BLUE, archerCard, 2.0, 0.0);

        // 等待8秒，圣水恢复
        System.out.println("\n⏳ 双方选手开始控场、积攒圣水 (主线程观战 8 秒)... \n");
        Thread.sleep(8000);

        // 红方反手打出弓箭手（3费，成功）
        battleField.deployCard(Team.RED, archerCard, 8.0, 0.0);

        // 继续观战10秒
        Thread.sleep(10000);
        // ✅ 新增结束 ================================================

        // 4. 战事休兵
        gameLoop.stop();
        System.out.println("\n==================================================");
        System.out.println("🏰 战局终了。感谢主宰观战！ 🏰");
        System.out.println("==================================================");
    }
}

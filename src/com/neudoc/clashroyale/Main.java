package com.neudoc.clashroyale;

import com.neudoc.clashroyale.constant.Team;
import com.neudoc.clashroyale.engine.BattleField;
import com.neudoc.clashroyale.engine.GameLoop;
import com.neudoc.clashroyale.factory.UnitFactory;
import com.neudoc.clashroyale.model.Card;
import com.neudoc.clashroyale.model.Deck;
import com.neudoc.clashroyale.player.Player;
import java.util.List;

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

        // 1. 严格按照游戏规则：凑齐 8 张卡牌作为一套完整的出战卡组
        List<Card> eightCards = List.of(
                new Card("王子卡牌", 5, "SOLDIER"),
                new Card("弓箭手卡牌", 3, "SOLDIER"),
                new Card("王子卡牌", 5, "SOLDIER"),
                new Card("弓箭手卡牌", 3, "SOLDIER"),
                new Card("弓箭手卡牌", 3, "SOLDIER"),
                new Card("王子卡牌", 5, "SOLDIER"),
                new Card("王子卡牌", 5, "SOLDIER"),
                new Card("弓箭手卡牌", 3, "SOLDIER")
        );

        // 2. 组装：把 8 张牌交给 Deck，再把 Deck 交给 Player
        Deck blueDeck = new Deck(eightCards);
        Player bluePlayer = new Player("player_001", Team.BLUE, blueDeck);

        System.out.println("\n--- 🎮 规范化架构：卡组轮替测试开始 ---");
        // 【回合 1】：看手牌，打出第 0 张
        bluePlayer.showHand();
        Card c1 = bluePlayer.getDeck().playCard(0);
        battleField.deployCard(bluePlayer.getTeam(), c1, 0.0, 0.0);

        // 【回合 2】：再看手牌，验证循环补牌
        bluePlayer.showHand();

        Thread.sleep(5000);
        // ✅ 新增结束 ================================================

        // 4. 战事休兵
        gameLoop.stop();
        System.out.println("\n==================================================");
        System.out.println("🏰 战局终了。感谢主宰观战！ 🏰");
        System.out.println("==================================================");
    }
}

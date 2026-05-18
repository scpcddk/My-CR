package com.neudoc.clashroyale.engine;

public class GameLoop {
    private final BattleField battleField;
    private boolean running = false;
    private final int fps = 10; // 每秒 10 次心跳，方便我们在控制台肉眼看清跑马灯

    public GameLoop(BattleField battleField) {
        this.battleField = battleField;
    }

    // 🚀 启动游戏时钟
    public void start() {
        if(running)
            return;
        this.running = true;
        // 🔥 【JDK 21 终极黑科技】：直接开启一个轻量级的虚拟线程（Virtual Thread）来跑游戏死循环！
        // 传统的 Thread 极其消耗内存，而虚拟线程由 JVM 调度，开销几乎为零
        Thread.ofVirtual().start(this::loop);
        System.out.println("[游戏时钟] ⏳ 核心时间轮已通过 JDK 21 虚拟线程成功挂载并点火！");
    }

    // 🔄 游戏主死循环体
    private void loop() {
        long interval = 1000 / fps;  // 每次心跳的标准间隔时间（100毫秒）

        while(running) {
            long startTime = System.currentTimeMillis();

            // 💓 核心驱动：强行按一下战场的update按钮，触发【雷达寻敌 -> 移动 -> 厮杀 -> 收尸】
            battleField.update();

            // 计算这一帧逻辑耗费了多少时间（防止卡顿抖动）
            long frameTime = System.currentTimeMillis() - startTime;
            long sleepTime = interval - frameTime; // 算出要补的休息时间

            if(sleepTime > 0) {
                try {
                    // 让出 CPU 资源，静静等待下一帧的到来
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    // 🛑 停止游戏时钟
    public  void stop() {
        this.running = false;
        System.out.println("[游戏时钟] 🛑 核心时间轮已安全刹车，对局宣告结束。");
    }
}

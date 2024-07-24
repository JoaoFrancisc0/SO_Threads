public class Clock extends Thread {
    private ClockInterface clockInterface;
    private int clockSleep;

    public Clock(ClockInterface clockInterface, int clockSleep) {
        this.clockInterface = clockInterface;
        this.clockSleep = clockSleep;
    }

    public void run() {
        try {
            while(true) {
                clockInterface.tick();

                Thread.sleep(clockSleep);
            }
        } catch (InterruptedException e) {
            System.out.println("Algo deu errado");
        }
    }
}

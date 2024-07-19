public class Clock extends Thread {
    private ClockInterface clockInterface;

    public Clock(ClockInterface clockInterface) {
        this.clockInterface = clockInterface;
    }

    public void run() {
        try {
            while(true) {
                clockInterface.tick();

                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            System.out.println("Algo deu errado");
        }
    }
}

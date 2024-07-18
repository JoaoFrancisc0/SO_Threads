public class Clock extends Thread {
    private Memoria memoria;

    public Clock(Memoria memoria) {
        this.memoria = memoria;
    }

    public void run() {
        try {
            while(true) {
                memoria.tick();

                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            System.out.println("Algo deu errado");
        }
    }
}

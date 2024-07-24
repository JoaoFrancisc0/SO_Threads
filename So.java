public class So {
    static final int tamanhoMemoria = 20;
    static final int tempoSleep = 2000;
    static final int clockSleep = 5000;
    public static void main(String[] args) {
        String[] listaOp_1 = {"20-W-1","3-R"};
        String[] listaOp_2 = {"1-R","2-R"};

        Memoria memoria = new Memoria(tamanhoMemoria);
        
        Clock clock = new Clock(memoria, clockSleep);
        clock.start();
        
        Processo thread_1 = new Processo(listaOp_1, memoria, 1, tempoSleep);
        Processo thread_2 = new Processo(listaOp_2, memoria, 2, tempoSleep);

        thread_1.start();
        thread_2.start();
    }
}
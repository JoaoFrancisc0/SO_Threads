public class So {
    public static void main(String[] args) {
        String[] listaOp_1 = {"20-W-1","3-R"};
        String[] listaOp_2 = {"1-R","2-R"};

        Memoria memoria = new Memoria(20);
        
        Clock clock = new Clock(memoria);
        clock.start();
        
        Processo thread_1 = new Processo(listaOp_1, memoria, 1);
        Processo thread_2 = new Processo(listaOp_2, memoria, 2);

        thread_1.start();
        thread_2.start();
    }
}
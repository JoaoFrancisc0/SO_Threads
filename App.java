public class App {
    public static void main(String[] args) {
        String[] listaOp_1 = {"5-W-1","2-R","1-W-555","5-R"};
        String[] listaOp_2 = {"1-R","2-R","1-R","1-R"};

        Memoria memoria = new Memoria(20);
        
        Clock clock = new Clock(memoria);
        
        Operacoes thread_1 = new Operacoes(listaOp_1, memoria);
        Operacoes thread_2 = new Operacoes(listaOp_2, memoria);
        
        thread_1.start();
        thread_2.start();
    }
}
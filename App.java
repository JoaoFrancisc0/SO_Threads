public class App {
    public static void main(String[] args) {
        String[] listaOp_1 = {"5-W-1","1-W-4","5-R"};
        Memoria memoria = new Memoria();
        Operacoes thread_1 = new Operacoes(listaOp_1, memoria);
        thread_1.start();
    }
}
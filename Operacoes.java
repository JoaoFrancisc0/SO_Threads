public class Operacoes extends Thread {
    private String[] listaOp;
    private Memoria memoria;

    public Operacoes(String[] listaOp, Memoria memoria) {
        this.listaOp = listaOp;
        this.memoria = memoria;
    }

    public void run() {
        for (String string : listaOp) {
            String[] operacoes = string.split("-");
            if (operacoes.length == 3) {
                int posicao = Integer.parseInt(operacoes[0]);
                int valor = Integer.parseInt(operacoes[2]);
                memoria.write(posicao, valor);
                System.out.println("Escrito");
            }
            else {
                int posicao = Integer.parseInt(operacoes[0]);
                System.out.println(memoria.read(posicao));
            }
        }
    }
}
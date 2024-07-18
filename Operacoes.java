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
                memoria.write(posicao - 1, valor);
                System.out.println("Escrito");
            }
            else {
                int posicao = Integer.parseInt(operacoes[0]);
                int temp = memoria.read(posicao - 1);
                if (temp != -1){
                    System.out.println(temp);
                }
                else {
                    System.out.println("Posição na memória virtual vazia");
                }

            }
        }
    }
}
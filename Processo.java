public class Processo extends Thread {
    private String[] listaOp;
    private Memoria memoria;
    private int id;
    private int tempoSleep;

    public Processo(String[] listaOp, Memoria memoria, int id, int tempoSleep) {
        this.listaOp = listaOp;
        this.memoria = memoria;
        this.id = id;
        this.tempoSleep = tempoSleep;
    }

    public void run() {
        for (String string : listaOp) {
            String[] operacoes = string.split("-");
            if (operacoes.length == 3) {
                int posicao = Integer.parseInt(operacoes[0]);
                int valor = Integer.parseInt(operacoes[2]);
                memoria.write(posicao - 1, valor, id);
                System.out.println("Escrito");
            }
            else {
                int posicao = Integer.parseInt(operacoes[0]);
                int temp = memoria.read(posicao - 1, id);
                if (temp != -1){
                    System.out.println(temp);
                }
                else {
                    System.out.println("Posição na memória virtual vazia");
                }

            }
            try {
                sleep(tempoSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
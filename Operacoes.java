public class Operacoes extends Thread {
    private String listaOp;
    private Memoria memoria;

    public Operacoes(String listaOp, Memoria memoria) {
        this.listaOp = listaOp.substring(1, listaOp.length() - 1);
        this.memoria = memoria;
    }

    public void run() {
        System.out.println(listaOp);
        String[] array = listaOp.split(",");

        for (String string : array) {
            if (string.length() == 5) {
                char valor = string.charAt(string.length() - 1);
                int posicao = Integer.parseInt(string.substring(0, string.length() - 4));
                memoria.write(posicao, valor);
                System.out.println("Escrito");
            }
            else {
                int posicao = Integer.parseInt(string.substring(0, string.length() - 2));
                System.out.println(memoria.read(posicao));
            }
        }
    }
}
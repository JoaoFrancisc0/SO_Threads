public class Memoria {
    int X = 20;
    private Pagina[] memoria_V = new Pagina[X];

    public int read(int posicao) {
        if (memoria_V[posicao].isPresenca()) {
            memoria_V[posicao].setReferenciada(true);
            return memoria_V[posicao].getValor();
        }
        System.out.println("Retorno Vazio");
        return memoria_V[posicao].getValor();
    }

    public void write(int posicao, int valor) {
        if (memoria_V[posicao] == null) {
            memoria_V[posicao] = new Pagina(true, true, posicao, valor);
        }
        else if (!(memoria_V[posicao].isReferenciada())) {
            memoria_V[posicao].setPosicao(posicao);
            memoria_V[posicao].setPresenca(true);
            memoria_V[posicao].setReferenciada(true);
            memoria_V[posicao].setValor(valor);
        }
        // else Parte das outras memorias
    }
}

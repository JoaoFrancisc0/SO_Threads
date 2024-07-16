public class Memoria {
    private int fisicaLivre;

    private Pagina[] memoria_V;
    private Pagina[] memoria_F;
    private Pagina[] memoria_D;

    public Memoria(int tamanho) {
        this.memoria_V = new Pagina[tamanho];
        this.memoria_F = new Pagina[tamanho/2];
        this.memoria_D = new Pagina[tamanho];
    }

    public int read(int posicao) {
        if (memoria_V[posicao] != null) {
            // Caso true, esta página está na memória física
            if (memoria_V[posicao].isPresenca()) {
                posicao = memoria_V[posicao].getPosicao();
                return memoria_F[posicao].getValor();
            }
            // Caso false, esta página está na memória de disco
            else {
                // Buscando local na memoria fisica para ocupar
                fisicaLivre = relogio(posicao);
                memoria_D[posicao].setReferenciada(true);
                memoria_F[fisicaLivre] = memoria_D[posicao];
                memoria_D[posicao] = null;
                memoria_F[fisicaLivre].setPosicao(fisicaLivre);
                posicao = memoria_V[posicao].getPosicao();
                return memoria_F[posicao].getValor();
            }
        }
        else {
            System.out.println("Posição na memória virtual vazia");
            return 404;
        }
    }

    public void write(int posicao, int valor) {
        // Caso o espaço da memoria virtual esteja livre
        if(memoria_V[posicao] == null) {
            fisicaLivre = relogio(posicao);
            memoria_V[posicao] = new Pagina(true, true, fisicaLivre, valor);
            memoria_F[fisicaLivre] = memoria_V[posicao];
        }
        else {
            fisicaLivre = relogio(posicao);
            memoria_V[posicao].setReferenciada(true);
            memoria_V[posicao].setPresenca(true);
            memoria_V[posicao].setPosicao(fisicaLivre);
            memoria_V[posicao].setValor(valor);
            memoria_F[fisicaLivre] = memoria_V[posicao];
        }
    }

    public int relogio(int posicao) {
        int cont = 0;
        // Buscando local na memoria fisica para ocupar
        while(true){
            for (Pagina pagina : memoria_F) {
                // Caso a pagina esteja vazia
                if (pagina == null) {
                    break;
                }
                // Caso a pagina esteja no estado não referenciado
                // Passa para o disco
                else if(!pagina.isReferenciada()) {
                    memoria_F[cont].setPresenca(false);
                    memoria_D[posicao] = memoria_F[cont];
                    memoria_F[cont] = null;
                    break;
                }
                cont++;
            }
            // Cont será a posição da memoria fisica que está livre
            return cont;
        }
    }
}

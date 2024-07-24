import java.util.Arrays;

public class Memoria implements ClockInterface {
    private int fisicaLivre;
    private Pagina[] memoria_V;
    private Pagina[] memoria_F;
    private Pagina[] memoria_D;
    private int tamanho;

    public Memoria(int tamanho) {
        this.memoria_V = new Pagina[tamanho];
        this.memoria_F = new Pagina[tamanho/2];
        this.memoria_D = new Pagina[tamanho];
        Arrays.fill(memoria_V, null);
        Arrays.fill(memoria_F, null);
        Arrays.fill(memoria_D, null);
        this.tamanho = tamanho;
    }

    public int read(int posicao, int id) {
        posicao = divisor(posicao, id);
        
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
                memoria_D[posicao].setPresenca(true);
                // Depois de modificar na memoria de disco passa para a fisica e entao remove da de disco
                memoria_F[fisicaLivre] = memoria_D[posicao];
                memoria_D[posicao] = null;
                memoria_F[fisicaLivre].setPosicao(fisicaLivre);
                // Passa a versão atualizada para a virtual
                memoria_V[posicao] = memoria_F[fisicaLivre];
                return memoria_F[fisicaLivre].getValor();
            }
        }
        else {
            return -1;
        }
    }

    public void write(int posicao, int valor, int id) {
        posicao = divisor(posicao, id);

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

    // Retorna o index da memoria fisica que esta livre
    // E caso tenha alguma pagina com referenciada = false mesmo estando na memoria fisica, passa para a de disco
    public int relogio(int posicao) {
        int cont = 0;
        boolean verificador = false;
        // Buscando local na memoria fisica para ocupar
        while(true){
            for (Pagina pagina : memoria_F) {
                // Caso a pagina esteja vazia
                if (pagina == null) {
                    verificador = true;
                    break;
                }
                // Caso a pagina esteja no estado não referenciado
                // Passa para o disco
                else if(!pagina.isReferenciada()) {
                    // posicao -> indice em memoria virtual
                    // cont -> indice em memoria fisica
                    memoria_F[cont].setPresenca(false);
                    memoria_F[cont].setPosicao(-1);
                    memoria_D[posicao] = memoria_F[cont];
                    memoria_F[cont] = null;
                    memoria_V[posicao] = memoria_D[posicao];
                    verificador = true;
                    break;
                }
                cont++;
            }
            // Cont será a posição da memoria fisica que está livre
            if (verificador) {
                return cont;
            }
        }
    }

    // Percorre memoria F e passa referenciada para false
    public void tick() {
        for (Pagina pagina : memoria_V) {
            if(pagina != null && pagina.isPresenca()) {
                pagina.setReferenciada(false);
            }
        }
    }

    // Algoritmo que "divide" as memorias
    public int divisor(int posicao, int id) {
        if (id == 1)
            posicao = posicao % (tamanho/2);
        else
            if(posicao < (tamanho/2))
                posicao += tamanho/2;
        return posicao;
    }
}

public class Pagina {
    private boolean referenciada;
    private boolean presenca;
    private int posicao;
    private char valor;

    public Pagina(boolean referenciada, boolean presenca, int posicao, char valor) {
        this.referenciada = referenciada;
        this.presenca = presenca;
        this.posicao = posicao;
        this.valor = valor;
    }
    
    public boolean isReferenciada() {
        return referenciada;
    }
    public void setReferenciada(boolean referenciada) {
        this.referenciada = referenciada;
    }
    public boolean isPresenca() {
        return presenca;
    }
    public void setPresenca(boolean presenca) {
        this.presenca = presenca;
    }
    public int getPosicao() {
        return posicao;
    }
    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
    public char getValor() {
        return valor;
    }
    public void setValor(char valor) {
        this.valor = valor;
    }

    
}
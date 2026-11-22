import greenfoot.*;

public class GerenciadorPontuacao {
    private int pontos;

    public GerenciadorPontuacao() {
        this.pontos = 0;
    }

    public void adicionarPontos(int pontos) {
        this.pontos += pontos;
        if (this.pontos < 0) {
            this.pontos = 0;
        }
    }

    public int getPontos() {
        return pontos;
    }

    public void resetar() {
        this.pontos = 0;
    }
}
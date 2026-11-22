import greenfoot.*;

public class GerenciadorFases {
    private World mundo;
    private int ondaAtual;
    private int metaDaOnda;
    private int floresColetadas;
    private static final int TOTAL_ONDAS = 3;

    public GerenciadorFases(World mundo) {
        this.mundo = mundo;
        this.ondaAtual = 0;
        this.metaDaOnda = 0;
        this.floresColetadas = 0;
    }

    public void iniciarProximaOnda() {
        ondaAtual++;
        floresColetadas = 0;
        metaDaOnda = 3 + (ondaAtual * 2);
        gerarFlores();
        gerarInimigos();
    }

    public int getOndaAtual() {
        return ondaAtual;
    }

    public int getFloresRestantes() {
        int restantes = metaDaOnda - floresColetadas;
        return Math.max(0, restantes);
    }

    public void registrarFlorColetada() {
        floresColetadas++;
    }

    public boolean isOndaConcluida() {
        return ondaAtual > 0 && floresColetadas >= metaDaOnda;
    }

    public boolean isJogoConcluido() {
        return ondaAtual >= TOTAL_ONDAS && isOndaConcluida();
    }

    public void gerarInimigos() {
        if (mundo == null) {
            return;
        }
        int largura = mundo.getWidth();
        int altura = mundo.getHeight();

        int qtdBrisas = 1 + ondaAtual;
        int qtdVentanias = ondaAtual;

        for (int i = 0; i < qtdBrisas; i++) {
            int x = Greenfoot.getRandomNumber(largura);
            int y = Greenfoot.getRandomNumber(altura);
            mundo.addObject(new Brisa(), x, y);
        }

        for (int i = 0; i < qtdVentanias; i++) {
            int x = Greenfoot.getRandomNumber(largura);
            int y = Greenfoot.getRandomNumber(altura);
            mundo.addObject(new Ventania(), x, y);
        }
    }

    public void gerarFlores() {
        if (mundo == null) {
            return;
        }
        int largura = mundo.getWidth();
        int altura = mundo.getHeight();

        for (int i = 0; i < metaDaOnda; i++) {
            int x = 60 + Greenfoot.getRandomNumber(Math.max(1, largura - 120));
            int y = 80 + Greenfoot.getRandomNumber(Math.max(1, altura - 140));
            mundo.addObject(new Flor(), x, y);
        }

        // Adiciona um Nectar extra a cada onda para recuperacao de vida
        int nx = 60 + Greenfoot.getRandomNumber(Math.max(1, largura - 120));
        int ny = 80 + Greenfoot.getRandomNumber(Math.max(1, altura - 140));
        mundo.addObject(new Nectar(), nx, ny);
    }
}
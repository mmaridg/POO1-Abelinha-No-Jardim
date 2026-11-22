import greenfoot.*;

public class PainelHUD {
    private World mundo;

    public PainelHUD(World mundo) {
        this.mundo = mundo;
    }

    public void atualizar(int vida, int pontos, int onda, int flores) {
        if (mundo == null) {
            return;
        }
        mundo.showText("Vida: " + vida, 70, 25);
        mundo.showText("Pontos: " + pontos, 190, 25);
        mundo.showText("Onda: " + onda, 310, 25);
        mundo.showText("Flores restantes: " + flores, 460, 25);
    }

    public void mostrarVitoria() {
        if (mundo == null) {
            return;
        }
        mundo.showText("VITORIA! Jardim salvo!", mundo.getWidth() / 2, mundo.getHeight() / 2);
    }

    public void mostrarDerrota() {
        if (mundo == null) {
            return;
        }
        mundo.showText("DERROTA! A abelhinha perdeu todas as vidas.", mundo.getWidth() / 2, mundo.getHeight() / 2);
    }
}
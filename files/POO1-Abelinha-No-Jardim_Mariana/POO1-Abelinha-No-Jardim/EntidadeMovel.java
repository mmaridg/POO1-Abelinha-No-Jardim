import greenfoot.*;

/**
 * Base de todas as entidades que se movem e possuem vida na arena.
 */
public abstract class EntidadeMovel extends Actor {
    private int vida;
    private int vidaMaxima;
    private int velocidade;

    public EntidadeMovel(int vidaMaxima, int velocidade) {
        super();
        this.vidaMaxima = Math.max(1, vidaMaxima);
        this.vida = this.vidaMaxima;
        this.velocidade = Math.max(1, velocidade);
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = Math.max(0, Math.min(vida, vidaMaxima));
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        if (velocidade > 0) {
            this.velocidade = velocidade;
        }
    }

    public void receberDano(int valor) {
        if (valor > 0) {
            setVida(vida - valor);
        }
    }

    public boolean estaViva() {
        return vida > 0;
    }

    protected void manterDentroDaArena() {
        World mundo = getWorld();
        if (mundo == null || getImage() == null) {
            return;
        }
        int metadeLargura = getImage().getWidth() / 2;
        int metadeAltura = getImage().getHeight() / 2;
        int x = Math.max(metadeLargura, Math.min(getX(), mundo.getWidth() - 1 - metadeLargura));
        int y = Math.max(metadeAltura, Math.min(getY(), mundo.getHeight() - 1 - metadeAltura));
        setLocation(x, y);
    }
}

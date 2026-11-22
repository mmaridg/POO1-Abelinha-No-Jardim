import greenfoot.*;

public class Brisa extends Inimigo {
    private int direcao;

    public Brisa() {
        super(20, 3, 5);
        this.direcao = Greenfoot.getRandomNumber(360);
        setRotation(this.direcao);
        setImage(gerarSpriteBrisa());
    }

    @Override
    public void atuar() {
        if (Greenfoot.getRandomNumber(100) < 5) {
            direcao = (direcao + Greenfoot.getRandomNumber(90) - 45) % 360;
            setRotation(direcao);
        }
        move(getVelocidade());
    }

    @Override
    public void atacar(Abelha alvo) {
        if (alvo != null && alvo.estaViva()) {
            alvo.receberDano(getDano());
            int dx = Integer.compare(alvo.getX() - getX(), 0) * 8;
            int dy = Integer.compare(alvo.getY() - getY(), 0) * 8;
            alvo.empurrar(dx, dy);
        }
    }
    
    private GreenfootImage gerarSpriteBrisa() {
        return criarSpriteVento(
            36,
            24,
            new Color(173, 216, 230), // Azul claro suave
            new Color(255, 255, 255), // Branco
            2
        );
    }
}
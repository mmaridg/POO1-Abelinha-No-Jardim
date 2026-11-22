import greenfoot.*;

public class Ventania extends Inimigo {
    private int forcaEmpurrao;

    public Ventania() {
        super(30, 4, 15);
        this.forcaEmpurrao = 25;
        setRotation(Greenfoot.getRandomNumber(360));
        setImage(gerarSpriteVentania());
    }

    @Override
    public void atuar() {
        move(getVelocidade());
        if (Greenfoot.getRandomNumber(100) < 8) {
            turn(Greenfoot.getRandomNumber(120) - 60);
        }
    }

    @Override
    public void atacar(Abelha alvo) {
        if (alvo != null && alvo.estaViva()) {
            alvo.receberDano(getDano());
            int dx = Integer.compare(alvo.getX() - getX(), 0) * forcaEmpurrao;
            int dy = Integer.compare(alvo.getY() - getY(), 0) * forcaEmpurrao;
            alvo.empurrar(dx, dy);
        }
    }

    public int getForcaEmpurrao() {
        return forcaEmpurrao;
    }

    public void setForcaEmpurrao(int forca) {
        this.forcaEmpurrao = Math.max(0, forca);
    }
    
    private GreenfootImage gerarSpriteVentania() {
        GreenfootImage img = criarSpriteVento(
            52,
            36,
            new Color(70, 130, 180),  // Azul aço mais forte
            new Color(224, 255, 255), // Ciano claro
            4
        );

        img.setColor(new Color(135, 206, 250, 160));
        img.drawOval(10, 6, 20, 20);
        img.drawOval(14, 10, 12, 12);

        return img;
    }
}
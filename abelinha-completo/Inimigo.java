import greenfoot.*;

public abstract class Inimigo extends EntidadeMovel {
    private int dano;
    private GerenciadorAudio audio;

    public Inimigo(int vidaMaxima, int velocidade, int dano) {
        super(vidaMaxima, velocidade);
        this.dano = Math.max(0, dano);
    }

    public int getDano() {
        audio = new GerenciadorAudio();
        
        audio.tocar("collision.wav");
        return dano;
    }

    public void setDano(int dano) {
        this.dano = Math.max(0, dano);
    }

    public abstract void atuar();

    public abstract void atacar(Abelha alvo);

    @Override
    public void act() {
        if (!estaViva()) {
            if (getWorld() != null) {
                getWorld().removeObject(this);
            }
            return;
        }
        atuar();
        virarNaBorda();
        manterDentroDaArena();
    }

    protected void virarNaBorda() {
        if (getWorld() == null) {
            return;
        }
        if (isAtEdge()) {
            turn(180);
        }
    }
    
    protected GreenfootImage criarSpriteVento(int largura, int altura, Color corPrincipal, Color corDestaque, int linhas) {
        GreenfootImage img = new GreenfootImage(largura, altura);
    
    
        img.setColor(new Color(corPrincipal.getRed(), corPrincipal.getGreen(), corPrincipal.getBlue(), 55));
        img.fillOval(2, 2, largura - 4, altura - 4);
    
        
        img.setColor(corPrincipal);
        for (int i = 0; i < linhas; i++) {
            int yBase = (altura / (linhas + 1)) * (i + 1);
            int xInicio = 6 + ((i * 7) % 10);
            int xFim = largura - 8 - ((i * 5) % 8);
    
            img.drawLine(xInicio, yBase, (xInicio + xFim) / 2, yBase - 4);
            img.drawLine((xInicio + xFim) / 2, yBase - 4, xFim, yBase);
        }
    
        img.setColor(corDestaque);
        img.drawOval(largura - 14, altura / 2 - 4, 8, 8);
    
        return img;
    }
}
import greenfoot.*;
import java.util.List;

/**
 * Jogador: voa pela arena coletando flores e desviando do vento.
 */
public class Abelha extends EntidadeMovel {
    private static final int VIDA_MAXIMA = 100;
    private static final int VELOCIDADE = 4;
    private static final int RAIO_ALERTA = 150;
    private static final int QUADROS_INVULNERAVEL_APOS_DANO = 60;

    private int raioAlerta;
    private int quadrosInvulneravel;
    private boolean emPerigo;

    public Abelha() {
        super(VIDA_MAXIMA, VELOCIDADE);
        this.raioAlerta = RAIO_ALERTA;
        this.quadrosInvulneravel = 0;
        this.emPerigo = false;
        setImage(carregarSprite());
    }

    @Override
    public void act() {
        lerTeclado();
        manterDentroDaArena();
        if (quadrosInvulneravel > 0) {
            quadrosInvulneravel--;
        }
        verificarPerigo();
    }

    @Override
    public void receberDano(int valor) {
        if (valor <= 0 || quadrosInvulneravel > 0) {
            return;
        }
        super.receberDano(valor);
        quadrosInvulneravel = QUADROS_INVULNERAVEL_APOS_DANO;
    }

    public void curar(int valor) {
        if (valor > 0) {
            setVida(getVida() + valor);
        }
    }

    public void empurrar(int dx, int dy) {
        setLocation(getX() + dx, getY() + dy);
        manterDentroDaArena();
    }

    public boolean isEmPerigo() {
        return emPerigo;
    }

    public List<Inimigo> getInimigosEmContato() {
        return getIntersectingObjects(Inimigo.class);
    }

    public List<ItemColetavel> getItensEmContato() {
        return getIntersectingObjects(ItemColetavel.class);
    }

    private void lerTeclado() {
        int dx = 0;
        int dy = 0;
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) {
            dx--;
        }
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) {
            dx++;
        }
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")) {
            dy--;
        }
        if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")) {
            dy++;
        }
        if (dx != 0 || dy != 0) {
            setLocation(getX() + dx * getVelocidade(), getY() + dy * getVelocidade());
        }
    }

    private void verificarPerigo() {
        emPerigo = !getObjectsInRange(raioAlerta, Inimigo.class).isEmpty();
    }

    private static GreenfootImage carregarSprite() {
        try {
            return new GreenfootImage("abelha.png");
        } catch (IllegalArgumentException e) {
            return criarSpritePadrao();
        }
    }

    private static GreenfootImage criarSpritePadrao() {
        GreenfootImage sprite = new GreenfootImage(44, 32);
        sprite.setColor(new Color(255, 255, 255, 210));
        sprite.fillOval(12, 0, 16, 14);
        sprite.setColor(new Color(250, 200, 30));
        sprite.fillOval(4, 8, 36, 22);
        sprite.setColor(Color.BLACK);
        sprite.fillRect(16, 10, 4, 18);
        sprite.fillRect(26, 10, 4, 18);
        return sprite;
    }
}

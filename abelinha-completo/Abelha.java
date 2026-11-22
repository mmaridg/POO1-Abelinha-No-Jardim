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

    

    
}

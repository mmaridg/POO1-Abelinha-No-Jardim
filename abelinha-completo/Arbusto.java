import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

/**
 * Elemento decorativo do jardim. A composição do MundoJogo mantém
 * uma lista de arbustos durante a existência da partida.
 *
 * Não movimenta nem aplica dano: sua responsabilidade é visual.
 */
public class Arbusto extends Actor {
    private int tamanho;

    /**
     * @param tamanho diâmetro aproximado da copa, em pixels (mínimo: 20)
     */
    public Arbusto(int tamanho) {
        if (tamanho < 20) {
            throw new IllegalArgumentException("O tamanho do arbusto deve ser de pelo menos 20.");
        }
        this.tamanho = tamanho;
    }

    public int getTamanho() {
        return tamanho;
    }

    /** Regra de negócio: não admite tamanho menor que 20 pixels. */
    public void setTamanho(int tamanho) {
        if (tamanho < 20) {
            throw new IllegalArgumentException("O tamanho do arbusto deve ser de pelo menos 20.");
        }
        this.tamanho = tamanho;
    }

    
    
}

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
        desenhar();
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
        desenhar();
    }

    /** Cria o sprite por código, sem exigir uma imagem externa. */
    private void desenhar() {
        int lado = tamanho + 12;
        GreenfootImage imagem = new GreenfootImage(lado, lado);
        int margem = 6;

        // Sombra e folhagem em camadas, centradas na imagem.
        imagem.setColor(new Color(45, 104, 45));
        imagem.fillOval(margem, margem + 5, tamanho, tamanho - 4);
        imagem.setColor(new Color(50, 138, 62));
        imagem.fillOval(margem + 3, margem + 1, tamanho - 6, tamanho - 9);
        imagem.setColor(new Color(87, 176, 78));
        imagem.fillOval(margem + 7, margem + 3, Math.max(7, tamanho / 2), Math.max(7, tamanho / 3));
        imagem.setColor(new Color(35, 95, 39));
        imagem.drawOval(margem, margem + 5, tamanho, tamanho - 4);
        setImage(imagem);
    }
}

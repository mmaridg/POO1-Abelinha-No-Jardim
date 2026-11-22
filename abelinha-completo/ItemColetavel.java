import greenfoot.*;

public abstract class ItemColetavel extends Actor {
    private int valor;

    public ItemColetavel(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public abstract void aplicarEfeito(Abelha abelha);

    protected void removerDoMundo() {
        if (getWorld() != null) {
            getWorld().removeObject(this);
        }
    }
}
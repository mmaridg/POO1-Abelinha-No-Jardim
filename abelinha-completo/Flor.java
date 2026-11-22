import greenfoot.*;

public class Flor extends ItemColetavel {
    public Flor() {
        super(10);
    }

    @Override
    public void aplicarEfeito(Abelha abelha) {
        if (abelha == null || !abelha.estaViva() || getWorld() == null) {
            return;
        }

        if (getWorld() instanceof MundoJogo) {
            MundoJogo mundo = (MundoJogo) getWorld();
            if (mundo.getPontuacao() != null) {
                mundo.getPontuacao().adicionarPontos(getValor());
            }
            if (mundo.getFases() != null) {
                mundo.getFases().registrarFlorColetada();
            }
        }

        removerDoMundo();
    }
}
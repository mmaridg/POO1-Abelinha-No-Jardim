import greenfoot.*;

public class Nectar extends ItemColetavel {
    public Nectar() {
        super(20);
    }

    @Override
    public void aplicarEfeito(Abelha abelha) {
        if (abelha == null || !abelha.estaViva() || getWorld() == null) {
            return;
        }

        abelha.curar(getValor());

        if (getWorld() instanceof MundoJogo) {
            MundoJogo mundo = (MundoJogo) getWorld();
            if (mundo.getPontuacao() != null) {
                mundo.getPontuacao().adicionarPontos(5);
            }
        }

        removerDoMundo();
    }
}
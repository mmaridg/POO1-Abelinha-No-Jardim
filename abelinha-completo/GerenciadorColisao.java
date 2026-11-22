import greenfoot.*;
import java.util.List;

public class GerenciadorColisao {
    private GerenciadorAudio audio;

    public GerenciadorColisao(GerenciadorAudio audio) {
        this.audio = audio;
    }

    public void verificar(Abelha abelha) {
        if (abelha == null || abelha.getWorld() == null || !abelha.estaViva()) {
            return;
        }

        // Itens coletaveis em contato com a abelha
        List<ItemColetavel> itens = abelha.getItensEmContato();
        for (ItemColetavel item : itens) {
            if (item != null && item.getWorld() != null) {
                item.aplicarEfeito(abelha);
                if (audio != null) {
                    audio.tocar("coletar.wav");
                }
            }
        }

        // Inimigos em contato com a abelha
        List<Inimigo> inimigos = abelha.getInimigosEmContato();
        for (Inimigo inimigo : inimigos) {
            if (inimigo != null && inimigo.getWorld() != null) {
                inimigo.atacar(abelha);
            }
        }
    }
}
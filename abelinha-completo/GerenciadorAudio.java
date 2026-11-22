import greenfoot.*;

public class GerenciadorAudio {
    private boolean somAtivo;
    private GreenfootSound musicaAtual;

    public GerenciadorAudio() {
        this.somAtivo = true;
    }

    public void tocar(String nomeDoSom) {
        if (somAtivo && nomeDoSom != null && !nomeDoSom.isEmpty()) {
            try {
                Greenfoot.playSound(nomeDoSom);
            } catch (Exception e) {
                // Evita erro caso o arquivo ainda nao exista em sounds/
            }
        }
    }

    public void tocarLoop(String nomeDoSom) {
        pararMusica();
        if (somAtivo && nomeDoSom != null && !nomeDoSom.isEmpty()) {
            try {
                musicaAtual = new GreenfootSound(nomeDoSom);
                musicaAtual.playLoop();
            } catch (Exception e) {
                musicaAtual = null;
            }
        }
    }

    public void pararMusica() {
        if (musicaAtual != null) {
            musicaAtual.stop();
            musicaAtual = null;
        }
    }

    public void setSomAtivo(boolean ativo) {
        this.somAtivo = ativo;
        if (!ativo) {
            pararMusica();
        }
    }

    public boolean isSomAtivo() {
        return somAtivo;
    }
}
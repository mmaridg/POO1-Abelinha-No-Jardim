import greenfoot.World;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Mundo principal do jogo Abelhinha no Jardim.
 *
 * Composição: o mundo possui HUD, fases, placar, colisões, áudio,
 * abelha, arbustos e o estado da partida.
 * Delegação: regras específicas ficam nos respectivos gerenciadores.
 *
 * Requer as classes dos demais integrantes conforme o diagrama UML:
 * Abelha, PainelHUD, GerenciadorFases, GerenciadorPontuacao,
 * GerenciadorColisao e GerenciadorAudio.
 */
public class MundoJogo extends World {
    private PainelHUD hud;
    private GerenciadorFases fases;
    private GerenciadorPontuacao pontuacao;
    private GerenciadorColisao colisao;
    private GerenciadorAudio audio;
    private List<Arbusto> arbustos;
    private Abelha abelha;
    private EstadoJogo estado;

    /** Cria uma arena com 800 x 600 pixels e escala de 1 pixel por célula. */
    public MundoJogo() {
        super(800, 600, 1);

        estado = EstadoJogo.JOGANDO;
        arbustos = new ArrayList<Arbusto>();
        criarCenario();

        // Componentes próprios: o mundo controla seu ciclo de vida.
        pontuacao = new GerenciadorPontuacao();
        audio = new GerenciadorAudio();
        colisao = new GerenciadorColisao(audio);
        hud = new PainelHUD(this);

        // A abelha deve existir antes de o gerenciador gerar as fases.
        abelha = new Abelha();
        addObject(abelha, getWidth() / 2, getHeight() / 2);
        criarArbustos();

        fases = new GerenciadorFases(this);
        fases.iniciarProximaOnda();
        atualizarHud();
    }

    /** Executado a cada ciclo pelo Greenfoot. */
    @Override
    public void act() {
        if (estado != EstadoJogo.JOGANDO) {
            return;
        }

        // A detecção é delegada ao componente dedicado, não à classe World.
        if (abelha != null && abelha.getWorld() == this) {
            colisao.verificar(abelha);
        }

        if (abelha == null || abelha.getVida() <= 0) {
            perder();
            return;
        }

        // Uma fase finalizada pode encerrar a partida ou iniciar a próxima.
        if (fases.isJogoConcluido()) {
            vencer();
            return;
        }
        if (fases.isOndaConcluida()) {
            fases.iniciarProximaOnda();
        }

        atualizarHud();
    }

    public GerenciadorPontuacao getPontuacao() {
        return pontuacao;
    }

    public GerenciadorFases getFases() {
        return fases;
    }

    public Abelha getAbelha() {
        return abelha;
    }

    public EstadoJogo getEstado() {
        return estado;
    }

    /** Termina a partida em vitória, uma única vez. */
    public void vencer() {
        if (estado != EstadoJogo.JOGANDO) {
            return;
        }
        estado = EstadoJogo.VITORIA;
        atualizarHud();
        hud.mostrarVitoria();
        Greenfoot.stop();
    }

    /** Termina a partida em derrota, uma única vez. */
    public void perder() {
        if (estado != EstadoJogo.JOGANDO) {
            return;
        }
        estado = EstadoJogo.DERROTA;
        atualizarHud();
        hud.mostrarDerrota();
        Greenfoot.stop();
    }

    /** Centraliza a atualização de interface; HUD controla a apresentação. */
    private void atualizarHud() {
        int vidaAtual = (abelha == null) ? 0 : Math.max(0, abelha.getVida());
        int pontosAtuais = pontuacao.getPontos();
        int ondaAtual = fases.getOndaAtual();
        int floresRestantes = fases.getFloresRestantes();
        hud.atualizar(vidaAtual, pontosAtuais, ondaAtual, floresRestantes);
    }

    /** Os arbustos permanecem como parte estrutural do cenário. */
    private void criarArbustos() {
        int[][] posicoes = {
            {65, 85, 50}, {180, 70, 38}, {325, 70, 42},
            {520, 80, 46}, {735, 95, 50}, {80, 315, 42},
            {720, 315, 45}, {80, 525, 52}, {230, 535, 38},
            {560, 530, 42}, {735, 510, 54}
        };

        for (int[] posicao : posicoes) {
            Arbusto arbusto = new Arbusto(posicao[2]);
            arbustos.add(arbusto);
            addObject(arbusto, posicao[0], posicao[1]);
        }
    }

    /** Fundo desenhado em Java: não depende de arquivos de imagem. */
    private void criarCenario() {
        GreenfootImage fundo = new GreenfootImage(getWidth(), getHeight());
        fundo.setColor(new Color(191, 226, 151));
        fundo.fill();

        // Faixa de grama escura que delimita visualmente o jardim.
        fundo.setColor(new Color(137, 193, 111));
        fundo.fillRect(0, 0, getWidth(), 24);
        fundo.fillRect(0, getHeight() - 24, getWidth(), 24);
        fundo.fillRect(0, 0, 24, getHeight());
        fundo.fillRect(getWidth() - 24, 0, 24, getHeight());

        // Pequenos detalhes fixos para dar aspecto de gramado.
        fundo.setColor(new Color(161, 207, 123));
        for (int x = 42; x < getWidth() - 35; x += 59) {
            for (int y = 39; y < getHeight() - 35; y += 53) {
                fundo.fillOval(x, y, 5, 3);
            }
        }
        setBackground(fundo);
    }
}

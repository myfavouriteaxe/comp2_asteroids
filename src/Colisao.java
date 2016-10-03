// Classe nao instanciavel
public abstract class Colisao {
    // Metodo estatico para verificar colisao entre nave e asteroide
    public static boolean testaColisaoNaveAsteroide(Nave nave, Asteroide asteroide) {
        boolean colidiu = false;
        if (Math.sqrt(Math.pow(asteroide.x - nave.x, 2) + Math.pow(asteroide.y - nave.y, 2)) < asteroide.raio+5) {
            colidiu = true;
        }
        return colidiu;
    }

    // Metodo estatico para verificar a colisao entre o tiros e o asteroide
    public static boolean testaColisaoTiroAsteroide(Tiro tiro, Asteroide asteroide) {
        boolean colidiu = false;
        if (Math.sqrt(Math.pow(tiro.x - asteroide.x, 2) + Math.pow(tiro.y - asteroide.y, 2)) < asteroide.raio) {
            colidiu = true;
        }
        return colidiu;
    }

}

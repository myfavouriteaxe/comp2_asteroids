
public class Colisao {
    // Metodo para verificar colisao entre nave e asteroide
    public boolean testaColisaoNaveAsteroide(Nave nave, Asteroide asteroide) {
        boolean colidiu = false;
        // Distancia entre a nave e o asteroide menor que o raio do asteroide mais cinco
        if (Math.sqrt(Math.pow(asteroide.x - nave.x, 2) + Math.pow(asteroide.y - nave.y, 2)) < asteroide.raio+5) {
            colidiu = true;
        }
        return colidiu;
    }

    // Metodo para verificar a colisao entre o tiros e o asteroide
    public boolean testaColisaoTiroAsteroide(Tiro tiro, Asteroide asteroide) {
        boolean colidiu = false;
        // Distance entre o tiro e o asteroide menor que o raio do asteroide mais uma constante de facilidade
        if (Math.sqrt(Math.pow(tiro.x - asteroide.x, 2) + Math.pow(tiro.y - asteroide.y, 2)) < asteroide.raio+ 3) {
            colidiu = true;
        }
        return colidiu;
    }

}

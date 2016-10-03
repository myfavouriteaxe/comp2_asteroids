import java.util.Set;
import java.util.HashSet;
public class Jogo {
	// Conjunto de asteroides e tiros
	Set<Asteroide> asteroides = new HashSet<Asteroide>();
	Set<Tiro> tiros = new HashSet<Tiro>();
	// Nave
	Nave nave = new Nave();

    // Conjuntos de elementos fora do jogo
	Set<Asteroide> asteroidesFora = new HashSet<Asteroide>();
	Set<Tiro> tirosFora = new HashSet<Tiro>();

	// Atributos gerais do jogo
	boolean vivo = true;
	int vidas = 3;
	boolean cooldown = false;
	double cooldownTempo = 0;
	
	public Jogo() {
		// Instancia os asteroides e os adiciona ao hashset
		for(int i = 0; i < 6; i++) {
			this.asteroides.add(new Asteroide());
		}
	}
	
	public String getTitulo () {
		return "Asteroids";
	}
	
	public int getAltura () {
		return 600;
	}
	
	public int getLargura () {
		return 800;
	}
	
	public void tecla(String tecla) {
		if (vivo && !cooldown && tecla.equals(" ")) {
			tiros.add(new Tiro(nave));
			cooldown = true;
		}
	}
	
	public void tique(Set<String> teclas, double dt) {
		if (vidas <= 0) {
			vivo = false;
		}

		// Teste de colisao entre asteroide e nave
		for (Asteroide asteroide : asteroides) {
			if (vivo) {
				if (Colisao.testaColisaoNaveAsteroide(nave, asteroide)) {
					vidas--;
					asteroidesFora.add(asteroide);
					nave = new Nave();
				}
			}
		}

		// Move os asteroides do hashset
		for (Asteroide asteroide : asteroides) {
			asteroide.mover(this, dt);
		}
		// Move os tiros
		for (Tiro tiro : this.tiros) {
			tiro.move(this, dt);
			if (tiro.tiroForaDaTela) {
				tirosFora.add(tiro);
			}
		}
		
		nave.move(this, dt);
		
		// Aceleração da nave
		if (teclas.contains("up")) {
			nave.motorLigado = true ;
			nave.acelera(dt);
		} else {
			nave.motorLigado = false;
		}
		// Gira a nave
		if (teclas.contains("left")) {
			nave.virarEsquerda(dt);
		}
		if (teclas.contains("right")) {
			nave.virarDireita(dt);
		}

		// Mecanica de cooldown pra evitar spamm de tiros
		if (cooldown) {
			cooldownTempo += dt;
			if (cooldownTempo >= 0.5) {
				cooldown = false;
				cooldownTempo = 0;
			}
		}

		// Remove os asteroids do hashset
		asteroides.removeAll(asteroidesFora);
		if (asteroidesFora.size() > 20) {
			asteroidesFora = new HashSet<Asteroide>();
		}
		// Remove os tiros do hashset
		tiros.removeAll(tirosFora);
		if (tirosFora.size() > 20) {
			tirosFora = new HashSet<Tiro>();
		}
	}
	
	public void desenhar(Tela tela) {
		// Desenha os asteroides do hashset
		for (Asteroide asteroide : this.asteroides) {
			asteroide.desenhar(tela);
		}
		// Desenha os tiros
		for (Tiro tiro : this.tiros) {
			tiro.desenhar(tela);
		}
		
		// Desenha a nave
		if(vivo) {
			this.nave.desenhar(tela);
		}
	}
	
	public static void main(String[] args) {
		new Motor(new Jogo());
	}

}

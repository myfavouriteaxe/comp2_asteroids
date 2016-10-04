import java.util.Set;
import java.util.HashSet;
public class Jogo {
	// Conjunto de asteroides e tiros
	Set<Asteroide> asteroides = new HashSet<Asteroide>();
	Set<Tiro> tiros = new HashSet<Tiro>();
	// Nave
	Nave nave = new Nave();

	// Asteroides a incluir apos a destruiçao de um maior
	Set<Asteroide> asteroidesNovos = new HashSet<Asteroide>();
    // Conjuntos de elementos fora do jogo pra serem removidos
	Set<Asteroide> asteroidesFora = new HashSet<Asteroide>();
	Set<Tiro> tirosFora = new HashSet<Tiro>();

	// Atributos gerais do jogo
	boolean vivo = true;
	int vidas = 3;
	int pontos = 0;
	double asteroidesSpawnTempo = 0;
	int asteroidesDestruidos = 0;
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

		for (Asteroide asteroide : asteroides) {
			// Teste de colisao entre asteroide e nave
			if (vivo) {
				if (Colisao.testaColisaoNaveAsteroide(nave, asteroide)) {
					vidas--;
					asteroidesDestruidos++;
					asteroidesFora.add(asteroide);
					nave = new Nave();
				}
			}

			// Teste de colisao entre tiro e asteroide
			for (Tiro tiro : tiros) {
				if (Colisao.testaColisaoTiroAsteroide(tiro, asteroide)) {
					//pontos += 20;
					asteroidesDestruidos++;
					asteroidesFora.add(asteroide);
					tirosFora.add(tiro);
					switch (asteroide.tamanho) {
						// Pontuaçao maior quanto menor for o asteroide
						case 1:
							pontos += 40;
							break;
						case 2:
							pontos += 30;
							break;
						case 3:  // Dois asteroides de tamanho 1 com direçoes opostas
							asteroidesNovos.add(new Asteroide(asteroide.x, asteroide.y, asteroide.vx, -asteroide.vy, 1, asteroide.cor));
							asteroidesNovos.add(new Asteroide(asteroide.x, asteroide.y, -asteroide.vx, asteroide.vy, 1, asteroide.cor));
							pontos += 20;
							break;
						case 4:  // Um asteroide de tamanho 2 e outro de 1
							asteroidesNovos.add(new Asteroide(asteroide.x, asteroide.y, asteroide.vx, -asteroide.vy, 2, asteroide.cor));
							asteroidesNovos.add(new Asteroide(asteroide.x, asteroide.y, -asteroide.vx, asteroide.vy, 1, asteroide.cor));
							pontos += 10;
							break;
					}
					if (pontos % 1000 == 0) {
						vidas++;
					}
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
			if (cooldownTempo >= 0.125) {
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
		// Dois novos asteroides a cada 5 segundos
		asteroidesSpawnTempo += dt;
		if (vivo && asteroidesSpawnTempo >= 10) {
			asteroidesNovos.add(new Asteroide(3));
			asteroidesNovos.add(new Asteroide(4));
			asteroidesSpawnTempo = 0;
		}

		if (asteroidesDestruidos >= 3) {
			asteroidesNovos.add(new Asteroide());
			asteroidesDestruidos = 0;
		}

		// Inclui os asteroides novos no jogo
		asteroides.addAll(asteroidesNovos);
		asteroidesNovos = new HashSet<Asteroide>();
	}
	
	public void desenhar(Tela tela) {
		// Desenha as informaçoes na tela
		tela.texto(String.valueOf(vidas), 730, 50, 40, Cor.BRANCO);
		tela.texto(String.valueOf(pontos), 50, 50, 40, Cor.BRANCO);

		if (!vivo) {
			tela.texto("FIM DE JOGO", 150, 300, 70, Cor.BRANCO);
		}

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

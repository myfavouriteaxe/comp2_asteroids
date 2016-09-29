import java.util.Set;
import java.util.HashSet;
public class Jogo {
	// Conjunto de asteroides
	Set<Asteroide> asteroides = new HashSet<Asteroide>();
	// Nave
	Nave nave = new Nave();
	
	// Atributos gerais do jogo
	int vidas = 3;
	
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
		
	}
	
	public void tique(Set<String> teclas, double dt) {
		// Move os asteroides do hashset
		for (Asteroide asteroide : this.asteroides) {
			asteroide.mover(this, dt);
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
		
	}
	
	public void desenhar(Tela tela) {
		// Desenha os asteroides do hashset
		for (Asteroide asteroide : this.asteroides) {
			asteroide.desenhar(tela);
		}
		
		// Desenha a nave
		if(this.vidas > 0) {
			this.nave.desenhar(tela);
		}
	}
	
	public static void main(String[] args) {
		new Motor(new Jogo());
	}

}

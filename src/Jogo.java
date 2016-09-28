import java.util.Set;
import java.util.HashSet;
public class Jogo {
	// Conjunto de asteroides
	Set<Asteroide> asteroids = new HashSet<Asteroide>();
	
	public Jogo() {
		// Instancia os asteroides e os adiciona ao hashset
		for(int i = 0; i < 6; i++) {
			this.asteroids.add(new Asteroide());
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
		for (Asteroide asteroide : this.asteroids) {
			asteroide.mover(this, dt);
		}
		
	}
	
	public void desenhar(Tela tela) {
		// Desenha os asteroides do hashset
		for (Asteroide asteroide : this.asteroids) {
			asteroide.desenhar(tela);
		}
	}
	
	public static void main(String[] args) {
		new Motor(new Jogo());
	}

}

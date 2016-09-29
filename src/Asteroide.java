
public class Asteroide {
	// Inerentes ao asteróide
	double x, y;
	int tamanho;
	int cor;
	double vx, vy, vr;
	double dir;
	boolean vivo;
	
	// Relativos diretamente ao sprite
	int xSprite, ySprite, largSprite, altSprite;
		
	
	public Asteroide () {
		// Inicializa cada asteróide com valores aleatórios
		this.x = Math.random() * 700;
		this.y = Math.random() * 500;
		this.tamanho = (int) Math.round(Math.random() * 3) + 1; // Tamanho vai de 1 a 4
		
		this.vx = Math.random() * 300 + 1;
		this.vy = Math.random() * 300 + 1;
		this.vr = Math.random() * 2*Math.PI;
		
		this.cor = (int) Math.round(Math.random() * 10);
		this.vivo = true;
	}
	
	public void mover(Jogo jogo, double dt) {
		if(!vivo) {
			return;
		}
		// Movimentação simples
		x += vx * dt;
		y += vy * dt;
		// Se asteróide sair da tela, volta do outro lado
		if(x > jogo.getLargura()) {
			x = 0;
		} else if (x < 0) {
			x = jogo.getLargura();
		}
		if(y > jogo.getAltura()) {
			y = 0;
		} else if (y < 0) {
			y = jogo.getAltura();
		}
		// Giro do asteróide
		dir += vr * dt;
	}
	
	public void desenhar(Tela t) {
		// Não desenha se o asteróide foi destruído
		if(!vivo) { 
			return; 
		}
		// Corrige o desalinhamento das alturas dos sprites
		if (tamanho == 1) {
			ySprite = 4 + (48 * cor);
		} else {
			ySprite = 48 * cor;
		}
		
		// Define as posições no spritesheet em função do tamanho do asteróide
		switch(tamanho) {
		case 1:
			xSprite = 4;
			largSprite = altSprite = 8;
			break;
		case 2:
			xSprite = 17;
			largSprite = altSprite = 15;
			break;
		case 3:
			xSprite = largSprite = altSprite = 32;
			break;
		case 4:
			xSprite = 65;
			largSprite = altSprite = 47;
		}
		
		// Chama o método de Tela pra carregar o sprite
		t.imagem("asteroids.png", xSprite, ySprite, largSprite, altSprite, dir, x, y);
	}
}

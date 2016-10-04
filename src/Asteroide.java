
public class Asteroide {
	// Inerentes ao asteróide
	double x, y;
	int tamanho;
	int cor;
	double vx, vy, vr;
	double dir;
	double raio;

	// Relativos diretamente ao sprite
	int xSprite, ySprite, largSprite, altSprite;
		
	
	public Asteroide () {
		// Inicializa cada asteróide com valores aleatórios
		this.x =  Math.random() * 700;
		this.y =  Math.random() * 500;
		this.tamanho = (int) Math.round(Math.random() * 3) + 1; // Tamanho vai de 1 a 4
		// Define o raio a ser checado nas colisoes como metade do tamanho dos sprites
		switch (this.tamanho) {
			case 1:
				this.raio = 4.0;
				break;
			case 2:
				this.raio = 7.5;
				break;
			case 3:
				this.raio = 16.0;
				break;
			case 4:
				this.raio = 23.5;
				break;
		}

		this.vx = Math.random() * 300 + 1;
		this.vy = Math.random() * 300 + 1;
		this.vr = Math.random() * 2*Math.PI + 0.00001*2*Math.PI;
		
		this.cor = (int) Math.round(Math.random() * 10);
	}

	public Asteroide (int tamanho) {
		// Inicializa cada asteróide com valores aleatórios
		this.x =  Math.random() * 700;
		this.y =  Math.random() * 500;
		this.tamanho = tamanho;
		// Define o raio a ser checado nas colisoes como metade do tamanho dos sprites
		switch (this.tamanho) {
			case 1:
				this.raio = 4.0;
				break;
			case 2:
				this.raio = 7.5;
				break;
			case 3:
				this.raio = 16.0;
				break;
			case 4:
				this.raio = 23.5;
				break;
		}

		this.vx = Math.random() * 300 + 1;
		this.vy = Math.random() * 300 + 1;
		this.vr = Math.random() * 2*Math.PI + 0.00001*2*Math.PI;

		this.cor = (int) Math.round(Math.random() * 10);
	}

	public Asteroide (double x, double y, double vx, double vy, int tamanho, int cor) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.vr = Math.random() * 2*Math.PI + 0.00001*2*Math.PI;
		this.tamanho = tamanho;
		this.cor = cor;

		// Define o raio a ser checado nas colisoes como metade do tamanho dos sprites
		switch (this.tamanho) {
			case 1:
				this.raio = 4.0;
				break;
			case 2:
				this.raio = 7.5;
				break;
			case 3:
				this.raio = 16.0;
				break;
			case 4:
				this.raio = 23.5;
				break;
		}
	}

	public void mover(Jogo jogo, double dt) {
		// Movimentação simples
		if (jogo.vivo) { // Asteroides congelados girando em caso de derrota
			x += vx * dt;
			y += vy * dt;
		}
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

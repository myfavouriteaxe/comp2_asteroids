
public class Nave {
	double x, y, dir;
	double vx, vy;
	boolean motorLigado;
	int aceleracao = 100;
	int velocidadeMax = 200;
	
	
	public Nave () {
		this.x = 400;
		this.y = 300;
		this.vx = this.vy = 0;
		this.motorLigado = false;
		
	}
	
	public void acelera(double dt) {
		// Componentes vertical e horizontal respectivamente da velocidade
		vy -= Math.cos(dir) * dt * aceleracao;
		vx += Math.sin(dir) * dt * aceleracao;
		// Testa limites superior e inferior das velocidades
		if (vx > velocidadeMax) {
			vx = velocidadeMax;
		} else if (vx < -velocidadeMax) {
			vx = -velocidadeMax;
		}
		if (vy > velocidadeMax) {
			vy = velocidadeMax;
		} else if (vy < -velocidadeMax) {
			vy = -velocidadeMax;
		}
	}
	
	public void move(Jogo jogo, double dt) {
		// Movimentação simples
		x += vx * dt;
		y += vy * dt;
		// Se nave sair da tela, volta do outro lado
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
	}
	
	// Giro da nave
	public void virarEsquerda(double dt) {
		this.dir -= Math.PI * dt;
	}
	public void virarDireita(double dt) {
		this.dir += Math.PI * dt;
	}
	
	// Escolha dos sprites certos em função do motor
	public void desenhar (Tela t) {
		if(motorLigado) {
			t.imagem("naves.png", 135, 0, 50, 60, dir, x, y);
		} else {
			t.imagem("naves.png", 70, 0, 50, 60, dir, x, y);
		}
	}
}


public class Tiro {
	double x, y;
	double vx, vy;
	Cor cor;
	
	public Tiro (Nave n) {
		this.x = n.xBico;
		this.y = n.yBico; // Correção pro tiro ser instanciado na ponta da nave
		this.cor = new Cor(255, 255, 255);

		this.vx = 700*Math.sin(n.dir) + n.vx;
		this.vy = -700*Math.cos(n.dir) + n.vy;
	}
	
	public void move (double dt) {
		x += vx * dt;
		y += vy * dt;
	}
	
	public void desenhar (Tela t) {
		t.circulo(x, y, 1, cor);
	}
}

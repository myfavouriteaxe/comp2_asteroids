
public class Nave {
	double x, y, dir;
	double vx, vy;
	boolean motorLigado;
	
	public Nave () {
		this.x = 400;
		this.y = 300;
		this.vx = this.vy = 0;
		this.motorLigado = false;
		
	}
	
	public void desenhar (Tela t) {
		if(motorLigado) {
			t.imagem("naves.png", 135, 0, 50, 60, dir, x, y);
		} else {
			t.imagem("naves.png", 70, 0, 50, 60, dir, x, y);
		}
	}
}

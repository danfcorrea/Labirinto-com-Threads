
public class Labirinto{
	
	int tamanho;
	double probabilidade = 0.5;
	int[][]labirinto;
	
	public Labirinto(int tamanho) {
		this.tamanho = tamanho;
		GeraLabirinto();
	}
	
	public void GeraLabirinto() {
		labirinto = new int[tamanho][tamanho];
		for (int i = 0; i < tamanho; i++) {
			for (int j = 0; j < tamanho; j++) {
				if(Math.random() > probabilidade) {
					labirinto[i][j] = 0;
				}else {
					labirinto[i][j] = 1;
				}
			}
		}
		if(labirinto[1][0] == 0 && labirinto[0][1] == 0) {
			GeraLabirinto();
		}
		labirinto[0][0] = -1;
	}
	
	public synchronized void ImprimeLabirinto() {
		try {
			Thread.sleep((int)(Math.random() * 300));
		} catch (InterruptedException e) {
			// TODO: nada
		}
		for (int i = 0; i < tamanho; i++) {
			for (int j = 0; j < tamanho; j++) {
				if(labirinto[i][j] == 0) {
					System.out.print("@ ");
				}else if(labirinto[i][j] == -1) {
					System.out.print("Q ");
				}else if(labirinto[i][j] == -2) {
					System.out.print("R ");
				}else if(labirinto[i][j] == -3) {
					System.out.print("x ");
				}else {
					System.out.print(". ");
				}				
			}
			System.out.println();
		}
		for (int i = 0; i < 2; ++i)  
		       System.out.println();
	}
	
}

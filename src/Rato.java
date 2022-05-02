import java.util.Random;
import java.util.Stack;

public class Rato implements Runnable {
	Random r = new Random();
	int posI = 0;
	int posJ = 0;
	Caminho pos;
	boolean andou = true;
	boolean achou = false;
	Stack<Caminho> memoria = new Stack<Caminho>();
	Labirinto l;

	public Rato(Labirinto l) {
		this.l = l;
	}

	public void Andar() {
		while (!achou) {
			if (!tentarEsquerda(l, posI, posJ)) {
				if (achou) {
					break;
				} else if (!tentarCima(l, posI, posJ)) {
					if (achou) {
						break;
					} else if (!tentarDireita(l, posI, posJ)) {
						if (achou) {
							break;
						} else if (!tentarBaixo(l, posI, posJ)) {
							if (!memoria.empty()) {
								pos = memoria.pop();
								l.labirinto[posI][posJ] = -3;
								posI = pos.x;
								posJ = pos.y;
								l.labirinto[posI][posJ] = -2;
								l.ImprimeLabirinto();
							} else {
								break;
							}
						}
					}
				}
			}
		}
	}

	public boolean tentarEsquerda(Labirinto l, int posicaoI, int posicaoJ) {
		andou = false;
		if (posicaoJ - 1 == -1) {
		} else if (l.labirinto[posicaoI][posicaoJ - 1] == 1) {
			l.labirinto[posicaoI][posicaoJ] = -3;
			l.labirinto[posicaoI][posicaoJ - 1] = -2;
			pos = new Caminho(posicaoI, posicaoJ);
			memoria.push(pos);
			posJ = posJ - 1;
			andou = true;
			l.ImprimeLabirinto();
			try {
				Thread.sleep((int) (Math.random() * 300));
			} catch (InterruptedException e) {
				// TODO: nada
			}
		} else if (l.labirinto[posicaoI][posicaoJ - 1] == -1) {
			l.labirinto[posicaoI][posicaoJ] = -3;
			l.labirinto[posicaoI][posicaoJ - 1] = -2;
			l.ImprimeLabirinto();
			achou = true;
		}
		return andou;
	}

	public boolean tentarDireita(Labirinto l, int posicaoI, int posicaoJ) {
		andou = false;
		if (posicaoJ + 1 == l.tamanho) {
		} else if (l.labirinto[posicaoI][posicaoJ + 1] == 1) {
			l.labirinto[posicaoI][posicaoJ] = -3;
			l.labirinto[posicaoI][posicaoJ + 1] = -2;
			pos = new Caminho(posicaoI, posicaoJ);
			memoria.push(pos);
			posJ = posJ + 1;
			andou = true;
			l.ImprimeLabirinto();
			try {
				Thread.sleep((int) (Math.random() * 300));
			} catch (InterruptedException e) {
				// TODO: nada
			}
		} else if (l.labirinto[posicaoI][posicaoJ + 1] == -1) {
			l.labirinto[posicaoI][posicaoJ] = -3;
			l.labirinto[posicaoI][posicaoJ + 1] = -2;
			l.ImprimeLabirinto();
			achou = true;
		}
		return andou;
	}

	public boolean tentarCima(Labirinto l, int posicaoI, int posicaoJ) {
		andou = false;
		if (posicaoI - 1 == -1) {
		} else if (l.labirinto[posicaoI - 1][posicaoJ] == 1) {
			l.labirinto[posicaoI][posicaoJ] = -3;
			l.labirinto[posicaoI - 1][posicaoJ] = -2;
			pos = new Caminho(posicaoI, posicaoJ);
			memoria.push(pos);
			posI = posI - 1;
			andou = true;
			l.ImprimeLabirinto();
			try {
				Thread.sleep((int) (Math.random() * 300));
			} catch (InterruptedException e) {
				// TODO: nada
			}
		} else if (l.labirinto[posicaoI - 1][posicaoJ] == -1) {
			l.labirinto[posicaoI][posicaoJ] = -3;
			l.labirinto[posicaoI - 1][posicaoJ] = -2;
			l.ImprimeLabirinto();
			achou = true;
		}
		return andou;
	}

	public boolean tentarBaixo(Labirinto l, int posicaoI, int posicaoJ) {
		andou = false;
		if (posicaoI + 1 == l.tamanho) {
		} else if (l.labirinto[posicaoI + 1][posicaoJ] == 1) {
			l.labirinto[posicaoI][posicaoJ] = -3;
			l.labirinto[posicaoI + 1][posicaoJ] = -2;
			pos = new Caminho(posicaoI, posicaoJ);
			memoria.push(pos);
			posI = posI + 1;
			andou = true;
			l.ImprimeLabirinto();
			try {
				Thread.sleep((int) (Math.random() * 300));
			} catch (InterruptedException e) {
				// TODO: nada
			}
		} else if (l.labirinto[posicaoI + 1][posicaoJ] == -1) {
			l.labirinto[posicaoI][posicaoJ] = -3;
			l.labirinto[posicaoI + 1][posicaoJ] = -2;
			l.ImprimeLabirinto();
			achou = true;
		}
		return andou;
	}

	public void GeraPosicao() {
		posI = r.nextInt(l.tamanho - 1);
		posJ = r.nextInt(l.tamanho - 1);
		if(posI == 0 && posJ == 0) {
			GeraPosicao();
		}

		l.labirinto[posI][posJ] = -2;
	}

//	public int GeraNumero(int min, int max) {
//		int valor = (int) Math.round(Math.random() * (max - min));
//		return valor + min;
//	}

	@Override
	public void run() {
		GeraPosicao();
		Andar();
		if (achou) {
			System.out.print("Queijo Encontrado");
			System.exit(1);
		}
	}

}

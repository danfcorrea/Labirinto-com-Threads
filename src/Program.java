import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		int tamanho;
		int ratos;
		System.out.print("Informe o tamanho do labirinto: ");
		tamanho = ler.nextInt();
		System.out.print("Informe a quantidade de ratos: ");
		ratos = ler.nextInt();
		Labirinto l = new Labirinto(tamanho);
		for (int i = 0; i < ratos; i++) {
			Rato r = new Rato(l);
			new Thread(r).start();
		}
	}
}

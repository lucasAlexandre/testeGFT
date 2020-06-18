package br.teste.gft.main;

import java.util.Scanner;
import br.teste.gft.Comanda;

public class Mesa {

	public static void main(String[] args) {

		Scanner sc1 = new Scanner(System.in);

		String entrada = new String();

		System.out.print("Input:");

		entrada = sc1.nextLine();

		sc1.close();
		
		Comanda comandaUsuario = new Comanda();
		comandaUsuario.separaPedido(entrada);

		System.out.println("Output:"+comandaUsuario.imprimeComanda());
	}
}

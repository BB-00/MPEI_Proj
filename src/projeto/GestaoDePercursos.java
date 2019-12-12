package projeto;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GestaoDePercursos {

	public static void main(String[] args) throws IOException {

		String[] string = new String[99];
		
		Scanner scanner = new Scanner(new File("src/projeto/Paises.csv"));
		
		int count=0;
		while(scanner.hasNextLine()) {
			String [] split = scanner.nextLine().split(",");
			string[count]=split[0];
			count++;
		}
		
		scanner.close();
		
		new Interfaces(string);
		
	}

}

package projeto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CreateDataBaseFile {
	
	static LinkedList<Percurso> listPercursos = new LinkedList<Percurso>();
	
	public static void main(String[] args) throws IOException {
        
        //numeros de percursos da base de dados
        for(int i=0; i<15; i++) {
        	readFile("src/projeto/Paises.csv");
        }
        writeFile(listPercursos,"src/projeto/Percursos2.txt");
	}
	
	//le o ficheiro e adiciona a uma lista de percursos
	public static void readFile(String pathFile) throws IOException {
		
		List<String> linhas = new ArrayList<String>();
		
		Scanner scanner = new Scanner(new File(pathFile));
		
		
		while(scanner.hasNextLine()) {
			linhas.add(scanner.nextLine());
		}
		
		scanner.close();
			
		Random r = new Random();
	    int result = r.nextInt(5-2) + 2;
	    int i1, i2, i3, i4;
	    
	    if(result==2) {
		    i1 = r.nextInt(100-1);
		    do {
		    	i2 = r.nextInt(100-1);
		    }while(i2==i1);
		    
		    String[] split1 = linhas.get(i1).split(",");
		    String[] split2 = linhas.get(i2).split(",");
		    
		    listPercursos.add(new Percurso(split1[0], split2[0], split1[1]+split2[1]));

	    }
	    else if(result==3) {
		    i1 = r.nextInt(100-1);
		    do {
		    	i2 = r.nextInt(100-1);
		    }while(i2==i1);
		    do {
		    	i3 = r.nextInt(100-1);
		    }while(i3==i1 || i3==i2);
		    
		    String[] split1 = linhas.get(i1).split(",");
		    String[] split2 = linhas.get(i2).split(",");
		    String[] split3 = linhas.get(i3).split(",");
		    
		    listPercursos.add(new Percurso(split1[0], split2[0], split3[0], split1[1]+split2[1]+split3[1]));
	    }   
	    else if(result==4) {
		    i1 = r.nextInt(10-1);
		    do {
		    	i2 = r.nextInt(100-1);
		    }while(i2==i1);
		    do {
		    	i3 = r.nextInt(100-1);
		    }while(i3==i1 || i3==i2);
		    do {
		    	i4 = r.nextInt(100-1);
		    }while(i4 == i3 || i4 == i2 || i4 == i1);
		    
		    String[] split1 = linhas.get(i1).split(",");
		    String[] split2 = linhas.get(i2).split(",");
		    String[] split3 = linhas.get(i3).split(",");
		    String[] split4 = linhas.get(i4).split(",");
		    
		    listPercursos.add(new Percurso(split1[0], split2[0], split3[0], split4[0], split1[1]+split2[1]+split3[1]+split4[1]));
	    }
			
	}
	
	//escreve o toString() dos percursos num ficheiro 
	public static void writeFile (LinkedList<Percurso> p, String pathFile) throws IOException {
		
		String a = "Codigo \t Origem \t Destino	\n";
		Path file = Paths.get(pathFile);
		Files.write(file, a.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		
		for(Percurso per : p) {
			Files.write(file, per.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		}
	}

}
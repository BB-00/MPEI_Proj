package projeto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Percurso {
	
	private String pais1;
	private String pais2;
	private String pais3;
	private String pais4;
	private String cod;
	
	public Percurso(String pais1, String pais2, String cod ) {
		this.pais1 = pais1;
		this.pais2 = pais2;
		this.cod = cod;
		
	}
	
	public Percurso(String pais1,String pais2, String pais3, String cod) {
		
		this.pais1 = pais1;
		this.pais2 = pais2;
		this.pais3 = pais3;
		this.cod = cod;
		
	}
	
	public Percurso(String pais1,String pais2, String pais3, String pais4, String cod) {
		
		this.pais1 = pais1;
		this.pais2 = pais2;
		this.pais3 = pais3;
		this.pais4 = pais4;
		this.cod = cod;
		
	}
	
	//AINDA FALTA ACABARRRRR!!
	public static String getPercurso (String cod) throws FileNotFoundException {
		
		String[] paises = readFile();
		
		String p = null;
		if(cod.length()==4) {
			String sub0 = cod.substring(0,2);
			String sub1 = cod.substring(2);
			
			String [] s0 = paises[Integer.parseInt(sub0)-1].split(",");
			String [] s1 = paises[Integer.parseInt(sub1)-1].split(",");
			
			p = "Origem : " + s0[0] + ", Destino : "+ s1[0];
			
		}
		else if(cod.length()==6) {
			String sub0 = cod.substring(0,2);
			String sub1 = cod.substring(2,4);
			String sub2 = cod.substring(4);
			
			String [] s0 = paises[Integer.parseInt(sub0)-1].split(",");
			String [] s1 = paises[Integer.parseInt(sub1)-1].split(",");
			String [] s2 = paises[Integer.parseInt(sub2)-1].split(",");
			
			p = "Origem : " + s0[0] + ", Destinos : "+ s1[0] +" e " + s2[0];
		}
		else if (cod.length()==7 || cod.length()==8) {
			String sub0 = cod.substring(0,2);
			String sub1 = cod.substring(2,4);
			String sub2 = cod.substring(4,6);
			String sub3 = cod.substring(6);

			String [] s0 = paises[Integer.parseInt(sub0)-1].split(",");
			String [] s1 = paises[Integer.parseInt(sub1)-1].split(",");
			String [] s2 = paises[Integer.parseInt(sub2)-1].split(",");
			String [] s3 = paises[Integer.parseInt(sub3)-1].split(",");
			
			p = "Origem : " + s0[0] + ", Destinos : "+ s1[0] +" , " + s2[0] + " e " + s3[0];
		}
			
		return p;
	}

	public String getPais1() {
		return pais1;
	}
	
	public static String getCodePais(String p) throws FileNotFoundException {
		
		String str[] = readFile();
		String a="";
		
		for(String s : str ) {
			String [] split = s.split(",");
			if(split[0].equals(p)) {
				if(0<Integer.parseInt(split[1]) && Integer.parseInt(split[1])<10) {
					a="0"+split[1];
				}
				else {
					a=split[1];
				}
			}
		}
		
		return a;
	}

	public String getPais2() {
		return pais2;
	}

	public String getPais3() {
		return pais3;
	}

	public String getPais4() {
		return pais4;
	}

	public String getCod() {
		return cod;
	}
	
	public static String[] readFile() throws FileNotFoundException {
		
		String[] string = new String[99];
		
		Scanner scanner = new Scanner(new File("src/projeto/Paises.csv"));
		
		int count=0;
		while(scanner.hasNextLine()) {
			string[count]=scanner.nextLine();
			count++;
		}
		
		scanner.close();
		
		return string;
	}
	
	@Override
	public String toString() {
		String ts="";
		
		if(pais3==null) ts = cod+"\t" + pais1 + "\t" + pais2 +"\n";
		else if(pais4==null) ts = cod+"\t" + pais1 + "\t" + pais2 +"\t" + pais3 + "\n";
		else ts = cod+"\t"+ pais1 + "\t" + pais2 +"\t" + pais3 + "\t"+ pais4 + "\n";
		
		return ts;
	}
	
}

package modules;

public class TestBloomFilter {
	
	static BloomFilter b;
	
	public static void main(String[] args){
		b = new BloomFilter(5);
		b.initialize();
        
		b.addToSet("1234567");
		b.addToSet("8765432");
		b.addToSet("13579");
		b.addToSet("02468");
		b.addToSet("1324657");
		
		System.out.println("1 deve dar que não pertence, 2 pertence, 3 pertence e 4 não pertence");
		
		if(b.contains("1345678")) System.out.println("Pertence!!"); // Nao pertence;
        else System.out.println("Nao Pertence!!");

        if(b.contains("1234567")) System.out.println("Pertence!!"); // Pertence;
        else System.out.println("Nao Pertence!!");
        
        if(b.contains("02468")) System.out.println("Pertence!!"); // Pertence;
        else System.out.println("Nao Pertence!!");
        
        if(b.contains("9387465")) System.out.println("Pertence!!"); // Nao pertence;
        else System.out.println("Nao Pertence!!");
        
	}
}

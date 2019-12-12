package modules;

import java.util.LinkedList;

public class TestMinHash {
	public static void main(String[] args) {
		//Apenas criamos o bloom filter para obter k funcoes de hash;
		BloomFilter b = new BloomFilter(200);
		//
		LinkedList<String> listStrings = new LinkedList<String>();
		listStrings.add("1234567");
		listStrings.add("8765432");
		listStrings.add("1235275");
		listStrings.add("024135");
		listStrings.add("024682");
		
		MinHash mH = new MinHash(listStrings, b.getK());
		
		int[][] minHash = mH.applyMinHash();
		//System.out.println(mH.applyMinHash());
		
//		for(int i=0 ; i<minHash.length ; i++) {
//			for(int j=0 ; j<minHash[i].length ; j++) {
//				System.out.print(minHash[i][j]+" ");
//			}
//			System.out.print("\n");
//		}
		
		System.out.println();
		
		//String a comparar "1234567";
		//Fixa 1234567 e procura similares nos restantes
		String s = "1234567";
		for(int i=0 ; i<listStrings.size() ; i++) {
			System.out.println("Jacard Distance: "+s+" -> "+listStrings.get(i)+" = "+mH.checkSimilaraty(s, listStrings.get(i)));
			System.out.println("---------------------------------------------------");
		}
		
		//String a comparar "024682"
		s = "024682";
		for(int i=0 ; i<listStrings.size() ; i++) {
			System.out.println("Jacard Distance: "+s+" -> "+listStrings.get(i)+" = "+mH.checkSimilaraty(s, listStrings.get(i)));
			System.out.println("---------------------------------------------------");
		}
	}
}

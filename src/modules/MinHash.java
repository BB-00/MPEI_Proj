package modules;

import java.util.LinkedList;

public class MinHash {
	private int[][] minHashValues;
	private LinkedList<String> listStrings = new LinkedList<String>();
	private int k;
	private HashFunction hashFunction;
	
	private int[] a;
	private int[] b;

	public MinHash(LinkedList<String> listStrings, int k) {
		this.listStrings = listStrings;
		this.k = k;
		this.minHashValues = new int[listStrings.size()+50][k];
		this.hashFunction = new HashFunction(k);
		this.a = new int[k];
		this.b = new int[k];
		this.initialize();
	}
	
	
	public int[][] applyMinHash() {
		for(int i=0 ; i<listStrings.size(); i++) {
			String[] sE = getElements(listStrings.get(i));
			for(int j=0 ; j<k ; j++) {
				int min = Integer.MAX_VALUE;
				for(String s : sE) {
					int hash[] = hashFunction.applyToHashFunction(s, a, b);
					if(hash[j]<min) {
						min = hash[j];
					}
				}
				minHashValues[i][j]=min;
			}
		}
		return minHashValues;
	}
	
	// Comparar id1 com id2;
	public double checkSimilaraty(String id1, String id2) {
		
		int count=0;
		int[] sig1 = minHashValues[listStrings.indexOf(id1)];
		int[] sig2 = minHashValues[listStrings.indexOf(id2)];
		
		for(int i=0 ; i<k ; i++) {
			if(sig1[i]==sig2[i]) {
				count++;
			}
		}
		double indexJacard = (double)(count)/(double)(k);
		double distJacard = 1-indexJacard;
		return distJacard;
	}
	
	public String[] getElements(String s) {
		int n=0;
		
		if(s.length()==4) n=1;
		else if(s.length()==6) n=2;
		else if(s.length()==7 || s.length()==8) n=3;
		else throw new IllegalArgumentException("Wrong Route Code!");
		
		String[] elements = new String[n];
		
		if(n==1) elements[0]=s;
		else if(n==2) {
			elements[0]=s.substring(0, 4);
			elements[1]=s.substring(2);
		}else if(n==3) {
			elements[0]="0"+s.substring(0, 3);
			elements[1]=s.substring(1, 5);
			elements[2]=s.substring(3);
		}
		
		return elements;
	}
	
	public void initialize() {
		for(int i=0 ; i<k ; i++) {
			this.a[i] = (int) (Math.random()*listStrings.size())+1;
			this.b[i] = (int) (Math.random()*listStrings.size())+1;
		}
	}
}

package modules;

public class BloomFilter {
	private int[] filter;
	private static int size;
	protected static int m;
	protected int k;
	protected int n;
	protected int[] a;
	protected int[] b;
	private HashFunction hashFunction;
	
	public BloomFilter(int n) {
		this.n = n;
		this.m = getOptimumSize(n, 0.01); // este 0.01 corresponde à probabilidade de falsos positivos pretendida;
		this.k = getOptimumK(m, n);
		this.filter = new int[m];
		this.a = new int[k];
		this.b = new int[k];
		this.hashFunction = new HashFunction(k);
	}
	
	public void initialize() {
		for(int i=0 ; i<k ; i++) {
			a[i] = (int) (Math.random()*31)+1;
			b[i] = (int) (Math.random()*31)+1;
		}
	}
	
	public void addToSet(String s) {
		int [] hash = hashFunction.applyToHashFunction(s, a, b);
		for (int i=0 ; i<k ; i++) {
			filter[hash[i]] = 1;
		}
		size++;
	}
	
	public boolean contains(String s) {
		int [] hash = hashFunction.applyToHashFunction(s, a, b);
		boolean contains = true;
		for (int i=0 ; i<k ; i++) {
			if(filter[hash[i]]==0) {
				contains = false;
			}
		}
		return contains;
	}
	
	public static int getSize() {
		return size;
	}
	
	public int getK() {
		return k;
	}
	public int getN() {
		return n;
	}
	public int getM() {
		return m;
	}
	
	public static int getOptimumSize(int n, double p){
        return (int) Math.round(-n * (Math.log(p)) / Math.pow(Math.log(2), 2));
    }
	
	// Calcular o k Ã³timo para garantir a menor probabilidade de falsos positivos com base dos argumentos fornecidos
    public static int getOptimumK(int m, int n){
        return (int) Math.round(((double)(m/n)) * Math.log(2));
    }

}

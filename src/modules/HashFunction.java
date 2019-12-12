package modules;

public class HashFunction {

    private static int[] hashing;

    public HashFunction(int k) {
        hashing = new int[k]; 
    }

    public static int[] applyToHashFunction(String s, int[] a, int[] b) {

        int x = Integer.parseInt(s);

        for (int i = 0; i < hashing.length; i++) {

            hashing[i] = (a[i]*x + b[i]) % BloomFilter.m;
        }
        return hashing;
    }
}

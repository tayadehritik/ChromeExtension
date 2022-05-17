package com.cryptography.Demojunit;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SDES {
	/**
     * Produces encrypted version of the string
     * @param msg plain text
     * @param key encryption key
     * @return encrypted string
     */
    public static String encrypt(String msg, int key) {
        int[] keys = getKeys(key);
        System.out.println("Plaintext:"+msg);
        StringBuilder builder = new StringBuilder(msg.length());
        for (int i = 0; i < msg.length(); i++)
            builder.append((char)encrypt(msg.charAt(i), keys, 0));
        System.out.println("Cyphertext:"+builder.toString());
        return builder.toString();
    }
    
    /**
     * Writes encrypted contents of in to out
     * @param in source of plain data
     * @param out destination
     * @param key encryption key
     * @throws IOException
     */
    public static void encrypt(InputStream in, OutputStream out, int key) throws IOException {
        int[] keys = getKeys(key);
        while(in.available() != 0)
            out.write(encrypt(in.read(), keys, 0));
    }

    /**
     * Produces decrypted version of the string
     * @param msg cypher text
     * @param key encryption key
     * @return decrypted string
     */
    public static String decrypt(String msg, int key) {
        int[] keys = getKeys(key);
        System.out.println("Cyphertext:"+msg);
        StringBuilder builder = new StringBuilder(msg.length());
        for (int i = 0; i < msg.length(); i++)
            builder.append((char)decrypt(msg.charAt(i), keys));
        System.out.println("Plaintext:"+builder.toString());
        return builder.toString();
    }

    /**
     * Writes decrypted contents of in to out
     * @param in source of encrypted data
     * @param out destination
     * @param key encryption key
     * @throws IOException
     */
    public static void decrypt(InputStream in, OutputStream out, int key) throws IOException {
        int[] keys = getKeys(key);
        while(in.available() != 0)
            out.write(decrypt(in.read(), keys));
    }

    static int encrypt(int c, int[] keys, int mode) {
        int result = f(IP(c), keys[0]);
        result = (result << 28) >>> 24 | (result >>> 4);
        result = f(result, keys[1]);
        return inverseIP(result);
    }

    static int decrypt(int c, int[] keys) {
        int[] newKeys = new int[2];
        newKeys[0] = keys[1];
        newKeys[1] = keys[0];
        return encrypt(c, newKeys, 1);
    }

    static int f(int plainText, int subKey) {
        int L = plainText >>> 4;
        int R = plainText << 28 >>> 28;
        return (L^F(R, subKey)) << 4 | R;
    }

    static int P10(int key){
    	return permutate(key,10, 2,4,1,6,3,9,0,8,7,5);
    }

    static int LS(int key) {
        return permutate(key,10, 1,2,3,4,0,6,7,8,9,5);
    }

    static int P8(int key) {
        return permutate(key,10, 5,2,6,3,7,4,9,8);
    }

    static int[] getKeys(int key) {
        int[] result = new int[2];
        int shifted = LS(P10(key));
        result[0] = P8(shifted);
        shifted = LS(shifted);
        shifted = LS(shifted);
        result[1] = P8(shifted);
        return result;
    }

    static int IP(int plainText) {
        return permutate(plainText,8, 1,5,2,0,3,7,4,6);
    }

    static int inverseIP(int cryptoText) {
    	return permutate(cryptoText,8, 3,0,2,4,6,1,7,5);
    }

    static int permutate(int bits, int req_len, int... pos) {
        int permutatedBits = 0;
        int len=(int) Math.ceil(Math.log(bits) / Math.log(2));
        int	diff=req_len-len;
        for(int i = 0; i < pos.length; i++)
        {
        	if(pos[i]<diff && i==0)
        		permutatedBits=permutatedBits<<1;
        	else
        		permutatedBits |= ((bits >>> len-pos[i]-1+diff) & 1) << (pos.length-i-1);
        }
        return permutatedBits;
    }

    static int F(int plainText, int subKey) {
    	plainText=plainText<<4;
        int permutation = permutate(plainText,8, 3,0,1,2,1,2,3,0);
        permutation ^= subKey;
        
        int substituted = 0;
        int i = ((permutation & (1 << 7)) >>> 6) | (permutation & (1 << 4)) >>> 4;
        int j = ((permutation & (1 << 6)) >>> 5) | (permutation & (1 << 5)) >>> 5;
        substituted |= S0[i][j] << 2;
        i = ((permutation & (1 << 3)) >>> 2) | (permutation & 1);
        j = ((permutation & (1 << 2)) >>> 1) | (permutation & (1 << 1)) >>> 1;
        substituted |= S1[i][j];
        
        return permutate(substituted,4, 1,3,2,0);
    }

    private final static int[][] S0 = new int[][] {
    	{1,0,3,2},
        {3,2,1,0},
        {0,2,1,3},
        {3,1,3,2}
    };

    private final static int[][] S1 = new int[][] {
        {0,1,2,3},
        {2,0,1,3},
        {3,0,1,0},
        {2,1,0,3}
    };
}

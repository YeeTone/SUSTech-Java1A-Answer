package Spring2022A3;

import java.util.Scanner;

public class A3Q1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fCipher = sc.nextLine();
        String cCipher = sc.nextLine();
        int cN = sc.nextInt();
        int M = sc.nextInt();
        System.out.println(getAnswer(fCipher, cCipher, cN, M));
        sc.close();
    }

    public static String getFence(String cipher) {
        int n = cipher.charAt(cipher.length() - 1) - '0';
        cipher = cipher.substring(0, cipher.length() - 1);

        StringBuilder[] sbs = new StringBuilder[n];
        for (int i = 0; i < n; i++) {
            sbs[i] = new StringBuilder();
        }

        for (int index = 0; index < cipher.length(); index++) {
            sbs[index % n].append(Character.toUpperCase(cipher.charAt(index)));
        }

        StringBuilder fence = new StringBuilder();
        for (int i = 0; i < n; i++) {
            fence.append(sbs[i]);
        }

        return fence.toString();
    }

    private static boolean isEnglishLetter(char c){
        return ('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z');
    }

    public static String getCaesar(String cipher, int N) {
        StringBuilder b = new StringBuilder();
        for (char c : cipher.toCharArray()) {
            if (isEnglishLetter(c)) {
                b.append((char) ((Character.toUpperCase(c) - 'A' + N) % 26 + 'A'));
            } else {
                b.append(c);
            }
        }
        return b.toString();
    }

    public static char getVigenere(char c1, char c2) {
        return (char) ((Character.toUpperCase(c1) + Character.toUpperCase(c2) - 2*'A') % 26 + 'A');
    }

    public static String getAnswer(String fenceCipher, String caesarCipher, int caesarN, int M) {
        StringBuilder keys = new StringBuilder(getFence(fenceCipher).substring(0, M));
        String decryptedCipher = getCaesar(caesarCipher, caesarN);
        while (keys.length() < decryptedCipher.length()) {
            keys.append(keys);
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0, j = 0; i < decryptedCipher.length(); i++) {
            if (isEnglishLetter(decryptedCipher.charAt(i))) {
                answer.append(getVigenere(keys.charAt(j), decryptedCipher.charAt(i)));
                j++;
            } else {
                answer.append(decryptedCipher.charAt(i));
            }
        }

        return answer.toString();
    }
}

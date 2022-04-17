package Spring2022A3;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class A3Q2 {
    private static long I;
    private static final ArrayList<String> stm = new ArrayList<String>();
    private static final ArrayList<String> tb = new ArrayList<>();

    private static final Map<String, Long> stmAppearTime = new HashMap<>();
    private static final Map<String, Long> tbAppearTime = new HashMap<>();

    private static void addInformation(String information, List<String> list, Map<String, Long> appearTimeMap) {
        list.add(information);
        if (!appearTimeMap.containsKey(information)) {
            appearTimeMap.put(information, 1L);
        } else {
            appearTimeMap.put(information, appearTimeMap.get(information) + 1);
        }
    }


    public static void main(String[] args) {
        FastReader fastReader = new FastReader(System.in);
        FastWriter fastWriter = new FastWriter(System.out);
        int n = fastReader.nextInt();
        long currentMatched = 0;
        I = fastReader.nextLong();
        long[] ais = new long[n];
        long[] bis = new long[n];

        long[] mis = new long[n];
        for (int i = 0; i < n; i++) {
            mis[i] = fastReader.nextLong();
        }

        boolean I_smaller_than_0 = false;

        for (int i = 0; i < n; i++) {
            String information;
            int type = fastReader.nextInt();
            switch (type) {
                case 1: {
                    information = fastReader.next();
                    addInformation(information, stm, stmAppearTime);
                    break;
                }

                case 2: {
                    information = fastReader.next();
                    alterInfluenceGauge(1);
                    addInformation(information, tb, tbAppearTime);
                    long L = counterStatements();
                    fastWriter.println(L);
                    if (I < L) {
                        alterInfluenceGauge(-1 * stm.size());
                    }

                    if (I < 0) {
                        I_smaller_than_0 = true;
                    }

                    if (stmAppearTime.containsKey(information)) {
                        currentMatched += stmAppearTime.get(information);
                    }
                    break;
                }

                case 3: {
                    fastWriter.println(currentMatched);
                    break;
                }
            }

            long ai = counterStatements();
            long bi = currentMatched;
            ais[i] = ai;
            bis[i] = bi;


        }

        long score;
        if (I < 0 || I_smaller_than_0) {
            score = -1;
        } else {
            score = (long) Long.signum(Arrays.stream(ais).sum()) *
                    Long.signum(Arrays.stream(bis).sum()) *
                    Long.signum(Arrays.stream(mis).sum());
        }

        if (score >= 0) {
            fastWriter.println("Qi Fei");
        } else {
            fastWriter.println("Fail");
        }

        //fastReader.close();
        fastWriter.close();


    }

    private static long counterStatements() {
        if (stm.isEmpty()) {
            return 0;
        }

        stm.sort(Comparator.comparing(String::length));

        if (stm.size() % 2 == 0) {
            return stm.get(stm.size() / 2 - 1).length();
        } else {
            return stm.get(stm.size() / 2).length();
        }

    }

    private static void alterInfluenceGauge(int k) {
        I += k;
    }


    private static class FastReader implements Closeable {
        private final BufferedReader br;
        private StringTokenizer st;

        public FastReader(InputStream in) {
            br = new BufferedReader(new InputStreamReader(in), 16384);
            eat("");
        }

        private void eat(String s) {
            st = new StringTokenizer(s);
        }

        public String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        public boolean hasNext() {
            while (!st.hasMoreTokens()) {
                String s = nextLine();
                if (s == null) return false;
                eat(s);
            }
            return true;
        }

        public String next() {
            hasNext();
            return st.nextToken();
        }

        public boolean nextBoolean() {
            return Boolean.parseBoolean(next());
        }


        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public float nextFloat() {
            return Float.parseFloat(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecimal() {
            return new BigDecimal(next());
        }

        public void close() {
            try {
                st = null;
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }

        }
    }

    private static class FastWriter implements Closeable {
        private final PrintWriter writer;

        public FastWriter(OutputStream out) {
            this.writer = new PrintWriter(out);
        }

        public void print(Object object) {
            writer.write(object.toString());
        }

        public void printf(String format, Object... os) {
            writer.write(String.format(format, os));
        }

        public void println() {
            writer.write(System.lineSeparator());
        }

        public void println(Object object) {
            writer.write(object.toString());
            writer.write(System.lineSeparator());
        }

        @Override
        public void close() {
            writer.close();
        }
    }
}

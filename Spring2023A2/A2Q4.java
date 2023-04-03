package Spring2023A2;

import java.util.*;

public class A2Q4 {

    public static int[] solve(int n) {
        return Solution130.solve(n);
    }
}

class Solution110 {
    public static int[] solve(int n) {
        int[] qr = queryOneByOne(n);
        int[] answer = new int[n];
        boolean[] knowAnswer = new boolean[n];
        Arrays.fill(knowAnswer, false);

        int[] zero_one = find01(n, qr, knowAnswer, answer);
        int zero = zero_one[0], one = zero_one[1];
        findAllBy01(n, zero, one, answer, knowAnswer);
        return answer;
    }

    private static int[] queryOneByOne(int n) {
        int[] qr = new int[n];
        for (int i = 0; i < n; i++) {
            qr[i] = Judge.query(i, (i + 1) % n, (i + 2) % n);
        }

        return qr;
    }

    private static int[] find01(int n, int[] qr, boolean[] knowAnswer, int[] answer) {
        int index0 = -1, index1 = -1;
        for (int i = 0; i < n; i++) {
            int q1 = qr[i], q2 = qr[(i + 1) % n];
            if (q1 != q2) {
                knowAnswer[i] = true;
                knowAnswer[(i + 3) % n] = true;
                answer[i] = q1;
                answer[(i + 3) % n] = q2;

                if (q1 == 0) {
                    index0 = i;
                } else {
                    index1 = i;
                }

                if (q2 == 0) {
                    index0 = (i + 3) % n;
                } else {
                    index1 = (i + 3) % n;
                }
            }
        }

        return new int[]{index0, index1};
    }

    private static void findAllBy01(int n, int zero, int one, int[] answer, boolean[] knowAnswer) {
        for (int i = 0; i < n; i++) {
            if (!knowAnswer[i]) {
                answer[i] = Judge.query(i, zero, one);
            }
        }
    }

}

class Solution120 {
    public static int[] solve(int n) {
        int[] qr = qrn(n);
        int[] answer = new int[n];
        boolean[] knowAnswer = new boolean[n];
        Arrays.fill(knowAnswer, false);
        int[] zero_one = find01(n, qr, knowAnswer, answer);
        int zero = zero_one[0], one = zero_one[1];
        findAllBy01_plus(n, zero, one, answer, knowAnswer, qr);
        return answer;
    }

    private static int[] qrn(int n) {
        int[] qr = new int[n];
        for (int i = 0; i < n; i++) {
            qr[i] = Judge.query(i, (i + 1) % n, (i + 2) % n);
        }

        return qr;
    }

    private static int[] find01(int n, int[] qr, boolean[] knowAnswer, int[] answer) {
        int index0 = -1, index1 = -1;
        for (int i = 0; i < n; i++) {
            int q1 = qr[i], q2 = qr[(i + 1) % n];
            if (q1 != q2) {
                knowAnswer[i] = true;
                knowAnswer[(i + 3) % n] = true;
                answer[i] = q1;
                answer[(i + 3) % n] = q2;

                if (q1 == 0) {
                    index0 = i;
                } else {
                    index1 = i;
                }

                if (q2 == 0) {
                    index0 = (i + 3) % n;
                } else {
                    index1 = (i + 3) % n;
                }
            }
        }

        return new int[]{index0, index1};
    }

    private static void findAllBy01_plus(int n, int zero, int one, int[] answer, boolean[] knowAnswer, int[] qr) {
        for (int i = 0; i < n; i++) {
            if (!knowAnswer[i]) {
                answer[i] = Judge.query(i, zero, one);
                knowAnswer[i] = true;
            }

            for (int j = 0; j < 3; j++) {
                int index0 = (i + j % 3) % n;
                int index1 = (i + (j + 1) % 3) % n;
                int index2 = (i + (j + 2) % 3) % n;

                if (knowAnswer[index0] && qr[i] != answer[index0]) {
                    answer[index1] = qr[i];
                    answer[index2] = qr[i];
                    knowAnswer[index1] = true;
                    knowAnswer[index2] = true;
                }
            }
        }
    }
}

class Solution130 {
    public static int[] solve(int n) {
        int[] answer = new int[n];
        boolean[] knowAnswer = new boolean[n];
        Arrays.fill(knowAnswer, false);
        int[] qr = queryDividedBy3(n);
        int pivot = selectPivot(qr);
        int[] zero_one = q5know6(qr, answer, knowAnswer, pivot);
        int zero = zero_one[0], one = zero_one[1];
        q2know3via01(qr, answer, knowAnswer, n, zero, one, pivot);
        return answer;
    }

    private static int[] q5know6(int[] qr, int[] answer, boolean[] knowAnswer, int pivot) {
        int q_p = qr[pivot], q_p_1 = qr[pivot - 1];
        int zero = -1, one = -1, unknown1, unknown2;
        if (Judge.query(3 * pivot - 2, 3 * pivot - 1, 3 * pivot) != q_p_1) {
            answer[3 * pivot - 3] = q_p_1;
            answer[3 * pivot] = q_p;
            knowAnswer[3 * pivot - 3] = true;
            knowAnswer[3 * pivot - 3] = true;
            answer[3 * pivot - 2] = Judge.query(3 * pivot - 3, 3 * pivot - 2, 3 * pivot);
            knowAnswer[3 * pivot - 2] = true;
            answer[3 * pivot - 1] = 1 - answer[3 * pivot - 2];
            knowAnswer[3 * pivot - 1] = true;

            if (q_p_1 == 0) {
                zero = 3 * pivot - 3;
            } else {
                one = 3 * pivot - 3;
            }

            if (q_p == 0) {
                zero = 3 * pivot;
            } else {
                one = 3 * pivot;
            }

            unknown1 = 3 * pivot + 1;
            unknown2 = 3 * pivot + 2;
        } else if (Judge.query(3 * pivot - 1, 3 * pivot, 3 * pivot + 1) != q_p_1) {
            answer[3 * pivot - 2] = q_p_1;
            answer[3 * pivot + 1] = q_p;
            knowAnswer[3 * pivot - 2] = true;
            knowAnswer[3 * pivot + 1] = true;
            answer[3 * pivot - 1] = Judge.query(3 * pivot - 2, 3 * pivot - 1, 3 * pivot + 1);
            knowAnswer[3 * pivot - 1] = true;
            answer[3 * pivot] = 1 - answer[3 * pivot - 1];
            knowAnswer[3 * pivot] = true;

            if (q_p_1 == 0) {
                zero = 3 * pivot - 2;
            } else {
                one = 3 * pivot - 2;
            }

            if (q_p == 0) {
                zero = 3 * pivot + 1;
            } else {
                one = 3 * pivot + 1;
            }

            unknown1 = 3 * pivot - 3;
            unknown2 = 3 * pivot + 2;
        } else {
            answer[3 * pivot - 1] = q_p_1;
            answer[3 * pivot + 2] = q_p;
            knowAnswer[3 * pivot - 1] = true;
            knowAnswer[3 * pivot + 2] = true;
            answer[3 * pivot] = Judge.query(3 * pivot - 1, 3 * pivot, 3 * pivot + 2);
            knowAnswer[3 * pivot] = true;
            answer[3 * pivot + 1] = 1 - answer[3 * pivot];
            knowAnswer[3 * pivot + 1] = true;

            if (q_p_1 == 0) {
                zero = 3 * pivot - 1;
            } else {
                one = 3 * pivot - 1;
            }

            if (q_p == 0) {
                zero = 3 * pivot + 2;
            } else {
                one = 3 * pivot + 2;
            }

            unknown1 = 3 * pivot - 3;
            unknown2 = 3 * pivot - 2;
        }

        answer[unknown1] = Judge.query(unknown1, zero, one);
        answer[unknown2] = Judge.query(unknown2, zero, one);
        knowAnswer[unknown1] = true;
        knowAnswer[unknown2] = true;

        return new int[]{zero, one};
    }

    private static void q2know3via01(int[] qr, int[] answer, boolean[] knowAnswer, int n, int zero, int one, int pivot) {
        for (int i = 0; i < n / 3; i++) {
            if (i == pivot || i == pivot - 1) {
                continue;
            }

            int coreNumber = qr[i] == 0 ? one : zero;
            int q = Judge.query(3 * i + 1, 3 * i + 2, coreNumber);
            if (q == qr[i]) {
                answer[3 * i + 1] = qr[i];
                answer[3 * i + 2] = qr[i];
                answer[3 * i] = Judge.query(3 * i, zero, one);
            } else {
                answer[3 * i] = qr[i];
                answer[3 * i + 1] = Judge.query(3 * i + 1, zero, one);
                answer[3 * i + 2] = 1 - answer[3 * i + 1];
            }

            knowAnswer[3 * i] = true;
            knowAnswer[3 * i + 1] = true;
            knowAnswer[3 * i + 2] = true;
        }
    }

    private static int[] queryDividedBy3(int n) {
        int[] qr = new int[n / 3];
        for (int i = 0; i < n / 3; i++) {
            qr[i] = Judge.query(i * 3, (i * 3 + 1) % n, (i * 3 + 2) % n);
        }

        return qr;
    }

    private static int selectPivot(int[] qr) {
        int answer = 0;
        for (int i = 1; i < qr.length; i++) {
            if (qr[i] != qr[i - 1]) {
                answer = i;
            }
        }

        return answer;
    }

}

class Judge {
    private static int n;
    private static int[] x;
    private static int count = 0;

    public static int query(int a, int b, int c) {
        if (a == b || b == c || a == c) {
            System.err.printf("[!] Duplicated indices: %d %d %d\n", a, b, c);
            System.exit(1);
        }
        if (a >= n || b >= n || c >= n || a < 0 || b < 0 || c < 0) {
            System.err.printf("[!] Indices out of range: %d %d %d\n", a, b, c);
            System.exit(1);
        }

        count++;

        int sum = x[a] + x[b] + x[c];
        System.out.printf("[+] Query %d %d %d => %d\n", a, b, c, sum / 2);
        return sum / 2;
    }

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        /*System.out.print("[*] n: ");
        n = scanner.nextInt();

        System.out.print("[*] x[]: ");
        x = new int[n];
        for (int i = 0; i < n; i++)
            x[i] = scanner.nextInt();*/
        n = 12;
        x = new int[]{1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0};

        int[] y = A2Q4.solve(n);
        boolean correct = true;
        for (int i = 0; i < n; i++)
            if (x[i] != y[i])
                correct = false;

        System.out.println();
        System.out.println(correct ? "[+] Correct" : "[-] Wrong");
        System.out.printf("[+] Number of guesses: %d\n", count);
    }
}

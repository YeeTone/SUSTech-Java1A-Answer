package Spring2022A4;

import java.util.Arrays;
import java.util.Objects;

public class BigBinary {
    private int[] bits;
    private boolean positive;

    public BigBinary(int[] bits, boolean isPositive) {
        this.bits = Arrays.copyOf(bits, bits.length);
        this.positive = isPositive;
    }

    private void reverseSign() {
        this.positive = !this.positive;
    }


    private void ensureExpectedSize(int expectedSize) {
        if (this.bits.length >= expectedSize) {
            return;
        }

        int[] expanded = new int[expectedSize];

        System.arraycopy(this.bits, 0, expanded, expectedSize - this.bits.length, this.bits.length);

        this.bits = expanded;
    }

    public BigBinary add(BigBinary bigBinary) {
        if (this.positive ^ bigBinary.positive) {
            BigBinary absoluteBigger = getBigger(this, bigBinary);
            BigBinary absoluteSmaller = absoluteBigger == this ? bigBinary : this;

            BigBinary biggerClone = cloneBigBinary(absoluteBigger);
            BigBinary smallerClone = cloneBigBinary(absoluteSmaller);

            biggerClone.positive = true;
            smallerClone.positive = true;

            BigBinary minus = biggerClone.minus(smallerClone);

            this.bits = minus.bits;
            this.positive = absoluteBigger.positive;
            return this;
        }

        int maxLength = Math.max(this.bits.length, bigBinary.bits.length);
        this.ensureExpectedSize(maxLength);
        bigBinary.ensureExpectedSize(maxLength);

        int[] resultArray = new int[maxLength + 1];

        int carry = 0;
        for (int i = maxLength - 1; i >= 0 || carry != 0; i--) {

            int number = carry;
            if (i >= 0) {
                number += this.bits[i] + bigBinary.bits[i];
            }
            carry = number / 2;
            int bit = number % 2;
            resultArray[i + 1] = bit;
        }

        this.bits = resultArray;
        return this;
    }

    public BigBinary minus(BigBinary bigBinary) {

        if (this.positive ^ bigBinary.positive) {
            bigBinary.reverseSign();
            BigBinary result = this.add(bigBinary);
            bigBinary.reverseSign();
            return result;
        }

        BigBinary bigger = getBigger(this, bigBinary);
        int maxLength = bigger.bits.length;

        if (this.equals(bigBinary)) {
            Arrays.fill(this.bits, 0);
            this.positive = true;
        } else {

            BigBinary smaller = bigger == this ? bigBinary : this;

            BigBinary bigClone = cloneBigBinary(bigger);
            BigBinary smallClone = cloneBigBinary(smaller);
            bigClone.positive = true;
            smallClone.positive = true;

            bigClone.ensureExpectedSize(maxLength);
            smallClone.ensureExpectedSize(maxLength);

            int lend = 0;

            int[] bigBits = bigClone.bits, smallBits = smallClone.bits, resultBit = new int[maxLength];

            for (int i = maxLength - 1; i >= 0; i--) {
                int number = -1 * lend + bigBits[i] - smallBits[i];

                if (number < 0) {
                    lend = 1;
                    number += 2;
                } else {
                    lend = 0;
                }

                resultBit[i] = number;
            }


            BigBinary result = new BigBinary(resultBit, true);
            result.cutIntoLength(maxLength);

            this.bits = result.bits;

            if (bigger == this) {
                this.positive = bigger.positive;
            } else {
                this.positive = !bigger.positive;
            }
        }
        return this;
    }
    /*public BigBinary multiply(BigBinary bigBinary) {

        boolean resultPositive = this.positive == bigBinary.positive;
        int maxLength = Math.max(this.bits.length, bigBinary.bits.length);
        this.ensureExpectedSize(maxLength);
        bigBinary.ensureExpectedSize(maxLength);
        int[] resultArray = new int[maxLength * 2];

        for (int i = maxLength - 1; i >= 0; i--) {

            int carry = 0;
            for (int j = maxLength - 1; j >= 0 || carry != 0; j--) {
                int number = carry;
                if (j >= 0) {
                    number += this.bits[i] * bigBinary.bits[j];
                }

                number += resultArray[i + j + 1];

                carry = number / 2;
                int bit = number % 2;

                resultArray[i + j + 1] = bit;

            }
        }

        this.bits = resultArray;
        this.positive = resultPositive;
        return this;
    }*/


    public BigBinary multiply(BigBinary bigBinary) {

        boolean resultPositive = this.positive == bigBinary.positive;
        int maxLength = Math.max(this.bits.length, bigBinary.bits.length);

        int eachSize = 31;
        maxLength = (int) (Math.ceil(maxLength * 1.0 / eachSize) * eachSize);
        this.ensureExpectedSize(maxLength);
        bigBinary.ensureExpectedSize(maxLength);

        long[] thisHex = transformBinTo2EachSize(this.bits, eachSize);
        long[] bbHex = transformBinTo2EachSize(bigBinary.bits, eachSize);

        long[] resultArrayEachSize = new long[thisHex.length * 2];

        for (int i = thisHex.length - 1; i >= 0; i--) {

            long carry = 0;
            for (int j = thisHex.length - 1; j >= 0 || carry != 0; j--) {
                long number = carry;
                if (j >= 0) {
                    number += thisHex[i] * bbHex[j];
                }

                number += resultArrayEachSize[i + j + 1];

                carry = number / (2L << (eachSize - 1));
                long bit = number % (2L << (eachSize - 1));

                resultArrayEachSize[i + j + 1] = bit;

            }
        }

        int[] resultArray2 = hex2bin(resultArrayEachSize, eachSize);

        this.bits = resultArray2;
        this.positive = resultPositive;
        return this;
    }


    private long[] transformBinTo2EachSize(int[] array, int eachSize) {
        int lengthEachSize = array.length / eachSize;
        long[] arrayEachSize = new long[lengthEachSize];

        for (int i = 0; i < array.length; i += eachSize) {

            for (long j = 0, pow = 2L << (eachSize - 2); j < eachSize; j++, pow /= 2) {
                int b = array[(int) (i + j)];
                arrayEachSize[i / eachSize] += (long) b * pow;
            }
        }

        return arrayEachSize;

    }

    private int[] hex2bin(long[] array, int eachSize) {
        int length = array.length * eachSize;
        int[] bin = new int[length];

        for (int i = 0; i < array.length; i++) {
            long hexValue = array[i];
            for (long pow = 2L << (eachSize - 2), j = 0; pow > 0 && j < eachSize; pow /= 2, j++) {
                long h = hexValue / pow;
                hexValue %= pow;
                bin[(int) (eachSize * i + j)] = (int) h;
            }
        }

        return bin;

    }

    public static BigBinary add(BigBinary bigBinary1, BigBinary bigBinary2) {
        BigBinary bigBinary1Clone = cloneBigBinary(bigBinary1);
        BigBinary bigBinary2Clone = cloneBigBinary(bigBinary2);
        return bigBinary1Clone.add(bigBinary2Clone);
    }

    public static BigBinary minus(BigBinary bigBinary1, BigBinary bigBinary2) {

        BigBinary bigBinary1Clone = cloneBigBinary(bigBinary1);
        BigBinary bigBinary2Clone = cloneBigBinary(bigBinary2);
        return bigBinary1Clone.minus(bigBinary2Clone);
    }

    public static BigBinary multiply(BigBinary bigBinary1, BigBinary bigBinary2) {
        BigBinary bigBinary1Clone = cloneBigBinary(bigBinary1);
        BigBinary bigBinary2Clone = cloneBigBinary(bigBinary2);
        return bigBinary1Clone.multiply(bigBinary2Clone);
    }

    private void cutIntoLength(int expectedLength) {
        if (this.bits.length < expectedLength) {
            return;
        }
        int[] cut = new int[expectedLength];
        System.arraycopy(this.bits, this.bits.length - expectedLength, cut, 0, this.bits.length - (this.bits.length - expectedLength));
        this.bits = cut;
    }

    private static BigBinary getBigger(BigBinary b1, BigBinary b2) {
        int maxLength = Math.max(b1.bits.length, b2.bits.length);
        b1.ensureExpectedSize(maxLength);
        b2.ensureExpectedSize(maxLength);

        for (int i = 0; i < maxLength; i++) {
            if (b1.bits[i] == 1 && b2.bits[i] == 0) {
                return b1;
            } else if (b1.bits[i] == 0 && b2.bits[i] == 1) {
                return b2;
            }
        }

        return b1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BigBinary bigBinary = (BigBinary) o;

        int maxLength = Math.max(this.bits.length, bigBinary.bits.length);
        this.ensureExpectedSize(maxLength);
        bigBinary.ensureExpectedSize(maxLength);
        return positive == bigBinary.positive && Arrays.equals(bits, bigBinary.bits);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(positive);
        result = 31 * result + Arrays.hashCode(bits);
        return result;
    }

    @Override
    public String toString() {


        StringBuilder b = new StringBuilder();
        if (!this.positive) {
            b.append("-");
        }

        boolean nonZero = false;

        for (int i : this.bits) {

            if (i != 0) {
                nonZero = true;
            }
            if (nonZero) {
                b.append(i);
            }
        }

        if (!nonZero) {
            return "0";
        } else {
            return b.toString();
        }


    }

    public static BigBinary cloneBigBinary(BigBinary bigBinary) {
        return new BigBinary(bigBinary.bits, bigBinary.positive);
    }

}

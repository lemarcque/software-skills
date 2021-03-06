/**
 * MIT License
 *
 * Copyright (c) 2018 Henoc Sese
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class Main {

    private static int printLine = 1;

    public static void main(String[] main) {

        final int x = 10;
        final int y = 25;

        Pair pair = new PairInt(x, y);
        log(pair);

        // Permute the value of the variable x and y
        Pair permutedPair = pair.permute(x, y);
        log(permutedPair);

        // Permute using XOR methods
        int a = permutedPair.x;
        int b = permutedPair.y;
        Pair xorPermutedPair = Permute.permuteXor(a, b);
        log(xorPermutedPair);

        // Permute using XOR methods
        int c = xorPermutedPair.x;
        int d = xorPermutedPair.y;
        Pair addPermutedPair = Permute.permuteAdd(x, y);
        log(addPermutedPair);
    }

    public static void log(Pair pair) {
        System.out.println(printLine + " : X = " + pair.x + " / Y = " + pair.y);
        printLine++;
    }
}

package com.roachfu.tutorial.similarity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author roach
 * @date 2018/8/23
 */
public class SimilarTutorial {

    private static int compare(String str, String target) {
        int[][] d;              // 矩阵
        int n = str.length();
        int m = target.length();
        int i;                  // 遍历str的
        int j;                  // 遍历target的
        char ch1;               // str的
        char ch2;               // target的
        int temp;               // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        // 初始化第一列
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }

        // 初始化第一行
        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }

        // 遍历str
        for (i = 1; i <= n; i++) {
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }

    private static int min(int one, int two, int three) {
        one = one < two ? one : two;
        return one < three ? one : three;
    }

    /**
     * 获取两字符串的相似度
     */

    private static double getSimilarityRatio(String str, String target) {
        return  1 - (double) compare(str, target) / Math.max(str.length(), target.length());
    }

    public static double calcSimilarity(String str, String target){

        double result = getSimilarityRatio(str, target);
        BigDecimal bg = BigDecimal.valueOf(result * 100).setScale(2, RoundingMode.HALF_UP);

        return bg.doubleValue();
    }

    public static double halfUpDouble(double ratio){
        return BigDecimal.valueOf(ratio).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        String source = "临汾婴达喜复兴巷店";
        String target = "可亲可爱世纪金源店";

        System.out.println("similarityRatio=" + calcSimilarity(source, target));
    }
}

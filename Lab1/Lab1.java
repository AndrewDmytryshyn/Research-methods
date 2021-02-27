package com.company;

public class Lab1 {
    public static void main(String[] args) {
        int x1[] = new int[8];
        int x2[] = new int[8];
        int x3[] = new int[8];
        double var206[] = new double[8];
        double xn1[] = new double[8];
        double xn2[] = new double[8];
        double xn3[] = new double[8];
        int Y[] = new int[8];
        int a[] = {1 + (int) (Math.random() * 20), 1 + (int) (Math.random() * 20), 1 + (int) (Math.random() * 20),
                1 + (int) (Math.random() * 20)};
        for (int i = 0; i < x1.length; i++) {
            x1[i] = 1 + (int) (Math.random() * 20);
            x2[i] = 1 + (int) (Math.random() * 20);
            x3[i] = 1 + (int) (Math.random() * 20);
            Y[i] = a[0] + a[1] * x1[i] + a[2] * x2[i] + a[3] * x3[i];
        }
        double max1 = 0;
        double max2 = 0;
        double max3 = 0;
        double min1 = x1[0];
        double min2 = x2[0];
        double min3 = x3[0];
        for (int i = 0; i < x1.length; i++) {
            if (x1[i] > max1) max1 = x1[i];
            else if (x1[i] < min1) min1 = x1[i];
            if (x2[i] > max2) max2 = x2[i];
            else if (x2[i] < min2) min2 = x2[i];
            if (x3[i] > max3) max3 = x3[i];
            else if (x3[i] < min3) min3 = x3[i];
        }
        double x01 = (max1 + min1) / 2;
        double x02 = (max2 + min2) / 2;
        double x03 = (max3 + min3) / 2;

        double xd1 = x01 - min1;
        double xd2 = x02 - min2;
        double xd3 = x03 - min3;

        for (int i = 0; i < xn1.length; i++) {
            xn1[i] = (x1[i] - x01) / xd1;
            xn2[i] = (x2[i] - x02) / xd2;
            xn3[i] = (x3[i] - x03) / xd3;
        }

        double Yet = a[0] + a[1] * x01 + a[2] * x02 + a[3] * x03;

        double minvar = Math.pow(Y[0] - Yet, 2);

        for (int i = 0; i < var206.length; i++) {
            var206[i] = Math.pow(Y[i] - Yet, 2);
            if (var206[i] < minvar) minvar = var206[i];
        }

        System.out.println("a[0] + x1[i] * a[1] + x2[i] * a[2] + x3[i] * a[3] = Y[i]");

        for (int i = 0; i < x1.length; i++) {
            System.out.println(a[0] + " + " + x1[i] + " * " + a[1] + " + " + x2[i] + " * " + a[2] + " + "
                    + x3[i] + " * " + a[3] + " = " + Y[i] + ";  Xn1 = " + xn1[i] + "; Xn2 = " + xn2[i] + "; Xn3 = " + xn3[i]);

        }
        System.out.println("\nx01 = " + x01 + "; x02 = " + x02 + "; x03 = " + x03);
        System.out.println("\nxd1 = " + xd1 + "; xd2 = " + xd2 + "; xd3 = " + xd3);
        System.out.println("\na[0] + x01 * a[1] + x02 * a[2] + x03 * a[3] = Yet");
        System.out.println(a[0] + " + " + x01 + " * " + a[1] + " + " + x02 + " * " + a[2] + " + " + x03 + " * " + a[3] + " = " + Yet);
        System.out.println("\nmin(Y[i]-Yet)^2 = " + minvar);


    }
}

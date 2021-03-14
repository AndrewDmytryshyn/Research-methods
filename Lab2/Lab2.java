package com.company;

import java.util.Random;

public class Lab2 {
    static double avarege(int[] y) {
        double sum = 0;
        for (int i = 0; i < y.length; i++) {
            sum += y[i];
        }
        return sum / y.length;
    }

    static double[] dispersion(int y[][]) {
        double d[] = new double[y.length];
        for (int i = 0; i < y.length; i++) {
            double sum = 0;
            for (int j = 0; j < y[i].length; j++) {
                sum += Math.pow(y[i][j] - avarege(y[i]), 2);
            }
            d[i] = sum / y[i].length;
        }
        return d;
    }

    static double determinant(double a[][]) {
        return a[0][0] * a[1][1] * a[2][2] + a[0][1] * a[1][2] * a[2][0] + a[1][0] * a[0][2] * a[2][1] - a[0][2] * a[1][1] * a[2][0] - a[0][1] *
                a[1][0] * a[2][2] - a[0][0] * a[1][2] * a[2][1];
    }

    public static void main(String[] args) {
        double ymax = (30 - 206) * 10;
        double ymin = (20 - 206) * 10;
        double x[][] = {{1, 1},
                {-1, 1},
                {1, -1}};
        double x1min = 10;
        double x1max = 40;
        double x2min = -15;
        double x2max = 35;
        double m = 5;
        Random random = new Random();
        int y[][] = new int[3][5];
        for (int i = 0; i < y.length; i++) {
            for (int j = 0; j < y[i].length; j++) {
                y[i][j] = random.nextInt((int) ymax - (int) ymin + 1) + (int) ymin;
            }
        }
        double yavr[] = {avarege(y[0]), avarege(y[1]), avarege(y[2])};

        double sigmateta = Math.sqrt((2 * (2 * m - 2)) / (m * (m - 4)));

        double Fuv[] = {Math.max(dispersion(y)[0], dispersion(y)[1]) / Math.min(dispersion(y)[0], dispersion(y)[1]),
                Math.max(dispersion(y)[2], dispersion(y)[0]) / Math.min(dispersion(y)[2], dispersion(y)[0]),
                Math.max(dispersion(y)[2], dispersion(y)[1]) / Math.min(dispersion(y)[2], dispersion(y)[1])};

        double Teta[] = {(m - 2) / m * Fuv[0], (m - 2) / m * Fuv[1], (m - 2) / m * Fuv[2]};

        double Ruv[] = {Math.abs(Teta[0] - 1) / sigmateta, Math.abs(Teta[1] - 1) / sigmateta,
                Math.abs(Teta[2] - 1) / sigmateta};

        if ((Ruv[0] > 2) || (Ruv[1] > 2) || (Ruv[2] > 2)) {
            System.out.println("Неоднорідна дисперсія");
            System.exit(1337);
        }
        double mx1 = (x[0][0] + x[1][0] + x[2][0]) / 3;
        double mx2 = (x[0][1] + x[1][1] + x[2][1]) / 3;
        double my = (yavr[0] + yavr[1] + yavr[2]) / 3;
        double a1 = (Math.pow(x[0][0], 2) + Math.pow(x[1][0], 2) + Math.pow(x[2][0], 2)) / 3;
        double a2 = (x[0][0] * x[0][1] + x[1][0] * x[1][1] + x[2][0] * x[2][1]) / 3;
        double a3 = (Math.pow(x[0][1], 2) + Math.pow(x[1][1], 2) + Math.pow(x[2][1], 2)) / 3;
        double a11 = (x[0][0] * yavr[0] + x[1][0] * yavr[1] + x[2][0] * yavr[2]) / 3;
        double a22 = (x[0][1] * yavr[0] + x[1][1] * yavr[1] + x[2][1] * yavr[2]) / 3;

        double k1[][] = {{my, mx1, mx2}, {a11, a1, a2}, {a22, a2, a3}};
        double k2[][] = {{1, my, mx2}, {mx1, a11, a2}, {mx2, a22, a3}};
        double k3[][] = {{1, mx1, my}, {mx1, a1, a11}, {mx2, a2, a22}};
        double z[][] = {{1, mx1, mx2}, {mx1, a1, a2}, {mx2, a2, a3}};

        double b0 = determinant(k1) / determinant(z);
        double b1 = determinant(k2) / determinant(z);
        double b2 = determinant(k3) / determinant(z);

        double yp1 = b0 + b1 * x[0][0] + b2 * x[0][1];
        double yp2 = b0 + b1 * x[1][0] + b2 * x[1][1];
        double yp3 = b0 + b1 * x[2][0] + b2 * x[2][1];

        double dx1 = Math.abs(x1max - x1min) / 2;
        double dx2 = Math.abs(x2max - x2min) / 2;

        double x10 = Math.abs(x1max + x1min) / 2;
        double x20 = Math.abs(x2max + x2min) / 2;

        double a0n = b0 - (b1 * x10 / dx1) - (b2 * x20 / dx2);
        double a1n = b1 / dx1;
        double a2n = b2 / dx2;

        double eq1 = a0n + a1n * x1max + a2n * x2max;
        double eq2 = a0n + a1n * x1min + a2n * x2max;
        double eq3 = a0n + a1n * x1max + a2n * x2min;
        System.out.println("Нормована матриця планування:");
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                System.out.print(x[i][j]+", ");
            }
            System.out.println("");
        }
        System.out.print("Середні значення: ");
        for (int i = 0; i < yavr.length; i++) {
            System.out.print(yavr[i] + " ");
        }
        System.out.println("\n" + "З нормованими коефіцієнтами: " + yp1 + " " + yp2 + " " + yp3);
        System.out.println("З натуралізованими: " + eq1 + " " + eq2 + " " + eq3);
    }
}


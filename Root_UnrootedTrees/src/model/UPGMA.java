package model;

import java.util.ArrayList;

/**
 * @author Jose Daniel Fuentes T.
 */
public class UPGMA {

    public UPGMA() {
    }

    /**
     * Neighbor Joining algorithm, the taxa names must have the same order that appears in the distance matrix,
     * Assuming that all distances are positives
     * @param distanceMatrix
     * @param taxaNames
     */

    public static String neighborjoining(double[][] distanceMatrix, ArrayList<String> taxaNames){
        double min = Double.MAX_VALUE;
        int x = 0;
        int y = 0;
        String tree = "";
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i <distanceMatrix.length ; i++) {
            for (int j = 0; j < distanceMatrix.length&&i<j; j++) {
                distanceMatrix[i][j] = Double.MAX_VALUE;
            }
        }
        while (taxaNames.size()>1) {
            min = Double.MAX_VALUE;
            /**
             * Select the minimum value in the matrix
             */
            for (int j = 0; j < distanceMatrix.length; j++) {
                for (int k = 0; k < distanceMatrix.length && k < j; k++) {
                    if (distanceMatrix[j][k] < min) {
                        min = distanceMatrix[j][k];
                        x = j;
                        y = k;
                    }
                }
            }
            System.out.println("Min Value: " + min);
            // System.out.println("Try "+min+" "+x+" "+ y+" "+distanceMatrix.length);
            names.add(taxaNames.get(x));
            names.add(taxaNames.get(y));
            //tree = "(" + taxaNames.get(x) + ":" +distanceMatrix[x][y] / 2 + ", " + taxaNames.get(y) + ":" + distanceMatrix[x][y] / 2 + "):" + distanceMatrix[x][y];
            tree = "(" + taxaNames.get(x) + ", " + taxaNames.get(y) + "):" + distanceMatrix[x][y];

            //System.out.println(tree);

            /**
             * Merge their values
             */
            ArrayList<Double> ab = new ArrayList<Double>();
            double[][] aux = new double[distanceMatrix.length - 1][distanceMatrix.length - 1];
            for (int i = 0; i < distanceMatrix.length; i++) {
                for (int j = 0; j < distanceMatrix.length; j++) {
                    if (j != x && j != y) {
                        ab.add((distanceMatrix[j][x] + distanceMatrix[j][y]) / 2);
                    }
                }
            }

            int amin = Integer.max(x, y);
            //System.out.println(amin);
            int contador = 0;
            for (int j = 0; j < aux.length; j++) {
                for (int k = 0; k < aux.length; k++) {
                    if (k == amin && j != 0) {
                        aux[j][k] = ab.get(contador);
                        //System.out.println(ab.get(contador));
                        contador++;
                        }
                    else {
                        aux[j][k] = distanceMatrix[j+1][k+1];
                    }
                }
            }
                distanceMatrix = aux;
                taxaNames.set(x, tree);
                taxaNames.remove(y);
                printMatrix(distanceMatrix);

        }
            System.out.println("Arbol :" + tree + ";");

        for (int i = 0; i < names.size() ; i++) {
            System.out.print(names.get(i)+" ");
        }
        return(tree);
    }

    public static void main(String[] args) {
        double[][] a = {
                {999,4,2,3},
                {0.11,999,1,5},
                {0.5,0.5,999,6},
                {0.2,1,0.3,999}};
        ArrayList<String> f = new ArrayList<>();
        f.add("a");
        f.add("b");
        f.add("c");
        f.add("d");
        printMatrix(a);
        neighborjoining(a,f);
    }


    public static void printMatrix(double[][] a){
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < a.length; i++) {
            //System.out.print(names.get(i)+"\t");
            for (int j = 0; j < a.length &&j<i; j++) {
                System.out.print(a[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("------------------------------------------------------------------");
    }
}

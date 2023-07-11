package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadUnrootedFile {


    public ArrayList<Gene> ReadFile(String filePath){
        ArrayList<Gene> unrootedTree = new ArrayList<Gene>();
        ArrayList<AsociatedGenes> mappedGenes = new ArrayList<AsociatedGenes>();
        String cadena = "";
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            while(reader.ready()) {
                cadena = reader.readLine();

                cadena = cadena.replace("(", "");
                cadena = cadena.replace(")", "");
                cadena = cadena.replace(";", "");
                System.out.println(cadena);
                String[] aux = cadena.split(",");
                for (int i = 0; i < aux.length; i++) {
                    String[] a = aux[i].split(":");
                    //System.out.println(a[1]);

                    Gene b = new Gene(a[0], a[1]);
                    unrootedTree.add(b);

                    if(!a[2].isEmpty()){

                    }
                }
            }
            double [][] distance = generateDistanceMatrix(unrootedTree);
            ArrayList<String> names = new ArrayList<>();
            for (int i = 0; i < unrootedTree.size(); i++) {
                names.add(unrootedTree.get(i).getName());
            }
            UPGMA neighborJoining = new UPGMA();
            neighborJoining.neighborjoining(distance,names);
            reader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            return unrootedTree;
        }
    }

    public double[][] generateDistanceMatrix(ArrayList<Gene> a){
        double[][] distance = new double[a.size()][a.size()];

        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.size(); j++) {
                if (i==j){
                    distance[i][j]=0;
                }
                else {
                    distance[i][j] = (Double.parseDouble(a.get(j).getValue()) + Double.parseDouble(a.get(i).getValue()))/2;
                }
            }
        }
        return distance;
    }

    /**
     * Reads the newick tree and saves it as a string
     * @param filePath
     * @return
     */

    public String readNewickTree(String filePath) {
        ArrayList<Gene> unrootedTree = new ArrayList<Gene>();
        ArrayList<AsociatedGenes> mappedGenes = new ArrayList<AsociatedGenes>();
        String cadena = "";
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            cadena = reader.readLine();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            return cadena;
        }
    }

    public String buildDistanceMatrix(String newickTree){
        ArrayList<Gene> unrootedTree = new ArrayList<>();
        String cadena = newickTree;
        cadena = cadena.replace("(", "");
        cadena = cadena.replace(")", "");
        cadena = cadena.replace(";", "");

        String[] aux = cadena.split(",");
        for (int i = 0; i < aux.length; i++) {
            String[] a = aux[i].split(":");
            Gene b = new Gene(a[0], a[1]);
            unrootedTree.add(b);
        }
        double [][] distance = generateDistanceMatrix(unrootedTree);
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < unrootedTree.size(); i++) {
            names.add(unrootedTree.get(i).getName());
        }
        System.out.println(distance.length);
        System.out.println(names.size());
        for (int i = 0; i < distance.length ; i++) {
            for (int j = 0; j < distance.length; j++) {
                System.out.print(distance[i][j]+" ");
            }
            System.out.println();
        }
            UPGMA neighborJoining = new UPGMA();
           return neighborJoining.neighborjoining(distance,names);

    }
}

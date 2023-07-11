package controller;

import model.Gene;
import model.NewickTree;
import model.ReadUnrootedFile;
import model.compareTrees;
import view.View;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    /* Instancia del Modelo*/
    private static ReadUnrootedFile readUnrootedFile;

    private static compareTrees compare;

    /* Instancia de la Vista*/
    private View view;
    private NewickTree newickTree;

    public Controller ()
    {
        view = new View();
        readUnrootedFile = new ReadUnrootedFile();
    }

    public void run() {
        Scanner lector = new Scanner(System.in);
        boolean fin = false;
        String dato = "";
        String respuesta = "";
        String aunrootedNewickTree = "";
        String newickTree = "";
        compare = new compareTrees();
        while (!fin) {
            view.printMenu();

            int option = lector.nextInt();
            switch (option) {
                case 1:

                    System.out.println("Write unrooted species Path");
                    String path = lector.next();
                    aunrootedNewickTree = readUnrootedFile.readNewickTree(path);
                    long startTime = System.currentTimeMillis();
                     newickTree = readUnrootedFile.buildDistanceMatrix(aunrootedNewickTree);
                    try{
                        BufferedWriter writer = new BufferedWriter(new FileWriter("./Output/RootedTree.tre"));
                        writer.write(newickTree);
                        writer.close();
                        System.out.println();
                        System.out.println("Tiempo de ejecucion: " + (System.currentTimeMillis() - startTime) + "ms");
                        Runtime rt = Runtime.getRuntime();
                        long total_mem = rt.totalMemory();
                        long free_mem = rt.freeMemory();
                        long used_mem = total_mem - free_mem;
                        System.out.println(used_mem);

                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Write rooted species path");
                    path =  lector.next();;
                    String rootedNewickTree = readUnrootedFile.readNewickTree(path);
                    System.out.println("Percentage of comparison"+compare.identicalTrees(newickTree,rootedNewickTree));
                    break;

                case 3:
                    fin  = true;
                    break;

                default:
                    System.out.println("--------- \n Opcion Invalida !! \n---------");
                    break;
            }

        }
    }


}

package model;

import java.util.ArrayList;

public class compareTrees {
    public compareTrees() {

    }
    public double identicalTrees(String newickTreeA, String newickTreeB)
    {
        double percentage = 100;
        double percentage2 = 100;
        if (newickTreeA.equals(newickTreeB)){
            return 100;
        }
        else{
            newickTreeA = newickTreeA.replace("(", "");
            newickTreeA = newickTreeA.replace(")", "");
            newickTreeA = newickTreeA.replace(";", "");
            String[] aux1 = newickTreeA.split(",");

            newickTreeB = newickTreeB.replace("(", "");
            newickTreeB = newickTreeB.replace(")", "");
            newickTreeB = newickTreeB.replace(";", "");
            String[] aux2 = newickTreeB.split(",");

            double price = 100/aux2.length;

            for (int i = 0; i < aux1.length; i++) {
                if(aux1[i]!=aux2[i]){
                    percentage=percentage-price;
                }

            }

            for (int i = 0; i < aux1.length; i++) {
                if(aux1[i]!=aux2[(aux2.length-1)-i]){
                    percentage2=percentage2-price;
                }

            }
            //System.out.println(percentage2/10 +" "+ percentage/10);
            return 1-Double.max(percentage/10,percentage2/10);
        }

    }
}

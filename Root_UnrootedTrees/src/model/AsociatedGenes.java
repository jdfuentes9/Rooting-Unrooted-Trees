package model;

public class AsociatedGenes {
    int idA;
    int idB;
    double value;

    public AsociatedGenes(int idA, int idB, double value) {
        this.idA = idA;
        this.idB = idB;
        this.value = value;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    public int getIdB() {
        return idB;
    }

    public void setIdB(int idB) {
        this.idB = idB;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

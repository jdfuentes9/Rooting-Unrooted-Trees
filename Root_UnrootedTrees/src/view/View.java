package view;

public class View {
    /**
     * Metodo constructor
     */
    public View()
    {

    }

    public void printMenu()
    {
        System.out.println("1. Enraizar el arbol");
        System.out.println("2. Comparar resultados con algoritmo de STRIDE");
        System.out.println("3. Salir");
        System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
    }

    public void printMessage(String mensaje) {

        System.out.println(mensaje);
    }

}

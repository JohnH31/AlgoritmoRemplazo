package algoritmos;

public class Fifo {

    private int cPaginas;
    private int cMarcos;
    int[] paginas;
    int[][] matriz;
    int[] fallos;
    int auxiliar = 0;

    public Fifo() {

    }

    // metodos set
    public void setPaginas(int[] paginas) {
        this.paginas = paginas;
    }

    public void setcPaginas(int cPaginas) {
        this.cPaginas = cPaginas;
    }

    public void setcMarcos(int cMarcos) {
        this.cMarcos = cMarcos;
    }

    // fallos conteo
    public void iniciarxfallos() {
        for (int i = 0; i < cPaginas; i++) {
            fallos[i] = 0;
        }
    }

    // verifica los marcos para pasar al siguiente
    public void siguiente() {
        auxiliar++;
        if (auxiliar == cMarcos) {// al momento de llegar al final de los marcos regresa al primero
            auxiliar = 0;
        }
    }

    // busqueda para llenar la matriz
    public boolean buscar(int paginaAcutal) {
        boolean bandera = false;

        for (int j = 0; j < cMarcos; j++) {
            // inspecciona todos los marcos de una pagina determinada
            if (paginas[paginaAcutal] == matriz[j][paginaAcutal]) {
                // se le asigna verdadero ala bandera, si el valor de la pagina actual existe en
                // algun marco
                bandera = true; // es sierto
            }
        }
        return bandera;
    }

    // Metodo de modificacion segun el metodo del algoritmo FIFO
    public void modificar(boolean encontrado, int paginaActual) {
        if (!encontrado) {
            for (int aux = paginaActual; aux < cPaginas; aux++) {
                matriz[auxiliar][aux] = paginas[paginaActual];
                fallos[paginaActual] = 1;
            }
            siguiente();
        }
    }

    // Algoritmo FIFO
    public void fifo() {
        matriz = new int[cMarcos][cPaginas];
        fallos = new int[cPaginas];
        iniciarxfallos();
        for (int i = 0; i < cPaginas; i++) {
            modificar(buscar(i), i);
        }
        mostrarMatriz();
    }

    // Esta funcion sirve para mostrar la matriz del algoritmo FIFO
    public void mostrarMatriz() {
        int cantidadFallos = 0;
        for (int i = 0; i < cMarcos; i++) {
            for (int j = 0; j < cPaginas; j++) {
                System.out.print("" + matriz[i][j]);
            }
            System.out.println();
        }
        // Conteo de fallos
        for (int i = 0; i < cPaginas; i++) {
            if (fallos[i] == 1) {
                cantidadFallos++;
            }
            System.out.print("" + fallos[i]);
        }
        int fallot = cantidadFallos - cMarcos;
        System.out.println("\n\nFallos encontrados: " + fallot);
    }
}

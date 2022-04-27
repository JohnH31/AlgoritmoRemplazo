package algoritmos;

public class Optimos {

    public Optimos() {

    }

    // lector de datos a cola
    public void initializarLista(int lista[], int numero) {
        for (int i = 0; i < numero; i++) {
            lista[i] = -1;
        }
    }

    // estado de datos de la cola, (Por si se desea ver el proceso )
    public void MostrarLista(int lista[], int numero) {
        for (int i = 0; i < numero; i++) {
            // System.out.println(" "+lista[i]);
        }
        // System.out.print("");
    }

    // Estado actual de la memoria en lista (ingreso de la cadena )
    public void MostrarMemoriaLista(int lista[], int cadenaNum) {
        for (int i = 0; i < cadenaNum; i++) {
            if (lista[i] == -1) {
                break;
            }
            System.out.print(" " + lista[i]);
        }
        System.out.print("");
    }

    // funcion para los errores
    public void cuentaDeErrores(int conteoErrores, int numerosMarcos) {
        int fallos = conteoErrores - numerosMarcos;
        System.out.println("\nFallos encontrados: " + fallos);
    }

    // funcion verifica donde colocara el numero (Funcion Principal del algoritmo)
    int getNextPosition(int paginaActual, int posicionActual, int strList[], int numero) {

        for (int i = posicionActual + 1; i < numero; i++) {
            if (strList[i] == paginaActual) {
                return i;
            }
        }

        return 100;
    }

    // Algoritmo Optimo
    public void Optimos(int memoriaLista[], int numerosMarcos, int strList[], int numero) {

        int conteoErrores = 0; // conteo fallas

        // Posicion de acceso ala cadena o bloque fisico registrado en la memoria
        int[] nextPosition;
        nextPosition = new int[numerosMarcos];
        // inicialización
        initializarLista(nextPosition, numerosMarcos);

        boolean Visitado; // estado actual en registro de acceso a la pagina, false no visitado

        for (int i = 0; i < numero; i++) {
            Visitado = false;
            // verificamos si necesita ser remplazado,
            // si la memoria esta llena y la pagina a la que se accede no está en la memoria
            for (int j = 0; j < numerosMarcos; j++) {
                if (memoriaLista[j] == strList[i]) {
                    // si esta página ya existe en la memoria
                    // esta registrara la ubicación de la próxima visita
                    nextPosition[j] = getNextPosition(memoriaLista[j], i, strList, numero);

                    Visitado = true;// Modificar acceso

                    // si se desea mostrar este proceso descomentar abajo.
                    // System.out.print(" \n" + strList[i]);
                    // System.out.print(" \n");
                    break;
                }
                if (memoriaLista[j] == -1) {
                    // el numero no está en la memoria y la memoria no está llena almacena
                    // directamente.
                    memoriaLista[j] = strList[i];
                    nextPosition[j] = getNextPosition(memoriaLista[j], i, strList, numero);

                    conteoErrores++;

                    Visitado = true;// modificar el acceso

                    // si se desea ver el proceso de almacenamiento
                    // System.out.print(" \n"+strList[i]);
                    System.out.print(" \n");
                    MostrarMemoriaLista(memoriaLista, numerosMarcos);
                    break;
                }
            }

            if (!Visitado) {

                // hacemos el proceso de remplazo
                // como primero encuentra la pagina a la que se accedio la última
                int max = 0;
                for (int k = 1; k < numerosMarcos; k++) {
                    if (nextPosition[max] < nextPosition[k]) {
                        max = k;
                    }
                }

                // intercambia la pagina en esta posicion
                memoriaLista[max] = strList[i];
                nextPosition[max] = getNextPosition(memoriaLista[max], i, strList, numero);

                conteoErrores++;

                // si se desea mostrar este proceso
                // System.out.print(" \n"+strList[i]);
                System.out.print(" \n");
                MostrarMemoriaLista(memoriaLista, numerosMarcos);
            }
        }
        cuentaDeErrores(conteoErrores, numerosMarcos);
    }
}
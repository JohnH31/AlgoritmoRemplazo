package algoritmos;

public class LRU {
	private int cPaginas;
	private int cMarcos;
	int[] paginas;
	int[][] matriz;
	int[] fallos;
	int[] distancia;

	public LRU() {

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

	// matriz
	private void iniciarMatriz() {
		for (int i = 0; i < cMarcos; i++) {
			for (int j = 0; j < cPaginas; j++) {
				matriz[i][j] = -1;
			}
		}
	}

	// busqueda para llenar la matriz
	private boolean buscar(int paginaActual) {
		boolean encontrado = false;
		for (int i = 0; i < cMarcos; i++) {
			if (paginas[paginaActual] == matriz[i][paginaActual]) {
				encontrado = true;
			}
		}
		return encontrado;
	}

	// llenar filas
	private void llenarFila(int paginaActual, int frame) {
		for (int j = paginaActual; j < cPaginas; j++) {
			matriz[frame][j] = paginas[paginaActual];
		}
	}

	// Metodo del Algoritmo LRU para verificar en los marcos el mas antiguo
	private int MayorDistancia(int paginaActual) {
		int mayorDist = 0;
		for (int i = 0; i < cMarcos; i++) {
			for (int j = paginaActual; j >= 0; j--) {
				if (matriz[i][paginaActual] == paginas[j]) {
					distancia[i] = paginaActual - j;
					break;
				}
			}
		}

		for (int i = 0; i < cMarcos; i++) {
			if (distancia[i] > distancia[mayorDist]) {
				mayorDist = i;
			}
		}
		return mayorDist;
	}

	// Metodo de modificacion segun el metodo del algoritmo LRU
	private void modificar(int paginaActual) {
		boolean encontradoFrameLibre = false;
		int i;
		for (i = 0; i < cMarcos; i++) {
			if (matriz[i][paginaActual] == -1) {
				encontradoFrameLibre = true;
				break;
			}
		}

		if (!encontradoFrameLibre) {
			llenarFila(paginaActual, MayorDistancia(paginaActual));
		} else {
			llenarFila(paginaActual, (i));
		}

		fallos[paginaActual] = 1;

	}

	// Algoritmo LRU
	public void lru() {
		matriz = new int[cMarcos][cPaginas];
		fallos = new int[cPaginas];
		distancia = new int[cMarcos];
		iniciarxfallos();
		iniciarMatriz();
		// Recorremos todas las paginas
		for (int j = 0; j < cPaginas; j++) {
			if (!buscar(j)) {
				modificar(j);
			}
		}
		mostrarMatriz();
	}

	// Esta funcion sirve para mostrar la matriz del algoritmo LRU
	public void mostrarMatriz() {
		int cantidadFallos = 0;
		for (int i = 0; i < cMarcos; i++) {
			for (int j = 0; j < cPaginas; j++) {
				if (matriz[i][j] == -1) {
					System.out.print(" ");// Quitar el -1 de nuestra matriz
				} else
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

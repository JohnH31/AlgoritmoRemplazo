package algoritmos;

import javax.swing.JOptionPane;

public class Algoritmos {
	public static void main(String[] args) {
		boolean salir = false; // salida de menu (while)
		int cPaginas, cMarcos, menu;
		int[] paginas;
		int inicial = 0;
		int contar = -1;
		// ingreso de datos paginas, marcos y cadena en memoria
		cPaginas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el numero de Paginas"));
		cMarcos = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el numero de Marcos"));
		inicial = cMarcos;
		int cantidad = cPaginas + inicial;
		paginas = new int[cantidad];
		System.out.println("\nNumero de Paginas: " + cPaginas);
		System.out.println("Numero de Marcos: " + cMarcos);
		System.out.print("Cadena de paginas en memoria: ");
		for (int c = 0; c < inicial; c++) {
			paginas[c] = c + 1;
		}
		for (int d = inicial; d < cantidad; d++) {
			contar++;
			paginas[d] = Integer
					.parseInt(
							JOptionPane.showInputDialog(null, "Ingresando paginas en memoria (" + (contar + 1) + ")"));
			System.out.print(paginas[d] + " ");
		}
		Fifo fifo = new Fifo();
		LRU lru = new LRU();
		Optimos opts = new Optimos();
		int[] memoriaLista;
		memoriaLista = new int[cMarcos];
		opts.initializarLista(memoriaLista, cMarcos);
		opts.MostrarLista(paginas, cantidad);
		// menu de algoritmos
		while (!salir) {
			System.out.print("\nSeleccionar el numero de algoritmo de remplazo deseado: \n");
			System.out.println(
					" 1.Algoritmo Óptimo\n 2.Algoritmo FIFO\n 3.Algoritmo Último Recientemente Utilizado(LRU)\n 4.Vista de los 3 Algoritmos anteriores\n 5.Salir \n");
			menu = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el numero de Opción"));
			switch (menu) {
				case 1:
					System.out.println("\nOptimo");
					opts.MostrarLista(paginas, cantidad);
					opts.Optimos(memoriaLista, cMarcos, paginas, cantidad);
					opts.initializarLista(memoriaLista, cMarcos);
					break;
				case 2:
					System.out.println("\nFIFO\n");
					fifo.setcMarcos(cMarcos);
					fifo.setcPaginas(cantidad);
					fifo.setPaginas(paginas);
					fifo.fifo();
					break;
				case 3:
					System.out.println("\nLRU\n");
					lru.setcPaginas(cantidad);
					lru.setcMarcos(cMarcos);
					lru.setPaginas(paginas);
					lru.lru();
					break;
				case 4:
					System.out.println("\nOptimo");
					opts.MostrarLista(paginas, cantidad);
					opts.Optimos(memoriaLista, cMarcos, paginas, cantidad);
					opts.initializarLista(memoriaLista, cMarcos);
					System.out.println("\nFIFO\n");
					fifo.setcMarcos(cMarcos);
					fifo.setcPaginas(cantidad);
					fifo.setPaginas(paginas);
					fifo.fifo();
					System.out.println("\nLRU\n");
					lru.setcPaginas(cantidad);
					lru.setcMarcos(cMarcos);
					lru.setPaginas(paginas);
					lru.lru();
					break;
				default:
					salir = true;
					break;
			}
		}

	}
}

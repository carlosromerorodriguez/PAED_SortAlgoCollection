import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Barco> barcos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int end = 0;

        System.out.print("¡Bienvenido a CatTheHobie!\n\n");
        leerArchivo(barcos, scanner);

        do {
            int option = mostrarMenuOrdenacion (scanner);

            switch (option) {
                case 1 -> ordernarXAntiguedad(barcos);
                case 2 -> ordenarAlfabeticamente(barcos);
                case 3 -> ordenarXPrestaciones(barcos);
                case 4 -> {
                    System.out.println("¡Hasta pronto!");
                    end = 1;
                }
                default -> System.out.println("ERROR: Introduzca una opcion dentro del rango");
            }
        } while (end == 0);
    }

    public static void leerArchivo (ArrayList<Barco> barcos, Scanner scanner) {

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String nombre_fitxero_texto = "";
        int option = 0;

        try {
            // Apertura del fichero y creacion de BufferedReader para
            // realizar la lectura de fichero.
            option = leerOpcion (scanner);

            switch (option) {
                case 1 -> nombre_fitxero_texto = "data/fleetL.txt/";
                case 2 -> nombre_fitxero_texto = "data/fleetM.txt/";
                case 3 -> nombre_fitxero_texto = "data/fleetS.txt/";
                case 4 -> nombre_fitxero_texto = "data/fleetXS.txt/";
            }

            //Apertura del fichero
            archivo = new File(nombre_fitxero_texto);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String it = br.readLine();
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] parts = linea.split(";");
                Barco barco = new Barco(Integer.parseInt(parts[0]), parts[1], parts[2],
                                        Double.parseDouble(parts[3]), Double.parseDouble(parts[4]),
                                        Integer.parseInt(parts[5]), Integer.parseInt(parts[6]),
                                        parts[7], Integer.parseInt(parts[8]), parts[9]);
                barcos.add(barco);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close(); //cerrar el fichero
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static int leerOpcion (Scanner scanner) {
        int option = 0;
        do {
            System.out.print("¿Que fichero desea leer? \n");
            System.out.println("1- fleetL");
            System.out.println("2- fleetM");
            System.out.println("3- fleetS");
            System.out.print("4- fleetXS\n\n");
            System.out.print("Opcion? ");
            try {
                option = scanner.nextInt();
                if (option < 1  || option > 4) {
                    System.out.print("Please enter a number between 1 and 4.\n\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer.\n");
            } finally {
                scanner.nextLine();
            }
        } while (option < 1  || option > 4);
        return option;
    }

    // QuickSort para array de Float
    private static int counterQuickSort = 0; // contador par saber el número de iteraciones del algorismo
    public static void quickSortFloat(float[] barcos_id, int izq, int der) {
        counterQuickSort++;
        float pivote = barcos_id[izq]; // tomamos primer elemento como pivote
        int i = izq;                   // i realiza la búsqueda de izquierda a derecha
        int j = der;                   // j realiza la búsqueda de derecha a izquierda
        float aux;

        while (i < j) {                                  // mientras no se crucen las búsquedas
            while (barcos_id[i] <= pivote && i < j) i++; // busca elemento mayor que pivote
            while (barcos_id[j] > pivote) j--;           // busca elemento menor que pivote
            if (i < j) {                                 // si no se han cruzado
                aux = barcos_id[i];                      // los intercambia
                barcos_id[i] = barcos_id[j];
                barcos_id[j] = aux;
            }
        }

        barcos_id[izq] = barcos_id[j]; // se coloca el pivote en su lugar de forma que tendremos los
        barcos_id[j]=pivote;           // números más pequeños a su izquierda y los más grandes su derecha

        if(izq < j-1) {
            quickSortFloat(barcos_id,izq,j-1);           // ordenamos subarray izquierdo
        }
        if(j+1 < der)
            quickSortFloat(barcos_id,j+1,der);           // ordenamos subarray derecho
    }

    // QuickSort para array de Enteros
    public static void quickSortInt(int[] barcos_id, int izq, int der) {
        int pivote = barcos_id[izq]; // tomamos primer elemento como pivote
        int i = izq;                   // i realiza la búsqueda de izquierda a derecha
        int j = der;                   // j realiza la búsqueda de derecha a izquierda
        int aux;

        while (i < j) {                                  // mientras no se crucen las búsquedas
            while (barcos_id[i] <= pivote && i < j) i++; // busca elemento mayor que pivote
            while (barcos_id[j] > pivote) j--;           // busca elemento menor que pivote
            if (i < j) {                                 // si no se han cruzado
                aux = barcos_id[i];                      // los intercambia
                barcos_id[i] = barcos_id[j];
                barcos_id[j] = aux;
            }
        }

        barcos_id[izq] = barcos_id[j]; // se coloca el pivote en su lugar de forma que tendremos los
        barcos_id[j]=pivote;           // números más pequeños a su izquierda y los más grandes su derecha

        if(izq < j-1) {
            quickSortInt(barcos_id,izq,j-1);           // ordenamos subarray izquierdo
        }
        if(j+1 < der)
            quickSortInt(barcos_id,j+1,der);           // ordenamos subarray derecho
    }

    private static int counterMergeSort= 0; // contador par saber el número de iteraciones del algorismo
    public static void mergeSort(String[] a, int i, int j) {
        counterMergeSort++;
        if (i >= j) {
            return;
        }
        int mid = (i + j) / 2;
        mergeSort(a, i, mid);
        mergeSort(a, mid + 1, j);
        merge(a, i, mid, j);
    }

    public static void merge(String[] a, int i, int mid, int j) {
        String[] b = new String[a.length];
        int l = i;
        int r = mid + 1;
        int cursor = i;
        while ((l <= mid) && (r <= j)) {
            if (a[l].compareToIgnoreCase(a[r]) <= 0) {
                b[cursor] = a[l];
                l++;
            } else {
                b[cursor] = a[r];
                r++;
            }
            cursor++;
        }
        while (l <= mid) {
            b[cursor] = a[l];
            l++;
            cursor++;
        }
        while (r <= j) {
            b[cursor] = a[r];
            r++;
            cursor++;
        }
        int k = i;
        while (k <= j) {
            a[k] = b[k];
            k++;
        }
    }

    public static void bucketSortMergeSort(int[] a, int i, int j) {
        if (i >= j) {
            return;
        }
        int mid = (i + j) / 2;
        bucketSortMergeSort(a, i, mid);
        bucketSortMergeSort(a, mid + 1, j);
        bucketSortMerge(a, i, mid, j);
    }

    private static void bucketSortMerge(int[] a, int i, int mig, int j) {
        int[] b = new int[a.length];
        int l = i;
        int r = mig + 1;
        int cursor = i;
        while (l <= mig && r <= j) {
            if (a[l] <= a[r]) {
                b[cursor] = a[l];
                l++;
            } else {
                b[cursor] = a[r];
                r++;
            }
            cursor++;
        }
        while (l <= mig) {
            b[cursor] = a[l];
            l++;
            cursor++;
        }
        while (r <= j) {
            b[cursor] = a[r];
            r++;
            cursor++;
        }
        int k = i;
        while (k <= j) {
            a[k] = b[k];
            k++;
        }
    }

    private static int counterBucketSort= 0; // contador par saber el número de iteraciones del algorismo
    private static int counterQuickSortForEachBucket = 0; //contador par saber cuantas veces se llama al QuickSort i sumárselo al total de iteraciones del BucketSort
    private static void bucketSort (int[] barcos_id, int order, List<Integer>[] buckets) {
        int numBuckets = 10, bucketRange, index = 0, max = 0, min = 0;
        counterBucketSort++;
        if (order == numBuckets) {
            //Buscamos el máximo y mínimo de todos los valores.
            for (int i : barcos_id) {
                max = Math.max(i, max);
                min = Math.min(i, min);
            }
            //Calculamos el rango que tendrá cada cubo.
            bucketRange = (max - min + 1) / numBuckets;

            //Creamos un arraylist para cada cubo donde irán los numeros seleccionados por rango
            for (int j = 0; j < numBuckets + 1; j++) {
                buckets[j] = new ArrayList<>();
            }
            //Añadimos el id del barco a su cubo correspondiente
            for (int j : barcos_id) {
                buckets[(j - min) / bucketRange].add(j);
            }
        }
        if(order == -1){
            //juntamos todos los cubos después de ordenarlos
            for (List<Integer> bucket : buckets) { for (int num : bucket) { barcos_id [index++] = num; } }
        } else {
            //Mirar si el cubo esta vacio o no.
            if(buckets[order].size() == 0) {
                bucketSort(barcos_id, order - 1, buckets);
            } else{
                //Pasamos la arraylist del cubo seleccionado a un array de enteros para poder manejarlo mejor.
                int[] aux = new int[buckets[order].size()];
                for (int k = 0; k < buckets[order].size(); k++){
                    aux[k] = buckets[order].get(k);
                }
                //Ordenamos el array mediante un QuickSort
                counterQuickSortForEachBucket++;
                quickSortInt(aux, 0, aux.length-1);
                //Asignamos el valor del array ordenado a la Arraylist del cubo seleccionado
                for (int p = 0; p < buckets[order].size(); p++){
                    buckets[order].set(p, aux[p]);
                }
                bucketSort(barcos_id, order - 1, buckets);
            }
        }
    }

    public static int mostrarMenuOrdenacion (Scanner scanner) {
        int option = 0;
        do {
            System.out.print("\nElija una de las 4 opciones: \n");
            System.out.println("1- Embarcaciones en funcion de la antiguedad [DESC]");
            System.out.println("2- Embarcaciones en funcion del nombre [A-Z]");
            System.out.println("3- Embarcaciones en funcion de las prestaciones [*]");
            System.out.print("4- Salir\n\n");
            System.out.print("Opcion? ");
            try {
                option = scanner.nextInt();
                if (option < 1  || option > 4) {
                    System.out.print("Please enter a number between 1 and 4.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer.\n");
            } finally {
                scanner.nextLine();
            }
        } while (option < 1  || option > 4);
        return option;
    }

    public static void ordernarXAntiguedad (ArrayList<Barco> barcos) {
        int[] barcos_id = new int[barcos.size()];

        List<Integer>[] buckets = new List[11];

        try {
            //almacenar todos los "id" en un array de enteros
            for (int i = 0; i < barcos.size(); i++) {
                Barco aux1 = barcos.get(i);
                barcos_id[i] = aux1.getId();
            }
            long start = System.nanoTime();
            bucketSort(barcos_id, 10, buckets); //funcion recursiva
            long end = System.nanoTime();

            //mostrar todos los "id" ordenados de mayor a menor
            for (int i = barcos_id.length - 1; i >= 0 ; i--) {
                for (int j = barcos.size() - 1; j >= 0 ; j--) {
                    Barco aux1 = barcos.get(j);
                    //encuentro el barco que coincide con el "id" para mostrar su nombre
                    if (aux1.getId() == barcos_id[i]) {
                        System.out.println("> " + "ID: " + aux1.getId() + " || NOMBRE: " + aux1.getName());
                    }
                }
            }
            System.out.println("\n\n\u001B[34mTiempo BucketSort: "+(end-start)+" ns ("+ BigDecimal.valueOf(end-start).divide(BigDecimal.valueOf(1_000_000))+"ms)\u001B[0m");
            System.out.println("\n\n\u001B[34mNúmero de iteraciones BucketSort: "+ (counterBucketSort+counterQuickSortForEachBucket) +"\u001B[0m");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void ordenarAlfabeticamente (ArrayList<Barco> barcos) {
        String[] list = new String[barcos.size()];

        for (int i = 0; i < barcos.size(); i++) { // llenar el array de strings "list"
            Barco aux1 = barcos.get(i);           // con todos los nombres de los barcos
            list[i] = aux1.getName();
        }
        long start = System.nanoTime();
        mergeSort(list, 0, list.length-1); // función recursiva para ordenarlos
        long end = System.nanoTime();

        for (int i = 0; i < list.length; i++) {   // mostrar los nombres ordenados [A-Z]
            System.out.println(i+1 + " > " + list[i]);
        }
        System.out.println("\n\n\u001B[34mTiempo Mergesort: "+(end-start)+" ns ("+ BigDecimal.valueOf(end-start).divide(BigDecimal.valueOf(1_000_000)) +"ms)\u001B[0m");
        System.out.println("\n\n\u001B[34mNúmero de iteraciones MergeSort: "+ counterMergeSort +"\u001B[0m");
    }

    private static void ordenarXPrestaciones (ArrayList<Barco> barcos) {
        float[] prestaciones = new float[barcos.size()];

        for (int i = 0; i < barcos.size(); i++) { // llenar el array de strings "prestaciones"
            Barco aux1 = barcos.get(i);           // con todos los valores de la division
            prestaciones[i] = (float) (aux1.getPeso()/(aux1.getSlore()+aux1.getCapacity()+aux1.getVelocity()));
        }

        long start = System.nanoTime();
        quickSortFloat(prestaciones, 0, barcos.size() - 1); //función recursiva
        long end = System.nanoTime();

        // mostrar los barcos ordenados por mejores prestaciones
        for (float prestacion : prestaciones) {
            for (Barco aux1 : barcos) {
                //encuentro el barco cuya división de atributos coincide con la de la posición del array "prestaciones"
                if ((float) (aux1.getPeso() / (aux1.getSlore() + aux1.getCapacity() + aux1.getVelocity())) == prestacion) {
                    System.out.println("> NOMBRE: " + aux1.getName() + " || PESO: " + aux1.getPeso() + "kg" + " || ESLORA: " + aux1.getSlore()
                            + "m" + " || CAPACIDAD: " + aux1.getCapacity() + " pers." + " || VEL: " + aux1.getVelocity() + "km/h");
                }
            }
        }

        System.out.println("\n\n\u001B[34mTiempo QuickSort: "+(end-start)+" ns ("+ BigDecimal.valueOf(end-start).divide(BigDecimal.valueOf(1_000_000)) +"ms)\u001B[0m");
        System.out.println("\n\n\u001B[34mNúmero de iteraciones QuickSort: "+ counterQuickSort +"\u001B[0m");
    }
}

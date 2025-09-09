/**
 * Proyecto Escenario 3 - Conceptos Fundamentales de Programación
 * Autores: EDUAR JHAYR ESCURAINA HERNANDEZ, DIEGO BETANCUR MARIN
 * Fecha: Septiembre 2025
 */

package app;

/**
 * Proyecto: Conceptos Fundamentales de Programación
 * Clase: GenerateInfoFiles
 * Descripción: Genera archivos de prueba pseudoaleatorios que servirán como entrada
 * para el sistema de gestión de ventas.
 *
 * @author Eduar
 * @version 1.0
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInfoFiles {

    private static final String[] NOMBRES = {"Carlos", "Ana", "Luis", "María", "Andrés", "Paula", "Diego", "Laura"};
    private static final String[] APELLIDOS = {"Gómez", "Pérez", "Martínez", "Rodríguez", "López", "Fernández"};
    private static final String[] PRODUCTOS = {"Celular", "Computador", "Tablet", "Monitor", "Teclado", "Mouse"};
    private static final Random random = new Random();

    /**
     * Método principal.
     * Ejecuta los generadores de archivos y muestra un mensaje de finalización.
     */
    public static void main(String[] args) {
        try {
            createSalesManInfoFile(5);
            createProductsFile(5);
            createSalesMenFile(10, "Carlos", 123456789L);

            System.out.println("Archivos generados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al generar archivos: " + e.getMessage());
        }
    }

    /**
     * Genera un archivo con información de vendedores.
     * Formato: TipoDocumento;NúmeroDocumento;Nombres;Apellidos
     *
     * @param salesmanCount número de vendedores a generar
     * @throws IOException si ocurre un error al escribir el archivo
     */
    public static void createSalesManInfoFile(int salesmanCount) throws IOException {
        try (FileWriter writer = new FileWriter("vendedores.txt")) {
            for (int i = 0; i < salesmanCount; i++) {
                String tipoDoc = "CC";
                long documento = 100000000L + random.nextInt(900000000);
                String nombre = NOMBRES[random.nextInt(NOMBRES.length)];
                String apellido = APELLIDOS[random.nextInt(APELLIDOS.length)];

                writer.write(tipoDoc + ";" + documento + ";" + nombre + ";" + apellido + "\n");
            }
        }
    }

    /**
     * Genera un archivo con información de productos.
     * Formato: IDProducto;NombreProducto;PrecioPorUnidad
     *
     * @param productsCount número de productos a generar
     * @throws IOException si ocurre un error al escribir el archivo
     */
    public static void createProductsFile(int productsCount) throws IOException {
        try (FileWriter writer = new FileWriter("productos.txt")) {
            for (int i = 1; i <= productsCount; i++) {
                String nombre = PRODUCTOS[random.nextInt(PRODUCTOS.length)];
                double precio = 10000 + random.nextInt(90000); // precios entre 10k y 100k

                writer.write("P" + i + ";" + nombre + ";" + precio + "\n");
            }
        }
    }

    /**
     * Genera un archivo de ventas para un vendedor específico.
     * Formato:
     * TipoDocumentoVendedor;NúmeroDocumentoVendedor
     * IDProducto;CantidadVendida;
     *
     * @param randomSalesCount número de ventas a registrar
     * @param name nombre del vendedor
     * @param id número de documento del vendedor
     * @throws IOException si ocurre un error al escribir el archivo
     */
    public static void createSalesMenFile(int randomSalesCount, String name, long id) throws IOException {
        try (FileWriter writer = new FileWriter("ventas_" + name + ".txt")) {
            writer.write("CC;" + id + "\n");
            for (int i = 1; i <= randomSalesCount; i++) {
                String producto = "P" + (1 + random.nextInt(PRODUCTOS.length));
                int cantidad = 1 + random.nextInt(10);

                writer.write(producto + ";" + cantidad + ";\n");
            }
        }
    }
}

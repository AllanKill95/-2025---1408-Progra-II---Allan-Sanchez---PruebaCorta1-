import java.util.ArrayList;
import java.util.Scanner;

public class GestorTareas {


    static class Tarea {
        private String descripcion;
        private String fechaLimite;
        private String prioridad;
        private String categoria;
        private boolean completada;
//Generamos el Array y las clases en esta parte del codigo//

        public Tarea(String descripcion, String fechaLimite, String prioridad, String categoria) {
            this.descripcion = descripcion;
            this.fechaLimite = fechaLimite;
            this.prioridad = prioridad;
            this.categoria = categoria;
            this.completada = false;
        }

        public String getDescripcion() { return descripcion; }
        public String getFechaLimite() { return fechaLimite; }
        public String getPrioridad() { return prioridad; }
        public String getCategoria() { return categoria; }
        public boolean isCompletada() { return completada; }

        public void editarTarea(String descripcion, String fechaLimite, String prioridad, String categoria) {
            this.descripcion = descripcion;
            this.fechaLimite = fechaLimite;
            this.prioridad = prioridad;
            this.categoria = categoria;
        }

        public void marcarComoCompletada() { this.completada = true; }

        public void mostrarInfo() {
            System.out.println("   Descripción : " + descripcion);
            System.out.println("   Fecha límite: " + fechaLimite);
            System.out.println("   Prioridad   : " + prioridad);
            System.out.println("   Categoría   : " + categoria);
            System.out.println("   Estado      : " + (completada ? "✅ Completada" : "⏳ Pendiente"));
        }
    //Generamos los constructores y los metodos de la clase Tarea//
    }
    private ArrayList<Tarea> listaTareas = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    // ===== Métodos obligatorios =====
    public void agregarTarea(Tarea t) { listaTareas.add(t); }

    public void listarTareas() {
        if (listaTareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }
        System.out.println("\n=== LISTA DE TAREAS ===");
        for (int i = 0; i < listaTareas.size(); i++) {
            System.out.println("[" + (i + 1) + "]");
            listaTareas.get(i).mostrarInfo();
            System.out.println("-------------------------");
        }
    }

    public void listarTareasPorEstado(boolean completadas) {
        String titulo = completadas ? "\n=== TAREAS COMPLETADAS ===" : "\n=== TAREAS PENDIENTES ===";
        System.out.println(titulo);
        int count = 0;
        for (int i = 0; i < listaTareas.size(); i++) {
            if (listaTareas.get(i).isCompletada() == completadas) {
                System.out.println("[" + (i + 1) + "]");
                listaTareas.get(i).mostrarInfo();
                System.out.println("-------------------------");
                count++;
            }
        }
        if (count == 0) System.out.println("No hay tareas en este estado.");
    } //Agregamos los metodos de gestor de tareas y listamos las tareas//
    public void marcarTareaComoCompletada(int indice) {
        if (indice < 1 || indice > listaTareas.size()) {
            System.out.println("Número inválido.");
            return;
        }
        listaTareas.get(indice - 1).marcarComoCompletada();
        System.out.println("✅ Tarea marcada como completada.");
    }

    public void editarTarea(int indice, String desc, String fecha, String prioridad, String categoria) {
        if (indice < 1 || indice > listaTareas.size()) {
            System.out.println("Número inválido.");
            return;
        }
        listaTareas.get(indice - 1).editarTarea(desc, fecha, prioridad, categoria);
        System.out.println("✅ Tarea editada con éxito.");
    }

    public void eliminarTarea(int indice) {
        if (indice < 1 || indice > listaTareas.size()) {
            System.out.println("Número inválido.");
            return;
        }
        listaTareas.remove(indice - 1);
        System.out.println("✅ Tarea eliminada con éxito.");
    }
//Aqui mostramos las tareas filtradas y cambiamos los estados//

    private String leerPrioridad() {
        while (true) {
            System.out.print("Prioridad (Alta, Media, Baja): ");
            String p = sc.nextLine().trim();
            if (p.equalsIgnoreCase("Alta") || p.equalsIgnoreCase("Media") || p.equalsIgnoreCase("Baja")) {
                return p.substring(0,1).toUpperCase() + p.substring(1).toLowerCase();
            }
            System.out.println("Entrada inválida.");
        }
    }

    private String leerCategoria() {
        while (true) {
            System.out.print("Categoría (Trabajo, Estudio, Personal, Otro): ");
            String c = sc.nextLine().trim();
            if (c.equalsIgnoreCase("Trabajo") || c.equalsIgnoreCase("Estudio")
                    || c.equalsIgnoreCase("Personal") || c.equalsIgnoreCase("Otro")) {
                return c.substring(0,1).toUpperCase() + c.substring(1).toLowerCase();
            }
            System.out.println("Entrada inválida.");
        }
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            sc.next();
        }
        int num = sc.nextInt();
        sc.nextLine();
        return num;
    }
//Metodos para leer la prioridad, categoria y enteros.//
public void iniciar() {
    int opcion;
    do {
        System.out.println("\n===== GESTOR DE TAREAS =====");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Listar todas las tareas");
        System.out.println("3. Marcar tarea como completada");
        System.out.println("4. Editar tarea");
        System.out.println("5. Listar solo tareas pendientes");
        System.out.println("6. Listar solo tareas completadas");
        System.out.println("7. Eliminar tarea");
        System.out.println("8. Salir");
        opcion = leerEntero("Seleccione una opción: ");

        switch (opcion) {
            case 1 -> {
                System.out.print("Descripción: ");
                String desc = sc.nextLine();
                System.out.print("Fecha límite: ");
                String fecha = sc.nextLine();
                String prioridad = leerPrioridad();
                String categoria = leerCategoria();
                agregarTarea(new Tarea(desc, fecha, prioridad, categoria));
                System.out.println("✅ Tarea agregada.");
            }
            case 2 -> listarTareas();
            case 3 -> {
                listarTareas();
                marcarTareaComoCompletada(leerEntero("Número de tarea: "));
            }
            case 4 -> {
                listarTareas();
                int num = leerEntero("Número de tarea: ");
                System.out.print("Nueva descripción: ");
                String desc = sc.nextLine();
                System.out.print("Nueva fecha límite: ");
                String fecha = sc.nextLine();
                String prioridad = leerPrioridad();
                String categoria = leerCategoria();
                editarTarea(num, desc, fecha, prioridad, categoria);
            }
            case 5 -> listarTareasPorEstado(false);
            case 6 -> listarTareasPorEstado(true);
            case 7 -> {
                listarTareas();
                eliminarTarea(leerEntero("Número de tarea: "));
            }
            case 8 -> System.out.println("Saliendo...");
            default -> System.out.println("Opción inválida.");
        }
    } while (opcion != 8);
}

    // ===== main =====
    public static void main(String[] args) {
        new GestorTareas().iniciar();
    }
}
//Aqui generamos el menu principal y el metodo main para iniciar la aplicacion//
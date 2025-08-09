import java.util.ArrayList;
import java.util.Scanner;

public class GestionamientoTareas {


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

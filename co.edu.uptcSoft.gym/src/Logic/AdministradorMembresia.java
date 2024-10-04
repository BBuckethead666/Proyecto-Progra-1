package Logic;
import Model.Miembro;
import Model.TipoDeMembresia;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class AdministradorMembresia {
    private List<Miembro> miembros = new ArrayList<>();

    public AdministradorMembresia() {
        // Agregar algunos miembros ficticios
        agregarMiembro(new Miembro(1, "Juan Perez", "juan@gmail.com", TipoDeMembresia.BASICO, LocalDate.of(2022, 1, 15)));
        agregarMiembro(new Miembro(2, "Maria Gomez", "maria@gmail.com", TipoDeMembresia.ESTANDAR, LocalDate.of(2021, 12, 5)));
        agregarMiembro(new Miembro(3, "Pedro Rodriguez", "pedro@gmail.com", TipoDeMembresia.PREMIUM, LocalDate.of(2022, 3, 8)));
        agregarMiembro(new Miembro(4, "Luisa Fernandez", "luisa@gmail.com", TipoDeMembresia.BASICO, LocalDate.of(2022, 2, 20)));
        agregarMiembro(new Miembro(5, "Carlos Sanchez", "carlos@gmail.com", TipoDeMembresia.ESTANDAR, LocalDate.of(2021, 11, 10)));
        agregarMiembro(new Miembro(6, "Ana Ramirez", "ana@gmail.com", TipoDeMembresia.PREMIUM, LocalDate.of(2022, 4, 3)));
        agregarMiembro(new Miembro(7, "Sofia Martinez", "sofia@example.com", TipoDeMembresia.BASICO, LocalDate.of(2022, 5, 12)));
    }

    // Método para agregar un nuevo miembro
    public void agregarMiembro(Miembro miembro) {
        miembros.add(miembro);
    }

    // Método para buscar un miembro por ID
    public Miembro encontrarMiembroPorId(int IdMiembro) {
        for (Miembro miembro : miembros) {
            if (miembro.getIdMiembro() == IdMiembro) {
                return miembro;
            }
        }
        return null; // Si no se encuentra el miembro
    }

    public boolean eliminarMiembroPorId(int IdMiembro) {
        Miembro miembroAEliminar = encontrarMiembroPorId(IdMiembro);
        if (miembroAEliminar != null) {
            miembros.remove(miembroAEliminar);
            return true;
        }
        return false;
    }

    // Método para mostrar todos los miembros
    public void MostrarMiembros() {
        System.out.println("\nLista de miembros:");
        for (Miembro miembro : miembros) {
            miembro.mostrarInfoMiembros();
            System.out.println();
        }
    }

    public void cambiarMembresia(int idMiembro, TipoDeMembresia nuevoTipo) {
        Miembro miembro = encontrarMiembroPorId(idMiembro);
        if (miembro != null) {
            miembro.setTipoMembresia(nuevoTipo);
            System.out.println("Tipo de membresía actualizado para el miembro con ID: " + idMiembro);
        } else {
            System.out.println("Miembro no encontrado.");
        }
    }
}



     
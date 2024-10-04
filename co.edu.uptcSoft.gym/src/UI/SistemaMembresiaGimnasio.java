package UI;

import Logic.AdministradorMembresia;
import Model.Miembro;
import Model.TipoDeMembresia;

import javax.swing.*;
import java.time.LocalDate;

public class SistemaMembresiaGimnasio {
    private static double CuotaMensual;

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Bienvenido al sistema de membresías del gimnasio.");
        System.out.println("hola");

        AdministradorMembresia administradorMembresia = new AdministradorMembresia();

        int opcion = -1;  // Inicializamos opcion con un valor predeterminado.
        do {
            String opcionStr = JOptionPane.showInputDialog(null, "Menú:\n1. Crear nueva membresía\n2. Ver miembros existentes\n3. Buscar miembro por ID\n4. Eliminar miembro por ID\n5. Cambiar tipo de membresía\n6. Salir\nSeleccione una opción:");
            try {
                opcion = Integer.parseInt(opcionStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opción inválida. Intente nuevamente.");
                continue;
            }

            switch (opcion) {
                case 1:
                    crearMembresia(administradorMembresia);
                    break;
                case 2:
                    administradorMembresia.MostrarMiembros();
                    break;
                case 3:
                    encontrarMiembroPorId(administradorMembresia);
                    break;
                case 4:
                    eliminarMiembroPorId(administradorMembresia);
                    break;
                case 5:
                    cambiarMembresia(administradorMembresia);
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "¡Hasta luego!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 6);
    }

    private static void crearMembresia(AdministradorMembresia administradorMembresia) {
        String nombreMiembro;
        while (true) {
            nombreMiembro = JOptionPane.showInputDialog("Ingrese el nombre del miembro:");
            if (nombreMiembro == null) return;
    
            if (nombreMiembro.matches("[a-zA-Z]+")) {
                break; // Salimos del bucle si el nombre es válido
            } else {
                JOptionPane.showMessageDialog(null, "El nombre del miembro solo debe contener letras.");
            }
        }
        String[] opcionesMembresia = {"Básico (75000 por un mes)", "Estándar (130000 por tres meses)", "Premium (450000 por seis meses)"};
        Object seleccion = JOptionPane.showInputDialog(null, "Seleccione el tipo de membresía:", "Tipo de Membresía", JOptionPane.QUESTION_MESSAGE, null, opcionesMembresia, opcionesMembresia[0]);
        
        int opcionMembresia = -1;
        for (int i = 0; i < opcionesMembresia.length; i++) {
            if (seleccion.equals(opcionesMembresia[i])) {
                opcionMembresia = i;
                break;
            }
        }
        TipoDeMembresia tipoMembresia;
        switch (opcionMembresia) {
            case 0:
                tipoMembresia = TipoDeMembresia.BASICO;
                setCuotaMensual(75000);
                break;
            case 1:
                tipoMembresia = TipoDeMembresia.ESTANDAR;
                setCuotaMensual(130000);
                break;
            case 2:
                tipoMembresia = TipoDeMembresia.PREMIUM;
                setCuotaMensual(450000);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida. Se seleccionará Básico por defecto.");
                tipoMembresia = TipoDeMembresia.BASICO;
                setCuotaMensual(75000);
        }

        String email;
        int intentos = 0;
        while (true) {
            email = JOptionPane.showInputDialog("Ingrese su correo: ");
            if (email == null) return;
    
            // Validar email
            String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            if (email.matches(emailRegex)) {
                break; // Salimos del bucle si el correo es válido
            } else {
                JOptionPane.showMessageDialog(null, "Correo electrónico inválido. Debe tener el formato ejemplo@dominio.com");
                intentos++;
                if (intentos >= 10000) {
                    JOptionPane.showMessageDialog(null, "Ha superado el límite de intentos. Regresando al menú.");
                    return;
                }
            }
        }
        int idMiembro;
        while (true) {
            String idStr = JOptionPane.showInputDialog("Ingrese su ID (10 dígitos):");
            if (idStr == null) return;
            
            if (idStr.matches("\\d{10}")) {
                idMiembro = Integer.parseInt(idStr);
                break; // Salimos del bucle si el ID es válido
            } else {
                JOptionPane.showMessageDialog(null, "ID inválido. Debe tener 10 dígitos.");
            }
        }
        LocalDate fechaInicio = LocalDate.now();

        Miembro newMiembro = new Miembro(idMiembro, nombreMiembro, email, tipoMembresia, fechaInicio);
        administradorMembresia.agregarMiembro(newMiembro);

        JOptionPane.showMessageDialog(null, "Membresía creada con éxito.");
    }

  private static void eliminarMiembroPorId(AdministradorMembresia administradorMembresia) {
    int idMiembro;
    boolean miembroEliminado = false;
    
    while (!miembroEliminado) {
        String idStr = JOptionPane.showInputDialog("Ingrese el ID del miembro a eliminar (o cancele para volver al menú):");
        
        if (idStr == null) { // El usuario presionó Cancelar
            return;
        }
        
        try {
            idMiembro = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido. Intente nuevamente.");
            continue;
        }

        boolean eliminado = administradorMembresia.eliminarMiembroPorId(idMiembro);
        if (eliminado) {
            JOptionPane.showMessageDialog(null, "Miembro eliminado con éxito.");
            miembroEliminado = true;
        } else {
            JOptionPane.showMessageDialog(null, "Miembro no encontrado. Intente nuevamente.");
        }
    }
}
    private static void cambiarMembresia(AdministradorMembresia administradorMembresia) {
        int idMiembro;
        try {
            idMiembro = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del miembro:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido. Intente nuevamente.");
            return;
        }

        String[] opcionesMembresia = {"Básico (75000 por un mes)", "Estándar (130000 por tres meses)", "Premium (450000 por seis meses)"};
        Object seleccion = JOptionPane.showInputDialog(null, "Seleccione el tipo de membresía:", "Tipo de Membresía", JOptionPane.QUESTION_MESSAGE, null, opcionesMembresia, opcionesMembresia[0]);
        
        int opcionMembresia = -1;
        for (int i = 0; i < opcionesMembresia.length; i++) {
            if (seleccion.equals(opcionesMembresia[i])) {
                opcionMembresia = i;
                break;
            }
        }
        TipoDeMembresia nuevoTipo;
        switch (opcionMembresia) {
            case 0:
                nuevoTipo = TipoDeMembresia.BASICO;
                break;
            case 1:
                nuevoTipo = TipoDeMembresia.ESTANDAR;
                break;
            case 2:
                nuevoTipo = TipoDeMembresia.PREMIUM;
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida. Se mantendrá el tipo de membresía actual.");
                return;
        }

        administradorMembresia.cambiarMembresia(idMiembro, nuevoTipo);
        JOptionPane.showMessageDialog(null, "Tipo de membresía actualizado para el miembro con ID: " + idMiembro);
    }

    private static void encontrarMiembroPorId(AdministradorMembresia administradorMembresia) {
        int idMiembro;
        try {
            idMiembro = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del miembro a buscar:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido. Intente nuevamente.");
            return;
        }

        Miembro miembro = administradorMembresia.encontrarMiembroPorId(idMiembro);
        if (miembro != null) {
            JOptionPane.showMessageDialog(null, "Información del Miembro:\n" +
                    "ID: " + miembro.getIdMiembro() + "\n" +
                    "Nombre: " + miembro.getNombreMiembro() + "\n" +
                    "Correo: " + miembro.getEmail() + "\n" +
                    "Fecha de Inicio: " + miembro.getFechaInicio() + "\n" +
                    "Tipo de Membresía: " + miembro.getTipoMembresia());
        } else {
            JOptionPane.showMessageDialog(null, "Miembro no encontrado.");
        }
    }

    public static void setCuotaMensual(double cuotaMensual) {
        CuotaMensual = cuotaMensual;
    }

    public static double getCuotaMensual() {
        return CuotaMensual;
    }
    
}
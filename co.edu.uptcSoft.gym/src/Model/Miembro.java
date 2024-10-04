package Model;
import java.time.LocalDate;

public class Miembro {
    private int IdMiembro;
    private String NombreMiembro;
    private String email;
    private LocalDate FechaInicio;
    private TipoDeMembresia TipoMembresia;

    public Miembro(int IdMiembro, String NombreMiembro, String email, TipoDeMembresia TipoMembresia, LocalDate FechaInicio) {
        this.IdMiembro = IdMiembro;
        this.NombreMiembro = NombreMiembro;
        this.email = email;
        this.TipoMembresia = TipoMembresia;
        this.FechaInicio = FechaInicio;
    }

    public int getIdMiembro() {
        return IdMiembro;
    }

    public String getNombreMiembro() {
        return NombreMiembro;
    }

    public String getEmail() {
        return email;
    }

    public TipoDeMembresia getTipoMembresia() {
        return TipoMembresia;
    }

    public LocalDate getFechaInicio() {
        return FechaInicio;
    }

    public void setTipoMembresia(TipoDeMembresia tipoMembresia) {
        this.TipoMembresia = tipoMembresia;
    }

    public void mostrarInfoMiembros() {
        System.out.println("ID del miembro: " + IdMiembro);
        System.out.println("Nombre: " + NombreMiembro);
        System.out.println("Correo electrónico: " + email);
        System.out.println("Fecha de inicio: " + FechaInicio);
        System.out.println("Tipo de membresía: " + TipoMembresia);
    }
}
package profesiones;

import personas.Persona;

public class Doctor extends Persona {
    private final String specialty;
    private final int cobroPorConsulta = 10000;
    private int cantidadConsultas = 0;
    public int montoTotal = 0;

    public String getSpecialty() {
        return this.specialty;
    }
    
    public int getCobroPorConsulta() {
        return this.cobroPorConsulta;
    }
    public Doctor(String pname, String pspecialty) {
        super(pname);
        this.specialty = pspecialty;
    }

    public void realizarTrabajo() {
        System.out.println(getNombre() + " atiende pacientes de " + this.specialty + ".");
    }
    
    
    public int getCantidadConsultas() {
        return this.cantidadConsultas;
    }

        public int cobro(int pcobroPorConsulta, int pcantidadConsultas) {
       montoTotal = pcobroPorConsulta * pcantidadConsultas;
       return montoTotal;
    }
    
    public void setCantidadConsultas(int pcantidadConsultas) {
        this.cantidadConsultas = pcantidadConsultas;
        System.out.println("Cantidad de consultas actualizada: " + pcantidadConsultas);
    }
}
    

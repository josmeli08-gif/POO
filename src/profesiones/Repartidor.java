package profesiones;

import personas.Persona;

public class Repartidor extends Persona {

    private final String tipoVehiculo;
    private final int cobroPorEntrega = 5000;
    private int cantidadEntregas = 0;
    public int montoTotal = 0;

    public String getTipoVehiculo() {
        return this.tipoVehiculo;
    }
    
    public int getCobroPorEntrega() {
        return this.cobroPorEntrega;
    }
    
    public Repartidor(String pname, String ptipoVehiculo) {
        super(pname);
        this.tipoVehiculo = ptipoVehiculo;
    }

    public void realizarTrabajo() {
        System.out.println(getNombre() + " reparte pedidos en " + this.tipoVehiculo + ".");
    }
    
    
    public int getCantidadEntregas() {
        return this.cantidadEntregas;
    }

        public int cobro(int pcobroPorEntrega, int pcantidadEntregas) {
       montoTotal = pcobroPorEntrega * pcantidadEntregas;
       return montoTotal;
    }
    
    public void setCantidadEntregas(int pcantidadEntregas) {
        this.cantidadEntregas = pcantidadEntregas;
        System.out.println("Cantidad de entregas actualizada: " + pcantidadEntregas);
    }

}

package profesiones;

import personas.Persona;

public class Administrador extends Persona {    private final String area;
    private final int salario = 20000;
    private int cantidadTrabajados = 0;
    public int montoTotal = 0;
  
    
    public String getArea() {
        return this.area;
    }
    public int getSalario() {
        return this.salario;
    }
    public Administrador(String pname, String parea) {
        super(pname);
        this.area = parea;
    }

    public void realizarTrabajo() {
        System.out.println(getNombre() + " administra el area de " + this.area + ".");
    }
    
    
    public int getCantidadTrabajados() {
        return this.cantidadTrabajados;
    }

        public int cobro(int psalario, int pcantidadTrabajados) {
       montoTotal = psalario * pcantidadTrabajados;
       return montoTotal;
    }
    
    public void setCantidadTrabajados(int pcantidadTrabajados) {
        this.cantidadTrabajados = pcantidadTrabajados;
        System.out.println("Cantidad de trabajados actualizada: " + pcantidadTrabajados);
    }

}

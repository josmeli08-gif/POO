package programaMutante;

import personas.Persona;
import poderes.IPower;
import poderes.PoderDiagnostico;
import poderes.PoderEntregaRapida;
import poderes.PoderOrganizacion;
import poderes.Podercurarcion;
import poderes.Podervolar;
import profesiones.Administrador;
import profesiones.Doctor;
import profesiones.Repartidor;

public class quickstart {
    public static void main(String[] args) {
        System.out.println("Hello clase de Poo");

        Persona josy = new Persona();
        Persona p1 = new Persona("Joselyn", (byte) 17);

        System.out.println( josy.getNombre());
        josy.cantar();

        System.out.println("--------------------------------------------------");

        String nombreResultado = p1.getNombre();
        System.out.println(nombreResultado);
        p1.cantar();

        System.out.println("--------------------------------------------------");
        
        System.out.println( "Nombre: " + josy.getNombre() + " Edad: " +  josy.getEdad());
        josy.setEdad((byte) 18);
        System.out.println( "Nombre: " + josy.getNombre() + " Edad: " +  josy.getEdad());

        System.out.println("--------------------------------------------------");
        Persona xyz = p1;
        System.out.println( "Nombre: " + xyz.getNombre() + " Edad: " +  xyz.getEdad());
        System.out.println( "Nombre: " + p1.getNombre() + " Edad: " +  p1.getEdad());

        xyz.setEdad((byte) 27);
        System.out.println( "Nombre: " + xyz.getNombre() + " Edad: " +  xyz.getEdad());
        System.out.println( "Nombre: " + p1.getNombre() + " Edad: " +  p1.getEdad());

        // Crear instancias
        Doctor doctor = new Doctor("Dr. Garcia", "Cardiologia");
        Administrador admin = new Administrador("Maria Lopez", "Recursos Humanos");
        Repartidor repartidor = new Repartidor("Juan Perez", "Moto");

        // Mostrar informacion del Doctor
        System.out.println("--- DOCTOR ---");
        System.out.println("Nombre: " + doctor.getNombre());
        System.out.println("Especialidad: " + doctor.getSpecialty());
        doctor.setCantidadConsultas(5);
        System.out.println("Cantidad de consultas: " + doctor.getCantidadConsultas());
        int montoDr = doctor.cobro(10000, 5);
        System.out.println("Monto total (5 consultas): $" + montoDr);
        System.out.println();

        // Mostrar informacion del Administrador
        System.out.println("--- ADMINISTRADOR ---");
        System.out.println("Nombre: " + admin.getNombre());
        System.out.println("Area: " + admin.getArea());
        admin.setCantidadTrabajados(20);
        System.out.println("Cantidad de trabajados: " + admin.getCantidadTrabajados());
        int montoAdmin = admin.cobro(20000, 20);
        System.out.println("Monto total (20 trabajados): $" + montoAdmin);
        System.out.println();

        // Mostrar informacion del Repartidor
        System.out.println("--- REPARTIDOR ---");
        System.out.println("Nombre: " + repartidor.getNombre());
        System.out.println("Tipo de vehiculo: " + repartidor.getTipoVehiculo());
        repartidor.setCantidadEntregas(15);
        System.out.println("Cantidad de entregas: " + repartidor.getCantidadEntregas());
        int montoRep = repartidor.cobro(5000, 15);
        System.out.println("Monto total (15 entregas): $" + montoRep);
        System.out.println();

        // Resumen financiero
        System.out.println("========== RESUMEN FINANCIERO ==========");
        int totalGeneral = montoDr + montoAdmin + montoRep;
        System.out.println("Monto Doctor: $" + montoDr);
        System.out.println("Monto Administrador: $" + montoAdmin);
        System.out.println("Monto Repartidor: $" + montoRep);
        System.out.println("TOTAL: $" + totalGeneral);

      

        Persona[] personas = {
            new Doctor("Dra. Ana", "Neurologia"),
            new Administrador("Carlos Ruiz", "Finanzas"),
            new Repartidor("Sofia Vega", "Bicicleta")
        };

        System.out.println("--- Acciones profesionales ---");
        for (Persona persona : personas) {
            persona.realizarTrabajo();
        }
        Persona profesionales[] = new Persona[10];
        IPower poderesDisponibles[] = {
            new PoderDiagnostico(),
            new PoderOrganizacion(),
            new PoderEntregaRapida(),
            new Podervolar(),
            new Podercurarcion()
        };

        for (int i = 0; i < 10; i++) {
            int tipoprofesion = (int) (Math.random() * 3); // 0: Doctor, 1: Administrador, 2: Repartidor
            switch (tipoprofesion) {
                case 0:
                    profesionales[i] = new Doctor("Doctor " + i, "Especialidad " + i);
                    break;
                case 1:
                    profesionales[i] = new Administrador("Administrador " + i, "Area " + i);
                    break;
                case 2:
                    profesionales[i] = new Repartidor("Repartidor " + i, "Vehiculo " + i);
                    break;
            }
            profesionales[i].setPoder(poderesDisponibles[(int)(Math.random() * poderesDisponibles.length)]);

        }
        for(Persona p : profesionales) {
            System.out.print("Poder de " + p.getNombre() + ": ");
            p.usarPoder();
        }

   
        }
    }



        
 
    


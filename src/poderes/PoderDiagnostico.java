package poderes;

public class PoderDiagnostico implements IPower {
    public PoderDiagnostico() {
    }

    @Override
    public void usarPoder() {
        System.out.println("Diagnostico activado. ");
    }
}
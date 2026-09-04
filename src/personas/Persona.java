
package personas;

import poderes.IPower;

public class Persona{
   private byte edad;  // cuando no se especifica la visibilidad, el default es protected
   protected String nombre;
   private IPower poder;
    //private enum status = {CAMINANDO(0), DURMIENDO, TRABAJNADO, CANTANDO };
    //byte edad;
    //String nombre ;
    public String lugarNacimiento; 

    public Persona() {
      edad = 17;
      nombre = "Joselyn Hidalgo";
   }
   //un segundo constructor que si resive parametros 
    public Persona(byte pEdad, String pNombre) {
      this.edad = pEdad;
      this.nombre = pNombre;
   }

    public Persona(String pNombre, byte pEdad) {
      this.edad = pEdad;
      this.nombre = pNombre;
   }
    
    public Persona(String pNombre){
     this.edad = 0;//agrga un valor por default
     this.nombre = pNombre;//a pesar de que el parameto se llama igual que el atributo
     //this.nombre = pNombre; //this hace referencia al atributo de la clase
   }
   public String getNombre() {
      return this.nombre;
   }
   //metodo get para leer 
   //metodo set para escribir
   public byte getEdad() { //se le va allmar getters and setters 

      return this.edad;
   }

   public void setEdad(byte pEdad) {
      this.edad = pEdad;
   }

   public void realizarTrabajo() {
      System.out.println(this.nombre + " realiza un trabajo general.");
   }

   public void setPoder(IPower ppoder) {
      this.poder = ppoder;
   }

   public void usarPoder() {
      if (this.poder != null) {
         this.poder.usarPoder();
      } else {
         System.out.println(this.nombre + " no tiene poder asignado.");
      }
   }

   public void cantar( ){
        System.out.println("Ya me sinto un niño de papel que vive con miedo de olvidar" + "\n" +
         "Cuando se sonrrojan  esas mejillas con sentimientos de cristal" + "\n"+
         "por la noche, exploto y pienso en terminar con todo y volver a mí" + "\n" +
         "hoy la vida llora porque perdio otra hermosa flor del  de su jardin " + "\n" +
         "Milo J");
   }
}
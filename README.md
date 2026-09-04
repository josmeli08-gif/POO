# Practica de Programacion Orientada a Objetos

Programa Java de consola que representa personas con distintas profesiones y poderes. Demuestra herencia, polimorfismo, interfaces, encapsulamiento y asociaciones entre objetos.

## Funcionamiento

El programa crea personas con las profesiones de doctor, administrador y repartidor. Cada profesion puede realizar su trabajo y calcular el monto obtenido segun sus consultas, trabajos o entregas. Tambien se crean profesionales aleatorios, se les asigna un poder y se usa ese poder mediante la interfaz `IPower`.

La clase de inicio es `programaMutante.quickstart`.

## Estructura

```text
src/
  personas/          Clase base Persona
  profesiones/       Especializaciones profesionales
  poderes/           Interfaz y poderes concretos
  programaMutante/   Punto de entrada del programa
docs/
  diagrama-clases.puml
```

## Clases

| Paquete | Clase | Responsabilidad |
| --- | --- | --- |
| `personas` | `Persona` | Clase base con nombre, edad y un poder opcional. |
| `profesiones` | `Doctor` | Atiende pacientes, guarda especialidad y calcula cobros por consulta. |
| `profesiones` | `Administrador` | Administra un area y calcula el pago por trabajos realizados. |
| `profesiones` | `Repartidor` | Reparte pedidos, registra un vehiculo y calcula el cobro por entrega. |
| `poderes` | `IPower` | Define el contrato `usarPoder()`. |
| `poderes` | `PoderDiagnostico`, `PoderEntregaRapida`, `PoderOrganizacion`, `Podercurarcion`, `Podervolar` | Implementan los poderes disponibles. |
| `programaMutante` | `quickstart` | Contiene `main` y ejecuta los ejemplos del programa. |

## Compilar y ejecutar

Desde la raiz del proyecto, en PowerShell:

```powershell
javac -d bin (Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName })
java -cp bin programaMutante.quickstart
```

## Diagrama de clases (PlantUML)

El archivo fuente del diagrama tambien esta disponible en `docs/diagrama-clases.puml`.

```plantuml
@startuml
skinparam classAttributeIconSize 0

package personas {
    class Persona {
        - edad: byte
        # nombre: String
        + lugarNacimiento: String
        - poder: IPower
        + realizarTrabajo(): void
        + setPoder(poder: IPower): void
        + usarPoder(): void
    }
}

package profesiones {
    class Doctor
    class Administrador
    class Repartidor
}

package poderes {
    interface IPower {
        + usarPoder(): void
    }
    class PoderDiagnostico
    class PoderEntregaRapida
    class PoderOrganizacion
    class Podercurarcion
    class Podervolar
}

package programaMutante {
    class quickstart {
        + main(args: String[]): void
    }
}

Doctor --|> Persona
Administrador --|> Persona
Repartidor --|> Persona
Persona --> IPower : poder
PoderDiagnostico ..|> IPower
PoderEntregaRapida ..|> IPower
PoderOrganizacion ..|> IPower
Podercurarcion ..|> IPower
Podervolar ..|> IPower
quickstart ..> Persona : crea y usa
quickstart ..> Doctor : crea
quickstart ..> Administrador : crea
quickstart ..> Repartidor : crea
quickstart ..> IPower : asigna
@enduml
```
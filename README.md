# Practica de Programacion Orientada a Objetos

Programa Java de consola que representa personas con distintas profesiones y poderes. Demuestra herencia, polimorfismo, interfaces, encapsulamiento y asociaciones entre objetos.

## Funcionamiento

El programa crea personas con las profesiones de doctor, administrador y repartidor. Cada profesion puede realizar su trabajo y calcular el monto obtenido segun sus consultas, trabajos o entregas. Tambien se crean profesionales aleatorios, se les asigna un poder y se usa ese poder mediante la interfaz `IPower`.

La clase de inicio es `programaMutante.quickstart`.

## Ejercicio bancario: identidades y procesadores

La explicación del diseño, los cálculos y el diagrama de clases Mermaid están en [docs/README-bancario.md](docs/README-bancario.md). El archivo fuente del diagrama está en [docs/diagrama-bancario.mmd](docs/diagrama-bancario.mmd).

### Diagrama de clases bancario

```mermaid
classDiagram
    class Usuario {
        +Cadena id
        +Cadena nombre
        +Lista~Identidad~ identidades
    }
    class Identidad {
        <<abstract>>
        +Cadena id
        +Cadena cuenta
        +PoliticaIdentidad politica
        +validarOperacion(Operacion op, Fecha hoy, Cadena procesador) ResultadoValidacion
        #validarReglasEspecificas(Operacion op, Fecha hoy) ResultadoValidacion
    }
    class IdentidadPersonal {
        +Cadena documentoNacional
        #validarReglasEspecificas(Operacion op, Fecha hoy) ResultadoValidacion
    }
    class IdentidadEmpresa {
        +Cadena identificacionFiscal
        +Cadena nombreEmpresa
        #validarReglasEspecificas(Operacion op, Fecha hoy) ResultadoValidacion
    }
    class IdentidadMenor {
        +Cadena documentoNacional
        +Cadena idTutor
        #validarReglasEspecificas(Operacion op, Fecha hoy) ResultadoValidacion
    }
    class IdentidadResidenteExtranjero {
        +Cadena pasaporte
        +Cadena pais
        +Fecha venceResidencia
        #validarReglasEspecificas(Operacion op, Fecha hoy) ResultadoValidacion
    }
    class PoliticaIdentidad {
        +Conjunto~TipoOperacion~ operacionesPermitidas
        +Dinero limiteDiario
        +Dinero umbralAprobacion
        +Conjunto~Cadena~ procesadoresPermitidos
    }
    class Operacion {
        <<abstract>>
        +Cadena id
        +TipoOperacion tipo
        +Dinero importe
        +validarDatos() ResultadoValidacion
        +calcularTotal() Dinero
        +consumoLimite() Dinero
    }
    class Deposito {
        +validarDatos() ResultadoValidacion
        +consumoLimite() Dinero
    }
    class Retiro {
        +validarDatos() ResultadoValidacion
    }
    class TransferenciaNacional {
        +Cadena cuentaDestino
        +validarDatos() ResultadoValidacion
    }
    class TransferenciaInternacional {
        +Cadena cuentaDestino
        +Cadena bic
        +validarDatos() ResultadoValidacion
    }
    class Nomina {
        +Lista~Cadena~ cuentasEmpleados
        +calcularTotal() Dinero
        +validarDatos() ResultadoValidacion
    }
    class TipoOperacion {
        <<enumeration>>
        DEPOSITO
        RETIRO
        TRANSFERENCIA_NACIONAL
        TRANSFERENCIA_INTERNACIONAL
        NOMINA
    }
    class Dinero {
        +Entero64 unidadesMenores
        +Cadena moneda
    }
    class ResultadoValidacion {
        +Booleano valido
        +Lista~Cadena~ errores
    }
    class ResultadoOperacion {
        +Booleano exitosa
        +Cadena referencia
        +Cadena error
        +Dinero comision
    }
    class ResultadoProcesador {
        +Booleano aceptado
        +Cadena referencia
        +Cadena codigoRechazo
    }
    class ConfiguracionProcesador {
        +Cadena id
        +Booleano habilitado
        +Conjunto~TipoOperacion~ operacionesSoportadas
        +Dinero cargoFijo
        +Entero tasaPuntosBase
        +Cadena monedaCargo
        +Booleano tasaSoloSiNoUSD
    }
    class CalculadoraComision {
        +calcular(Dinero total, ConfiguracionProcesador config) Dinero
    }
    class RepositorioConfiguracion {
        +obtenerPolitica(Cadena tipoIdentidad) PoliticaIdentidad
        +obtenerProcesadores() Lista~ConfiguracionProcesador~
    }
    class ProcesadorPago {
        <<interface>>
        +obtenerId() Cadena
        +procesar(Identidad identidad, Operacion operacion) ResultadoProcesador
    }
    class AdaptadorBancoNacional {
        -NationalBankProcessor apiExterna
        +procesar(Identidad identidad, Operacion operacion) ResultadoProcesador
    }
    class AdaptadorBancoPacifico {
        -PacificBankProcessor apiExterna
        +procesar(Identidad identidad, Operacion operacion) ResultadoProcesador
    }
    class AdaptadorPasarelaSwift {
        -SwiftGatewayProcessor apiExterna
        +procesar(Identidad identidad, Operacion operacion) ResultadoProcesador
    }
    class NationalBankProcessor {
        <<externo, no modificable>>
        +postTransaction(cuenta, tipo, importe, contraparte) Cadena
    }
    class PacificBankProcessor {
        <<externo, no modificable>>
        +submit(referencia, codigo, centavos, destino) Cadena
        +submitPayroll(referencia, cuentas, centavosPorEmpleado) Cadena
    }
    class SwiftGatewayProcessor {
        <<externo, no modificable>>
        +sendWire(cuentaOrigen, cuentaDestino, bic, importe, moneda) Cadena
    }
    class SelectorProcesador {
        +seleccionar(Cadena id, Identidad identidad, Operacion operacion) ProcesadorPago
    }
    class RegistroLimitesDiarios {
        +consumido(Cadena identidadId, Fecha fecha, Cadena moneda) Dinero
        +registrarSalida(Identidad identidad, Fecha fecha, Dinero importe) Vacio
    }
    class RegistroAuditoria {
        +registrar(Operacion op, ResultadoOperacion resultado) Vacio
    }
    class ServicioBancario {
        +ejecutar(Usuario usuario, Identidad identidad, Operacion op, Cadena procesadorId) ResultadoOperacion
    }

    Identidad <|-- IdentidadPersonal
    Identidad <|-- IdentidadEmpresa
    Identidad <|-- IdentidadMenor
    Identidad <|-- IdentidadResidenteExtranjero
    Operacion <|-- Deposito
    Operacion <|-- Retiro
    Operacion <|-- TransferenciaNacional
    Operacion <|-- TransferenciaInternacional
    Operacion <|-- Nomina
    ProcesadorPago <|.. AdaptadorBancoNacional
    ProcesadorPago <|.. AdaptadorBancoPacifico
    ProcesadorPago <|.. AdaptadorPasarelaSwift

    Usuario "1" *-- "1..*" Identidad : posee
    IdentidadMenor "0..*" --> "1" Usuario : tutor
    Identidad --> PoliticaIdentidad : aplica
    Operacion --> TipoOperacion
    Operacion *-- Dinero : importe
    ConfiguracionProcesador *-- Dinero : tarifa
    RepositorioConfiguracion ..> PoliticaIdentidad : carga
    RepositorioConfiguracion ..> ConfiguracionProcesador : carga
    AdaptadorBancoNacional --> NationalBankProcessor : envuelve y llama postTransaction
    AdaptadorBancoPacifico --> PacificBankProcessor : envuelve y llama submit o submitPayroll
    AdaptadorPasarelaSwift --> SwiftGatewayProcessor : envuelve y llama sendWire
    SelectorProcesador ..> ProcesadorPago : selecciona por id y compatibilidad
    SelectorProcesador ..> ConfiguracionProcesador : consulta
    ServicioBancario ..> RepositorioConfiguracion
    ServicioBancario ..> CalculadoraComision
    ServicioBancario ..> SelectorProcesador
    ServicioBancario ..> RegistroLimitesDiarios
    ServicioBancario ..> RegistroAuditoria
    ServicioBancario ..> Operacion : coordina
    ServicioBancario ..> ResultadoOperacion : produce
    ServicioBancario ..> Identidad : valida polimorficamente
    ServicioBancario ..> Operacion : calcula polimorficamente
    ServicioBancario ..> ProcesadorPago : invoca polimorficamente
    CalculadoraComision ..> ConfiguracionProcesador : obtiene tarifa
    AdaptadorBancoPacifico ..> Dinero : convierte a centavos
```

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
# Proyecto TP Integrador - Banco

## Pautas de trabajo y convenciones para el proyecto

Este documento establece las convenciones de escritura de código, organización de archivos, estilo de commits y otras prácticas de desarrollo para asegurar que el equipo mantenga consistencia y claridad en el trabajo.

---

### 1. Organización del Workspace y del Proyecto

- **Workspace**: Usaremos Eclipse como entorno de desarrollo, y el *workspace* contendrá una carpeta para el proyecto que servirá como repositorio de Git. La estructura será:
```
    /workspace
    └── TPINT_GRUPO_X_LAB4                 # Carpeta raíz del proyecto
        ├── src
        │   └── integrador               # Paquete principal del proyecto
        │       ├── controller              # Controladores
        │       │   ├── Archivo.java
        │       │   └── Etc...
        │       ├── dao                     # Acceso a datos
        │       │   ├── Archivo.java
        │       │   └── Etc...
        │       ├── model                   # Modelos o entidades del negocio
        │       │   ├── Cliente.java
        │       │   ├── Cuenta.java
        │       │   ├── Localidad.java
        │       │   └── Etc...
        │       ├── enums                   # Enumeraciones o valores constantes
        │       │   └── Etc...
        │       └── utils                   # Utilidades o clases de ayuda
        │           └── DatabaseConnection.java  # Clase de conexión a la base de datos
        ├── WebContent                      # Contenido web y JSPs
        │   ├── index.jsp                   # Página principal
        │   ├── pagina.jsp                   # Página extras
        │   ├── css                         # Archivos de estilos
        │   │   └── style.css
        │   ├── js                          # Archivos JavaScript
        │   │   └── scripts.js
        │   └── images                      # Imágenes del proyecto
        ├── .gitignore                      # Archivos y carpetas a ignorar en Git
        ├── README.md                       # Documentación del proyecto
        ├── TPINT documentation.docx        # Documento con el progreso de las pantallas y la base de datos del trabajo integrador
        ├── DER.png                         # Imagen del DER DDBB
        └── pom.xml                         # Configuración del proyecto Maven (si se usa Maven)
```

### 2. Convenciones de Estilo de Código

#### 2.1 Nombres de Clases, Variables y Métodos

- **Clases**: Nombres en *UpperCamelCase* (e.g., `Cliente`, `Cuenta`, `BancoController`).
- **Interfaces**: Nombres descriptivos, normalmente un sustantivo o adjetivo, también en *UpperCamelCase* (e.g., `IClienteDAO`).
- **Métodos**: Nombres en *lowerCamelCase* y deben describir claramente su función (e.g., `calcularIntereses`, `obtenerSaldo`).
- **Variables**: En *lowerCamelCase* y nombres significativos (e.g., `saldoInicial`, `fechaTransaccion`). Para constantes, usar *UPPER_SNAKE_CASE* (e.g., `MAX_CUENTAS`).
- **Paquetes**: En minúsculas y en singular, separados por puntos (e.g., `com.banco.controller`, `com.banco.model`).

#### 2.2 Comentarios en el Código

- **Comentarios de Clase**: Al inicio de cada clase, incluir un breve comentario que explique su propósito y función.

```java
/**
 * La clase Cliente representa un cliente del banco.
 * Incluye los datos personales y las cuentas asignadas.
 */
public class Cliente { ... }
```

- **Comentarios de Métodos**: Cada método debe tener un comentario breve explicando su función, sus parámetros y el valor de retorno (si aplica).

```java
/**
 * Calcula el saldo de una cuenta específica.
 * 
 * @param cuentaId ID de la cuenta
 * @return El saldo actual de la cuenta
 */
public double calcularSaldo(int cuentaId) { ... }
```

- **Comentarios de Bloque**: Para secciones complejas o lógicas importantes dentro de un método.

```java
// Verifica que el cliente tenga suficiente saldo antes de proceder con la transferencia.
```

#### 3 Estilos de commits

Para mantener un historial claro y estructurado, seguimos las convenciones de tipo de commit y formato:

- **Formato de Commit**:

```java
[Tipo]: [Título conciso del commit]

[Descripción detallada si se requieren más detalles]
```
- **Tipos de Commit**:

Feat: Agregar una nueva característica o funcionalidad.

Fix: Corrección de errores o fallos.

Chore: Cambios menores o de mantenimiento que no afectan el comportamiento funcional.

- **Ejemplo de Commit**:

Feat: Implementación de ABML para clientes

Se añade la funcionalidad para agregar, buscar, modificar y eliminar clientes en la base de datos, utilizando un patrón DAO y controladores separados.


#### 4 Organización del Código

- **Paquetes**: Organizar el código en paquetes por capas, como controller, model, view y dao, para mantener una arquitectura limpia y respetar el diseño en capas del proyecto.

- **Agrupación de Funciones**: Cada clase debe tener métodos agrupados por funcionalidad. Para clases largas, agrupar métodos de lógica común.

- **Evitar Contaminación en los Commits**: Asegúrarse de que los commits solo incluyan los archivos relevantes para el proyecto y eviten cualquier tipo de metadato o archivo temporal que pueda ser innecesario. Esto incluye archivos específicos del entorno, como configuraciones temporales de Eclipse o registros.

- **Revisión de cambios antes de cada commit:**: Antes de realizar un commit, revisa los cambios (git status o git diff) para verificar que solo se incluyan archivos relevantes.

#### 5 Buenas Prácticas Generales

- **Documentación**: Cada cambio importante o funcionalidad nueva debe documentarse en el README.md o en documentos de diseño en la carpeta /docs.

- **Refactorización**: Cuando se cambien nombres o estructura de clases, asegúrate de que las pruebas y la funcionalidad existente se mantengan.

- **Revisión de Código**: Antes de hacer un merge en la rama principal, al menos otro miembro del equipo debería revisar los cambios.

#### 6 Pruebas y Validación

- **Pruebas de Unidad**: Cada funcionalidad nueva o importante debe incluir pruebas unitarias, asegurando que el código sea modular y fácilmente testeable.

- **Pruebas Manuales**: Validar en cada entrega que la aplicación funcione en su totalidad, cumpliendo los criterios definidos para el TP Integrador.

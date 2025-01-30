# Diagrama de Clases: Sergio Pichardo Melgar

## 1. Clases y Relaciones

### Curso
- **Propiedades**:
  - `nombre`: Nombre del curso.
  - `codigo`: Código del curso.
- **Métodos**:
  - `agregarAlumno(estudiante)`: Añade un estudiante al curso.
  - `mostrarEstudiantes()`: Muestra la lista de estudiantes.

### Estudiante
- **Propiedades**:
  - `nombre`: Nombre del estudiante.
  - `dni`: Documento de identidad del estudiante.
- **Métodos**:
  - `inscribirse(curso)`: Inscribe al estudiante en un curso.

- **Relación**: Un `Curso` puede tener muchos `Estudiante`. Cada `Estudiante` pertenece a un solo `Curso`.

## 2. Métodos Clave

### `agregarAlumno(estudiante)`
- **Qué hace**: Añade un `Estudiante` a la lista de estudiantes del `Curso`.
- **Uso**: Cuando un estudiante se inscribe en un curso, se usa este método para registrarlo.

### `inscribirse(curso)`
- **Qué hace**: Inscribe al `Estudiante` en un `Curso` llamando al método `agregarAlumno` del curso.
- **Uso**: Permite al estudiante registrarse en un curso específico.

## 3. Código en Kotlin

```kotlin
class Curso(val nombre: String, val codigo: String) {
    private val estudiantes = mutableListOf<Estudiante>()

    fun agregarAlumno(estudiante: Estudiante) {
        estudiantes.add(estudiante)
        println("${estudiante.nombre} se ha inscrito en $nombre.")
    }

    fun mostrarEstudiantes() {
        println("Estudiantes en $nombre:")
        estudiantes.forEach { estudiante ->
            println("- ${estudiante.nombre} (DNI: ${estudiante.dni})")
        }
    }
}

class Estudiante(val nombre: String, val dni: String) {
    fun inscribirse(curso: Curso) {
        curso.agregarAlumno(this)
    }
}

fun main() {
    val curso = Curso("Programación", "PROG101")
    val estudiante1 = Estudiante("Juan Pérez", "12345678")
    val estudiante2 = Estudiante("María Gómez", "87654321")

    estudiante1.inscribirse(curso)
    estudiante2.inscribirse(curso)

    curso.mostrarEstudiantes()
}
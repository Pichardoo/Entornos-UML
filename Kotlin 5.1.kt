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
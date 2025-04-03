package br.com.casa_guido.screens

data class Agendamento(
    val nome: String,
    val horario: String,
    val tipo: String,
    val status: Boolean,
)

data class Paciente(
    val nome: String,
    val telefone: String,
    val status: Boolean,
)


val listaAgendamentos = listOf(
    Agendamento(
        nome = "Ana Silva",
        horario = "08:00",
        tipo = "Consulta inicial",
        status = true
    ),
    Agendamento(nome = "Carlos Oliveira", horario = "09:00", tipo = "Retorno", status = false),
    Agendamento(nome = "Mariana Santos", horario = "10:00", tipo = "Exame", status = true),
    Agendamento(
        nome = "Pedro Costa",
        horario = "10:00",
        tipo = "Procedimento",
        status = true
    ),
    Agendamento(nome = "Juliana Lima", horario = "10:00", tipo = "Exame", status = false),
    Agendamento(
        nome = "Mariana Santos",
        horario = "10:00",
        tipo = "Consulta inicial",
        status = true
    ),
    Agendamento(nome = "Pedro Costa", horario = "10:00", tipo = "Exame", status = true),
    Agendamento(
        nome = "Juliana Lima",
        horario = "10:00",
        tipo = "Consulta inicial",
        status = false
    ),
    Agendamento(
        nome = "Juliana Lima",
        horario = "10:00",
        tipo = "Consulta inicial",
        status = false
    ),
    Agendamento(
        nome = "Juliana Lima",
        horario = "10:00",
        tipo = "Consulta inicial",
        status = false
    )


)

val listaPacientes = listOf(
    Paciente(
        nome = "Ana Silva",
        telefone = "(48) 99999-9999",
        status = false
    ),
    Paciente(
        nome = "Pedro Costa",
        telefone = "(48) 99999-9999",
        status = true
    ),
    Paciente(
        nome = "Mariana Santos",
        telefone = "(48) 99999-9999",
        status = false
    ),
    Paciente(
        nome = "Juliana Lima",
        telefone = "(48) 99999-9999",
        status = true
    ),
    Paciente(
        nome = "Pedro Costa",
        telefone = "(48) 99999-9999",
        status = false
    ),
    Paciente(
        nome = "Ana Silva",
        telefone = "(48) 99999-9999",
        status = true
    ),
    Paciente(
        nome = "Juliana Lima",
        telefone = "(48) 99999-9999",
        status = true
    ),
    Paciente(
        nome = "Ana Silva",
        telefone = "(48) 99999-9999",
        status = true
    ),
    Paciente(
        nome = "Ana Silva",
        telefone = "(48) 99999-9999",
        status = false
    ),
    Paciente(
        nome = "Pedro Costa",
        telefone = "(48) 99999-9999",
        status = true
    ),
    Paciente(
        nome = "Ana Silva",
        telefone = "(48) 99999-9999",
        status = false
    ),
    Paciente(
        nome = "Ana Silva",
        telefone = "(48) 99999-9999",
        status = true
    ),
    Paciente(
        nome = "Pedro Costa",
        telefone = "(48) 99999-9999",
        status = false
    ),
    Paciente(
        nome = "Ana Silva",
        telefone = "(48) 99999-9999",
        status = true
    ),
    Paciente(
        nome = "Ana Silva",
        telefone = "(48) 99999-9999",
        status = false
    ),
    Paciente(
        nome = "Mariana Santos",
        telefone = "(48) 99999-9999",
        status = true
    ),
    Paciente(
        nome = "Ana Silva",
        telefone = "(48) 99999-9999",
        status = true
    ),
    Paciente(
        nome = "Juliana Lima",
        telefone = "(48) 99999-9999",
        status = false
    ),
    Paciente(
        nome = "Ana Silva",
        telefone = "(48) 99999-9999",
        status = true
    )


)

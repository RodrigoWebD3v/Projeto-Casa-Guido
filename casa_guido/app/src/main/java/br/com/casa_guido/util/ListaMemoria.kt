package br.com.casa_guido.util

import br.com.casa_guido.screens.Paciente
import br.com.casa_guido.screens.Pessoa
import java.util.UUID

//
//val nome: String = "",
//val telefone: String = "",
//val status: Boolean = false,
//val dataNascimento: String = "",
//val nomeMae: String = "",
//val nomePai: String = "",
//val nomeResponsavel: String = "",
//val cpf: String = "",
//val rg: String = "",
//val cartaoSus: String = "",

val id : String = UUID.randomUUID().toString()
object ListaMemoria {
    val pacientesLista: MutableList<Paciente> = mutableListOf(
        Paciente(
            pessoa = Pessoa(
                nome = "Pedro Silva",
                telefone = "(48) 99999-9999",
                dataNascimento = "01/01/2000",
                cpf = "123.456.789-00",
                rg = "12.345.678-9",
            ),
            status = true,
            nomeMae = "Maria Silva",
            nomePai = "João Silva",
            nomeOutro = "Ana Silva",
            cartaoSus = "123456789012345"
        ),
        Paciente(
            pessoa = Pessoa(
                nome = "Camila Rocha",
                telefone = "(11) 98888-1111",
                dataNascimento = "15/03/1995",
                cpf = "234.567.890-11",
                rg = "23.456.789-0",
            ),
            status = true,
            nomeMae = "Luciana Rocha",
            nomePai = "Carlos Rocha",
            nomeOutro = "Luciana Rocha",
            cartaoSus = "234567890123456"
        ),
        Paciente(
            pessoa = Pessoa(
                nome = "Lucas Fernandes",
                telefone = "(21) 97777-2222",
                dataNascimento = "09/07/1988",
                cpf = "345.678.901-22",
                rg = "34.567.890-1",
            ),
            status = false,
            nomeMae = "Helena Fernandes",
            nomePai = "Rogério Fernandes",
            nomeOutro = "Helena Fernandes",
            cartaoSus = "345678901234567"
        ),
        Paciente(
            pessoa = Pessoa(
                nome = "Juliana Castro",
                telefone = "(31) 96666-3333",
                dataNascimento = "23/11/2002",
                cpf = "456.789.012-33",
                rg = "45.678.901-2",
            ),
            status = true,
            nomeMae = "Renata Castro",
            nomePai = "Fabiano Castro",
            nomeOutro = "Renata Castro",
            cartaoSus = "456789012345678"
        ),
        Paciente(
            pessoa = Pessoa(
                nome = "Bruno Lima",
                telefone = "(41) 95555-4444",
                dataNascimento = "04/05/1999",
                cpf = "567.890.123-44",
                rg = "56.789.012-3",
            ),
            status = true,
            nomeMae = "Sandra Lima",
            nomePai = "Marcos Lima",
            nomeOutro = "Sandra Lima",
            cartaoSus = "567890123456789"
        ),
        Paciente(
            pessoa = Pessoa(
                nome = "Patrícia Melo",
                telefone = "(51) 94444-5555",
                dataNascimento = "18/08/1991",
                cpf = "678.901.234-55",
                rg = "67.890.123-4",
            ),
            status = false,
            nomeMae = "Elaine Melo",
            nomePai = "Roberto Melo",
            nomeOutro = "Elaine Melo",
            cartaoSus = "678901234567890"
        ),
        Paciente(
            pessoa = Pessoa(
                nome = "Diego Martins",
                telefone = "(61) 93333-6666",
                dataNascimento = "12/12/1985",
                cpf = "789.012.345-66",
                rg = "78.901.234-5"
            ),
            status = true,
            nomeMae = "Vera Martins",
            nomePai = "Ricardo Martins",
            nomeOutro = "Vera Martins",
            cartaoSus = "789012345678901"
        ),
        Paciente(
            pessoa = Pessoa(
                nome = "Carla Oliveira",
                telefone = "(71) 92222-7777",
                dataNascimento = "27/02/1993",
                cpf = "890.123.456-77",
                rg = "89.012.345-6"
            ),
            status = true,
            nomeMae = "Joana Oliveira",
            nomePai = "Bruno Oliveira",
            nomeOutro = "Joana Oliveira",
            cartaoSus = "890123456789012"
        ),
        Paciente(
            pessoa = Pessoa(
                nome = "Felipe Costa",
                telefone = "(91) 91111-8888",
                dataNascimento = "03/06/2001",
                cpf = "901.234.567-88",
                rg = "90.123.456-7"
            ),
            status = false,
            nomeMae = "Patrícia Costa",
            nomePai = "Sérgio Costa",
            nomeOutro = "Patrícia Costa",
            cartaoSus = "901234567890123"
        ),
        Paciente(
            pessoa = Pessoa(
                nome = "Isabela Nunes",
                telefone = "(85) 90000-9999",
                dataNascimento = "31/10/1997",
                cpf = "012.345.678-99",
                rg = "01.234.567-8"
            ),
            status = true,
            nomeMae = "Silvana Nunes",
            nomePai = "José Nunes",
            nomeOutro = "Silvana Nunes",
            cartaoSus = "012345678901234"
        ),
        Paciente(
            pessoa = Pessoa(
                nome = "Pedro Silva",
                telefone = "(48) 99999-9999",
                dataNascimento = "01/01/2000",
                cpf = "123.456.789-00",
                rg = "12.345.678-9"
            ),
            status = true,
            nomeMae = "Maria Silva",
            nomePai = "João Silva",
            nomeOutro = "Ana Silva",
            cartaoSus = "123456789012345"
        ),
        Paciente(
            pessoa = Pessoa(
                nome = "Camila Rocha",
                telefone = "(11) 98888-1111",
                dataNascimento = "15/03/1995",
                cpf = "234.567.890-11",
                rg = "23.456.789-0"
            ),
            status = true,
            nomeMae = "Luciana Rocha",
            nomePai = "Carlos Rocha",
            nomeOutro = "Luciana Rocha",
            cartaoSus = "234567890123456"
        ),
        Paciente(
            pessoa = Pessoa(
                nome = "Lucas Fernandes",
                telefone = "(21) 97777-2222",
                dataNascimento = "09/07/1988",
                cpf = "345.678.901-22",
                rg = "34.567.890-1"
            ),
            status = false,
            nomeMae = "Helena Fernandes",
            nomePai = "Rogério Fernandes",
            nomeOutro = "Helena Fernandes",
            cartaoSus = "345678901234567"
        ),
        Paciente(
            pessoa = Pessoa(
                nome = "Juliana Castro",
                telefone = "(31) 96666-3333",
                dataNascimento = "23/11/2002",
                cpf = "456.789.012-33",
                rg = "45.678.901-2"
            ),
            status = true,
            nomeMae = "Renata Castro",
            nomePai = "Fabiano Castro",
            nomeOutro = "Renata Castro",
            cartaoSus = "456789012345678"
        ),
        Paciente(
            pessoa = Pessoa(
                nome = "Bruno Lima",
                telefone = "(41) 95555-4444",
                dataNascimento = "04/05/1999",
                cpf = "567.890.123-44",
                rg = "56.789.012-3"
            ),
            status = true,
            nomeMae = "Sandra Lima",
            nomePai = "Marcos Lima",
            nomeOutro = "Sandra Lima",
            cartaoSus = "567890123456789"
        ),
        Paciente(
            pessoa = Pessoa(
                nome = "Patrícia Melo",
                telefone = "(51) 94444-5555",
                dataNascimento = "18/08/1991",
                cpf = "678.901.234-55",
                rg = "67.890.123-4"
            ),
            status = false,
            nomeMae = "Elaine Melo",
            nomePai = "Roberto Melo",
            nomeOutro = "Elaine Melo",
            cartaoSus = "678901234567890"
        )
    )
}
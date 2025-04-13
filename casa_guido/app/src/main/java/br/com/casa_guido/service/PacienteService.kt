package br.com.casa_guido.service

import AppDatabase
import br.com.casa_guido.screens.Paciente

class PacienteService(
    private val db: AppDatabase
){
//    suspend fun adicionarUsuario(nome: String, email: String) {
//            db.usuarioDao().inserir(Usuario(nome = nome, email = email))
//    }
//
//    suspend fun carregarUsuarios(callback: (List<Usuario>) -> Unit) {
//            val lista = db.usuarioDao().listarTodos()
//            callback(lista)
//    }

    suspend fun createPaciente(edicao: Paciente) {

    }
}
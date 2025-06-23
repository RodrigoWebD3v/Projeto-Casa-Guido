package br.com.casa_guido.repository

import br.com.casa_guido.room.dao.ArquivoDao

class ArquivoRepository (
    private val arquivoDao: ArquivoDao
){
    suspend fun getArquivosPorPaciente(pacienteId: String) = arquivoDao.getArquivosPorPacienteId(pacienteId)

    suspend fun createArquivo(entidade: br.com.casa_guido.room.entidades.Arquivo) {
        arquivoDao.insert(entidade)
    }

    suspend fun deleteArquivosPorPaciente(pacienteId: String) {
        arquivoDao.deleteArquivosPorPaciente(pacienteId)
    }
}
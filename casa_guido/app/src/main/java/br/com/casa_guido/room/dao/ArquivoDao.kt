package br.com.casa_guido.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.casa_guido.room.entidades.Arquivo
import kotlinx.coroutines.flow.Flow

@Dao
interface ArquivoDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(arquivo: Arquivo)

    @Update
    suspend fun update(arquivo: Arquivo)

    @Delete
    suspend fun delete(arquivo: Arquivo)

    @Query("DELETE FROM arquivo WHERE pacienteId = :pacienteId")
    suspend fun deleteArquivosPorPaciente(pacienteId: String)

    @Query("SELECT * FROM arquivo WHERE pacienteId = :pacienteId")
    fun getArquivosPorPacienteId(pacienteId: String): Flow<List<Arquivo>>

    @Query("SELECT * FROM arquivo")
    fun getAll(): Flow<List<Arquivo>>

    @Query("SELECT * FROM arquivo WHERE id = :id")
    suspend fun getById(id: String): Arquivo?
}
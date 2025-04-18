package br.com.casa_guido.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.casa_guido.room.entidades.Quimioterapia
import kotlinx.coroutines.flow.Flow

@Dao
interface QuimioterapiaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quimioterapia: Quimioterapia)

    @Delete
    suspend fun delete(quimioterapia: Quimioterapia)

    @Query("DELETE FROM quimioterapia WHERE pacienteId = :pacienteId")
    suspend fun deleteQuimios(pacienteId: String)

    @Query("SELECT * FROM quimioterapia")
    fun getAll(): Flow<List<Quimioterapia>>

    @Query("SELECT * FROM quimioterapia WHERE id = :id")
    suspend fun getById(id: String): Quimioterapia?

    @Query("SELECT * FROM quimioterapia WHERE pacienteId = :pacienteId")
    fun getQuimiosByPacienteId(pacienteId: String): Flow<List<Quimioterapia>>
}

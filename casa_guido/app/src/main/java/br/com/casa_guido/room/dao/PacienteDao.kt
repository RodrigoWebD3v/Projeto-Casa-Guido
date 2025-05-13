package br.com.casa_guido.room.dao

import androidx.room.*
import br.com.casa_guido.room.entidades.Paciente
import kotlinx.coroutines.flow.Flow

@Dao
interface PacienteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(paciente: Paciente)

    @Update
    suspend fun update(paciente: Paciente)

    @Delete
    suspend fun delete(paciente: Paciente)

    @Query("SELECT * FROM paciente")
    fun getAll(): Flow<List<Paciente>>

    @Query("SELECT * FROM paciente WHERE id = :id")
    suspend fun getById(id: String): Paciente?
}

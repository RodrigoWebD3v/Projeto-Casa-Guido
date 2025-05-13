package br.com.casa_guido.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.casa_guido.room.entidades.Cirurgia
import br.com.casa_guido.room.entidades.Entrevista
import kotlinx.coroutines.flow.Flow

@Dao
interface EntrevistaDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(entrevista: Entrevista)

    @Update
    suspend fun update(entrevista: Entrevista)

    @Delete
    suspend fun delete(entrevista: Entrevista)

    @Query("SELECT * FROM entrevista")
    fun getAll(): Flow<List<Entrevista>>

    @Query("SELECT * FROM entrevista WHERE id = :id")
    suspend fun getById(id: String): Entrevista?
}

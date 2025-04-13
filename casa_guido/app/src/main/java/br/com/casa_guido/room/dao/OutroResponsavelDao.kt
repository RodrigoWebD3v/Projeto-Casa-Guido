package br.com.casa_guido.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.casa_guido.room.entidades.OutroResponsavel
import kotlinx.coroutines.flow.Flow

@Dao
interface OutroResponsavelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(outro: OutroResponsavel)

    @Update
    suspend fun update(outro: OutroResponsavel)

    @Delete
    suspend fun delete(outro: OutroResponsavel)

    @Query("SELECT * FROM outro_responsavel")
    fun getAll(): Flow<List<OutroResponsavel>>

    @Query("SELECT * FROM outro_responsavel WHERE id = :id")
    suspend fun getById(id: Long): OutroResponsavel?
}

package br.com.casa_guido.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.casa_guido.room.entidades.Cirurgia
import kotlinx.coroutines.flow.Flow

@Dao
interface CirurgiaDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(cirurgia: Cirurgia)

    @Update
    suspend fun update(cirurgia: Cirurgia)

    @Delete
    suspend fun delete(cirurgia: Cirurgia)

    @Query("SELECT * FROM cirurgia")
    fun getAll(): Flow<List<Cirurgia>>

    @Query("SELECT * FROM cirurgia WHERE id = :id")
    suspend fun getById(id: Long): Cirurgia?
}

package br.com.casa_guido.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.casa_guido.room.entidades.Responsavel
import kotlinx.coroutines.flow.Flow

@Dao
interface ResponsavelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(responsavel: Responsavel)

    @Update
    suspend fun update(responsavel: Responsavel)

    @Delete
    suspend fun delete(responsavel: Responsavel)

    @Query("SELECT * FROM responsavel")
    fun getAll(): Flow<List<Responsavel>>

    @Query("SELECT * FROM responsavel WHERE id = :id")
    suspend fun getById(id: Long): Responsavel?
}

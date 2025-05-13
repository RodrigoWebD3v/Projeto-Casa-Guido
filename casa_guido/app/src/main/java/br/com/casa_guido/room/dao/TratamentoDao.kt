package br.com.casa_guido.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.casa_guido.room.entidades.Tratamento
import kotlinx.coroutines.flow.Flow

@Dao
interface TratamentoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tratamento: Tratamento)

    @Update
    suspend fun update(tratamento: Tratamento)

    @Delete
    suspend fun delete(tratamento: Tratamento)

    @Query("SELECT * FROM tratamento")
    fun getAll(): Flow<List<Tratamento>>

    @Query("SELECT * FROM tratamento WHERE id = :id")
    suspend fun getById(id: String): Tratamento?
}

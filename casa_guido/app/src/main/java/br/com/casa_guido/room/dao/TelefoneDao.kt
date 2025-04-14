package br.com.casa_guido.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.casa_guido.room.entidades.Cirurgia
import br.com.casa_guido.room.entidades.Telefone
import kotlinx.coroutines.flow.Flow

@Dao
interface TelefoneDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(telefone: Telefone)

    @Update
    suspend fun update(telefone: Telefone)

    @Delete
    suspend fun delete(telefone: Telefone)

    @Query("SELECT * FROM telefone")
    fun getAll(): Flow<List<Telefone>>

    @Query("SELECT * FROM telefone WHERE id = :id")
    suspend fun getById(id: String): Telefone?
}

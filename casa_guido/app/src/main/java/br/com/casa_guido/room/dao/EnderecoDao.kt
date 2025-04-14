package br.com.casa_guido.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.casa_guido.room.entidades.Endereco
import br.com.casa_guido.room.entidades.Pessoa
import kotlinx.coroutines.flow.Flow

@Dao
interface EnderecoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(endereco: Endereco)

    @Update
    suspend fun update(endereco: Endereco)

    @Delete
    suspend fun delete(endereco: Endereco)

    @Query("SELECT * FROM endereco")
    fun getAll(): Flow<List<Endereco>>

    @Query("SELECT * FROM endereco WHERE id = :id")
    suspend fun getById(id: String): Endereco?
}

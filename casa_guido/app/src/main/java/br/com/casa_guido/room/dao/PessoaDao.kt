package br.com.casa_guido.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.casa_guido.room.entidades.Pessoa
import kotlinx.coroutines.flow.Flow

@Dao
interface PessoaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pessoa: Pessoa)

    @Update
    suspend fun update(pessoa: Pessoa)

    @Delete
    suspend fun delete(pessoa: Pessoa)

    @Query("SELECT * FROM pessoa")
    fun getAll(): Flow<List<Pessoa>>

    @Query("SELECT * FROM pessoa WHERE id = :id")
    suspend fun getById(id: String): Pessoa?
}

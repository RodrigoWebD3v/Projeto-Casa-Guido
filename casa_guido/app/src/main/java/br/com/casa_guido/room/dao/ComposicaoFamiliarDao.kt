package br.com.casa_guido.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.casa_guido.room.entidades.ComposicaoFamiliar
import kotlinx.coroutines.flow.Flow

@Dao
interface ComposicaoFamiliarDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ComposicaoFamiliar)

    @Update
    suspend fun update(item: ComposicaoFamiliar)

    @Delete
    suspend fun delete(item: ComposicaoFamiliar)

    @Query("SELECT * FROM composicao_familiar")
    fun getAll(): Flow<List<ComposicaoFamiliar>>

    @Query("SELECT * FROM composicao_familiar WHERE id = :id")
    suspend fun getById(id: String): ComposicaoFamiliar?
}

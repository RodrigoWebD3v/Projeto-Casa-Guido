package br.com.casa_guido.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.casa_guido.room.entidades.SituacaoHabitacional
import kotlinx.coroutines.flow.Flow

@Dao
interface SituacaoHabitacionalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(situacao: SituacaoHabitacional)

    @Update
    suspend fun update(situacao: SituacaoHabitacional)

    @Delete
    suspend fun delete(situacao: SituacaoHabitacional)

    @Query("SELECT * FROM situacao_habitacional")
    fun getAll(): Flow<List<SituacaoHabitacional>>

    @Query("SELECT * FROM situacao_habitacional WHERE id = :id")
    suspend fun getById(id: Long): SituacaoHabitacional?
}

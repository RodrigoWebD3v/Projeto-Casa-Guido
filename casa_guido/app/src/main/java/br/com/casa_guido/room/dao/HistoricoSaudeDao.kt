package br.com.casa_guido.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.casa_guido.room.entidades.HistoricoSaude
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoricoSaudeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(historico: HistoricoSaude)

    @Update
    suspend fun update(historico: HistoricoSaude)

    @Delete
    suspend fun delete(historico: HistoricoSaude)

    @Query("SELECT * FROM historico_saude")
    fun getAll(): Flow<List<HistoricoSaude>>

    @Query("SELECT * FROM historico_saude WHERE id = :id")
    suspend fun getById(id: Long): HistoricoSaude?
}

package br.com.casa_guido.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.casa_guido.room.entidades.Cirurgia
import br.com.casa_guido.room.entidades.ProfissionalResponsavel
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfissionalResponsavelDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(profissional_responsavel: ProfissionalResponsavel)

    @Update
    suspend fun update(profissional_responsavel: ProfissionalResponsavel)

    @Delete
    suspend fun delete(profissional_responsavel: ProfissionalResponsavel)

    @Query("SELECT * FROM profissional_responsavel")
    fun getAll(): Flow<List<ProfissionalResponsavel>>

    @Query("SELECT * FROM profissional_responsavel WHERE id = :id")
    suspend fun getById(id: String): ProfissionalResponsavel?
}

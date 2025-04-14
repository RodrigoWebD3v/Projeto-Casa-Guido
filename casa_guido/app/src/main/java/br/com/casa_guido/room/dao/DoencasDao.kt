package br.com.casa_guido.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.casa_guido.room.entidades.Cirurgia
import br.com.casa_guido.room.entidades.Doencas
import br.com.casa_guido.room.entidades.Telefone
import kotlinx.coroutines.flow.Flow

@Dao
interface DoencasDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(doencas: Doencas)

    @Update
    suspend fun update(doencas: Doencas)

    @Delete
    suspend fun delete(doencas: Doencas)

    @Query("SELECT * FROM doencas")
    fun getAll(): Flow<List<Doencas>>

    @Query("SELECT * FROM doencas WHERE id = :id")
    suspend fun getById(id: String): Doencas?
}

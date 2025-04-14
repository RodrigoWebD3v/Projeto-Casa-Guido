package br.com.casa_guido.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.casa_guido.room.entidades.Cirurgia
import br.com.casa_guido.room.entidades.Quimioterapia
import br.com.casa_guido.room.entidades.RadioTerapia
import kotlinx.coroutines.flow.Flow

@Dao
interface RadioTerapiaDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(radioterapia: RadioTerapia)

    @Update
    suspend fun update(radioterapia: RadioTerapia)

    @Delete
    suspend fun delete(radioterapia: RadioTerapia)

    @Query("SELECT * FROM radioterapia")
    fun getAll(): Flow<List<RadioTerapia>>

    @Query("SELECT * FROM radioterapia WHERE id = :id")
    suspend fun getById(id: String): RadioTerapia?
}

package br.com.casa_guido.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.casa_guido.room.entidades.Cirurgia
import br.com.casa_guido.room.entidades.Quimioterapia
import kotlinx.coroutines.flow.Flow

@Dao
interface QuimioterapiaDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(quimioterapia: Quimioterapia)

    @Update
    suspend fun update(quimioterapia: Quimioterapia)

    @Delete
    suspend fun delete(quimioterapia: Quimioterapia)

    @Query("SELECT * FROM quimioterapia")
    fun getAll(): Flow<List<Quimioterapia>>

    @Query("SELECT * FROM quimioterapia WHERE id = :id")
    suspend fun getById(id: String): Quimioterapia?
}

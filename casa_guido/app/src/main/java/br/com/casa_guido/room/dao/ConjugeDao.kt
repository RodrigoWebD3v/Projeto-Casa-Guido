package br.com.casa_guido.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.casa_guido.room.entidades.Conjuge
import kotlinx.coroutines.flow.Flow

@Dao
interface ConjugeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(conjuge: Conjuge)

    @Update
    suspend fun update(conjuge: Conjuge)

    @Delete
    suspend fun delete(conjuge: Conjuge)

    @Query("SELECT * FROM conjuge")
    fun getAll(): Flow<List<Conjuge>>

    @Query("SELECT * FROM conjuge WHERE id = :id")
    suspend fun getById(id: Long): Conjuge?
}

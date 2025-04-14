import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.casa_guido.room.dao.*
import br.com.casa_guido.room.entidades.*

@Database(
    entities = [
        Pessoa::class,
        Endereco::class,
        Paciente::class,
        Cirurgia::class,
        Tratamento::class,
        HistoricoSaude::class,
        SituacaoHabitacional::class,
        ComposicaoFamiliar::class,
        Quimioterapia::class,
        RadioTerapia::class,
        Telefone::class,
        Doencas::class,
        ProfissionalResponsavel::class,
        Entrevista::class,


    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pessoaDao(): PessoaDao
    abstract fun enderecoDao(): EnderecoDao
    abstract fun pacienteDao(): PacienteDao
    abstract fun quimioterapiaDao(): QuimioterapiaDao
    abstract fun radioterapiaDao(): RadioTerapiaDao
    abstract fun telefoneDao(): TelefoneDao
    abstract fun cirurgiaDao(): CirurgiaDao
    abstract fun tratamentoDao(): TratamentoDao
    abstract fun historicoSaudeDao(): HistoricoSaudeDao
    abstract fun situacaoHabitacionalDao(): SituacaoHabitacionalDao
    abstract fun composicaoFamiliarDao(): ComposicaoFamiliarDao
    abstract fun doencasDao(): DoencasDao
    abstract fun profissionalResponsavelDao(): ProfissionalResponsavelDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "casa_guido_local.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

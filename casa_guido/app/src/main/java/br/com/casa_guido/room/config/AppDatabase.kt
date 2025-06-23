import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.casa_guido.room.Converters
import br.com.casa_guido.room.dao.ArquivoDao
import br.com.casa_guido.room.dao.CirurgiaDao
import br.com.casa_guido.room.dao.ComposicaoFamiliarDao
import br.com.casa_guido.room.dao.EnderecoDao
import br.com.casa_guido.room.dao.HistoricoSaudeDao
import br.com.casa_guido.room.dao.PacienteDao
import br.com.casa_guido.room.dao.PessoaDao
import br.com.casa_guido.room.dao.ProfissionalResponsavelDao
import br.com.casa_guido.room.dao.QuimioterapiaDao
import br.com.casa_guido.room.dao.RadioTerapiaDao
import br.com.casa_guido.room.dao.SituacaoHabitacionalDao
import br.com.casa_guido.room.dao.TratamentoDao
import br.com.casa_guido.room.entidades.Arquivo
import br.com.casa_guido.room.entidades.Cirurgia
import br.com.casa_guido.room.entidades.ComposicaoFamiliar
import br.com.casa_guido.room.entidades.Endereco
import br.com.casa_guido.room.entidades.Entrevista
import br.com.casa_guido.room.entidades.HistoricoSaude
import br.com.casa_guido.room.entidades.Paciente
import br.com.casa_guido.room.entidades.Pessoa
import br.com.casa_guido.room.entidades.ProfissionalResponsavel
import br.com.casa_guido.room.entidades.Quimioterapia
import br.com.casa_guido.room.entidades.RadioTerapia
import br.com.casa_guido.room.entidades.SituacaoHabitacional
import br.com.casa_guido.room.entidades.Tratamento

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
        ProfissionalResponsavel::class,
        Entrevista::class,
        Arquivo::class, // Uncomment if Arquivo entity is defined
    ],
    version = 24,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pessoaDao(): PessoaDao
    abstract fun enderecoDao(): EnderecoDao
    abstract fun pacienteDao(): PacienteDao
    abstract fun quimioterapiaDao(): QuimioterapiaDao
    abstract fun radioterapiaDao(): RadioTerapiaDao
    abstract fun cirurgiaDao(): CirurgiaDao
    abstract fun tratamentoDao(): TratamentoDao
    abstract fun historicoSaudeDao(): HistoricoSaudeDao
    abstract fun situacaoHabitacionalDao(): SituacaoHabitacionalDao
    abstract fun composicaoFamiliarDao(): ComposicaoFamiliarDao
    abstract fun profissionalResponsavelDao(): ProfissionalResponsavelDao
    abstract fun arquivoDao(): ArquivoDao

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

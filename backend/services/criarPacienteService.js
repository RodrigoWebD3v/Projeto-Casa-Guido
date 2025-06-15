
const { pacienteDTO } = require('../dto/pacienteDTO/pacienteDTO');
const { criarPessoaService } = require('./pessoaService');
const { criarPacineteRepository } = require('../repository/pacienteRepository');
const { criarHistoricoSaudeService } = require('./historicoSaudeService');
const { criarSituacaoHabitacionalService } = require('./situacaoHabitacionalService');

const criarPacienteService = async (body) => {

   try{
      const pessoaPaciete = await criarPessoaService(body.paciente.pessoa)
      const pessoaPai = await criarPessoaService(body.paciente.responsavel)
      const pessoaMae = await criarPessoaService(body.paciente.conjugeResponsavel)
      const pessoaOutro = await criarPessoaService(body.paciente.outroResponsavel)

      const criarPaciente = pacienteDTO(body.paciente, pessoaPaciete.id, pessoaPai.id, pessoaMae.id, pessoaOutro.id)
      
      const pacienteSalvo = await criarPacineteRepository(criarPaciente)

      criarHistoricoSaudeService(body.paciente.historicoSaude, pacienteSalvo.id)

      criarSituacaoHabitacionalService(body.paciente.situacaoHabitacional,  pacienteSalvo.id)

      return pacienteSalvo
   }catch(e)
   {
      console.log("Erro ao criar paciente", e)
   }
   //cria as pessoas ates de criar o restante dos registros
   
};

module.exports = { criarPacienteService };
const { pacienteDTO } = require('../dto/pacienteDTO/pacienteDTO');
const { criarPessoaService } = require('./pessoaService');
const { criarPacienteRepository } = require('../repository/pacienteRepository');
const { criarHistoricoSaudeService } = require('./historicoSaudeService');
const { criarSituacaoHabitacionalService } = require('./situacaoHabitacionalService');
const { criarCirurgiaService } = require('./cirurgiaService');
const { criarQuimioterapiaService } = require('./quimioterapiaService');
const { criarRadioterapiaService } = require('./radioterapiaService');
const { createPacienteResponseDTO } = require('./../dto/responseDTO/createPacienteDTO');

const criarPacienteService = async (body) => {

   try{
      // Verifica se os dados da pessoa paciente são válidos
      if (!body.pessoa || !body.pessoa.nome) {
         throw new Error('Dados da pessoa paciente são obrigatórios');
      }

      const pessoaPaciente = await criarPessoaService(body.pessoa);
      
      // Cria as pessoas responsáveis apenas se os dados forem fornecidos
      let pessoaPai = null;
      let pessoaMae = null;
      let pessoaOutro = null;

      if (body.responsavel && body.responsavel.nome) {
         try {
            pessoaPai = await criarPessoaService(body.responsavel);
         } catch (error) {
            console.warn('Erro ao criar pessoa pai:', error.message);
            pessoaPai = null;
         }
      }

      if (body.conjugeResponsavel && body.conjugeResponsavel.nome) {
         try {
            pessoaMae = await criarPessoaService(body.conjugeResponsavel);
         } catch (error) {
            console.warn('Erro ao criar pessoa mãe:', error.message);
            pessoaMae = null;
         }
      }

      if (body.outroResponsavel && body.outroResponsavel.nome) {
         try {
            pessoaOutro = await criarPessoaService(body.outroResponsavel);
         } catch (error) {
            console.warn('Erro ao criar outro responsável:', error.message);
            pessoaOutro = null;
         }
      }

      const criarPaciente = pacienteDTO(body, pessoaPaciente, pessoaPai, pessoaMae, pessoaOutro);
      
      const pacienteSalvo = await criarPacienteRepository(criarPaciente);

      // Cria os registros relacionados apenas se os dados forem fornecidos
      if (body.historicoSaude) {
         await criarHistoricoSaudeService(body.historicoSaude, pacienteSalvo.id);
      }

      if (body.situacaoHabitacional) {
         await criarSituacaoHabitacionalService(body.situacaoHabitacional, pacienteSalvo.id);
      }

      // Cria as cirurgias como entidades independentes
      if (body.cirurgias && body.cirurgias.length > 0) {
         for (const cirurgia of body.cirurgias) {
            await criarCirurgiaService(cirurgia, pacienteSalvo.id);
         }
      }

      // Cria as quimioterapias como entidades independentes
      if (body.quimios && body.quimios.length > 0) {
         for (const quimio of body.quimios) {
            await criarQuimioterapiaService(quimio, pacienteSalvo.id);
         }
      }

      // Cria as radioterapias como entidades independentes
      if (body.radios && body.radios.length > 0) {
         for (const radio of body.radios) {
            await criarRadioterapiaService(radio, pacienteSalvo.id);
         }
      }

      const dtoResponse = createPacienteResponseDTO(pacienteSalvo)

      console.log(dtoResponse)

      return dtoResponse;
   } catch(e) {
      console.log("Erro ao criar paciente", e);
      throw e; 
   }
};



module.exports = { criarPacienteService };
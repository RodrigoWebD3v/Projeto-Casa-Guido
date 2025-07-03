const { pacienteDTO } = require('../dto/pacienteDTO/pacienteDTO');
const { editarPessoaService } = require('./pessoaService');
const { editarPacienteRepository, buscarPacientePorIdRepository } = require('../repository/pacienteRepository');
const { editarHistoricoSaudeService } = require('./historicoSaudeService');
const { editarSituacaoHabitacionalService } = require('./situacaoHabitacionalService');
const { editarCirurgiaService } = require('./cirurgiaService');
const { editarQuimioterapiaService } = require('./quimioterapiaService');
const { editarRadioterapiaService } = require('./radioterapiaService');
const { criarQuimioterapiaService } = require('./quimioterapiaService');
const { criarPessoaService } = require('./pessoaService');
const { updatePacienteResponseDTO } = require('../dto/responseDTO/updatePacienteDTO');


const AppError = require('../handlerException/appError');

const editarPacienteService = async (id, body) => {
   try {
      const pacienteExistente = await buscarPacientePorIdRepository(id);
      if (!pacienteExistente) {
         throw new AppError('Paciente não encontrado', 404);
      }

      if (!body.pessoa || !body.pessoa.nome) {
         throw new Error('Dados da pessoa paciente são obrigatórios');
      }

      const pessoaPaciente = await editarPessoaService(pacienteExistente.pessoa, body.pessoa);

      let pessoaPai = null;
      let pessoaMae = null;
      let pessoaOutro = null;

      if (body.responsavel && body.responsavel.nome) {
         try {
            if (pacienteExistente.responsavel) {
               pessoaPai = await editarPessoaService(pacienteExistente.responsavel, body.responsavel);
            } else {
               pessoaPai = await criarPessoaService(body.responsavel);
            }
         } catch (error) {
            console.warn('Erro ao editar pessoa pai:', error.message);
            pessoaPai = null;
         }
      }

      if (body.conjugeResponsavel && body.conjugeResponsavel.nome) {
         try {
            if (pacienteExistente.conjugeResponsavel) {
               pessoaMae = await editarPessoaService(pacienteExistente.conjugeResponsavel, body.conjugeResponsavel);
            } else {
               pessoaMae = await criarPessoaService(body.conjugeResponsavel);
            }
         } catch (error) {
            console.warn('Erro ao editar pessoa mãe:', error.message);
            pessoaMae = null;
         }
      }

      if (body.outroResponsavel && body.outroResponsavel.nome) {
         try {
            if (pacienteExistente.outroResponsavel) {
               pessoaOutro = await editarPessoaService(pacienteExistente.outroResponsavel, body.outroResponsavel);
            } else {
               pessoaOutro = await criarPessoaService(body.outroResponsavel);
            }
         } catch (error) {
            console.warn('Erro ao editar outro responsável:', error.message);
            pessoaOutro = null;
         }
      }

      const dadosPaciente = pacienteDTO(body, pessoaPaciente, pessoaPai, pessoaMae, pessoaOutro);
      
      const pacienteAtualizado = await editarPacienteRepository(id, dadosPaciente);

      if (body.historicoSaude) {
         await editarHistoricoSaudeService(pacienteAtualizado.id, body.historicoSaude);
      }

      if (body.situacaoHabitacional) {
         await editarSituacaoHabitacionalService(pacienteAtualizado.id, body.situacaoHabitacional);
      }

      if (body.cirurgias && body.cirurgias.length > 0) {
         for (const cirurgia of body.cirurgias) {
            if (cirurgia.id) {
               await editarCirurgiaService(cirurgia.id, cirurgia, pacienteAtualizado.id);
            } else {
               const { criarCirurgiaService } = require('./cirurgiaService');
               await criarCirurgiaService(cirurgia, pacienteAtualizado.id);
            }
         }
      }

      if (body.quimios && body.quimios.length > 0) {
         for (const quimio of body.quimios) {
            if (quimio.id) {
               await editarQuimioterapiaService(quimio.id, quimio, pacienteAtualizado.id);
            } else {
               await criarQuimioterapiaService(quimio, pacienteAtualizado.id);
            }
         }
      }

      if (body.radios && body.radios.length > 0) {
         for (const radio of body.radios) {
            if (radio.id) {
               await editarRadioterapiaService(radio.id, radio, pacienteAtualizado.id);
            } else {
               const { criarRadioterapiaService } = require('./radioterapiaService');
               await criarRadioterapiaService(radio, pacienteAtualizado.id);
            }
         }
      }

      const dtoResponse = updatePacienteResponseDTO(pacienteAtualizado)

      return dtoResponse;
   } catch(e) {
      console.log("Erro ao editar paciente", e);
      throw e; 
   }
};

const buscarPacientePorIdService = async (id) => {
   try {
      const paciente = await buscarPacientePorIdRepository(id);
      if (!paciente) {
         throw new AppError('Paciente não encontrado', 404);
      }
      return paciente;
   } catch (e) {
      console.log("Erro ao buscar paciente por id", e);
      throw e;
   }
}

module.exports = { editarPacienteService, buscarPacientePorIdService }; 
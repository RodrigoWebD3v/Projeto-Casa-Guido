const { editarPacienteRepository, buscarPacientePorIdRepository } = require('../../repository/pacienteRepository');
const { escreverLog } = require('../../utils/escreverLog');
const AppError = require('../../handlerException/appError');
const { 
   getDefaultValue, 
   getDefaultArray, 
   getDefaultObject, 
   getDefaultString, 
   getDefaultNumber, 
   getDefaultBoolean 
} = require('../../utils/defaultValues');

const editarPacienteService = async (id, body) => {
   try {
      await escreverLog(`Iniciando edição de paciente: ${id}`);
      
      const pacienteExistente = await buscarPacientePorIdRepository(id);
      if (!pacienteExistente) {
         throw new AppError('Paciente não encontrado', 404);
      }

      if (!body.pessoa || !body.pessoa.nome) {
         throw new AppError('Dados da pessoa paciente são obrigatórios', 400);
      }

      // Preparar dados do paciente com estrutura embutida e valores padrão
      const dadosPaciente = {
         // Dados da pessoa principal (paciente)
         pessoa: body.pessoa,
         
         // Dados básicos do paciente com valores padrão
         status: getDefaultBoolean(body.status, pacienteExistente.status || false),
         nomeMae: getDefaultString(body.nomeMae, pacienteExistente.nomeMae || ""),
         nomePai: getDefaultString(body.nomePai, pacienteExistente.nomePai || ""),
         nomeOutro: getDefaultString(body.nomeOutro, pacienteExistente.nomeOutro || ""),
         recebeRemuneracao: getDefaultNumber(body.recebeRemuneracao, pacienteExistente.recebeRemuneracao || 0),
         remuneracao: getDefaultString(body.remuneracao, pacienteExistente.remuneracao || ""),
         recebeBpc: getDefaultNumber(body.recebeBpc, pacienteExistente.recebeBpc || 0),
         valorBpc: getDefaultString(body.valorBpc, pacienteExistente.valorBpc || ""),
         diagnostico: getDefaultString(body.diagnostico, pacienteExistente.diagnostico || ""),
         escolaNome: getDefaultString(body.escolaNome, pacienteExistente.escolaNome || ""),
         anoEscolar: getDefaultString(body.anoEscolar, pacienteExistente.anoEscolar || ""),
         tamRoupa: getDefaultString(body.tamRoupa, pacienteExistente.tamRoupa || ""),
         tamCalcado: getDefaultString(body.tamCalcado, pacienteExistente.tamCalcado || ""),
         origenInfoOng: getDefaultString(body.origenInfoOng, pacienteExistente.origenInfoOng || ""),
         idBackend: getDefaultString(body.idBackend, pacienteExistente.idBackend || ""),
         alterado: getDefaultBoolean(body.alterado, pacienteExistente.alterado || false),
         observacoes: getDefaultArray(body.observacoes || pacienteExistente.observacoes),
         
         // Responsáveis com valores padrão
         responsavel: getDefaultObject(body.responsavel, pacienteExistente.responsavel || {}),
         conjugeResponsavel: getDefaultObject(body.conjugeResponsavel, pacienteExistente.conjugeResponsavel || {}),
         outroResponsavel: getDefaultObject(body.outroResponsavel, pacienteExistente.outroResponsavel || {}),
         
         // UBS e CRAS com valores padrão
         ubs: getDefaultObject(body.ubs, pacienteExistente.ubs || { municipio: "", bairro: "" }),
         cras: getDefaultObject(body.cras, pacienteExistente.cras || { municipio: "", bairro: "" }),
         
         // Histórico médico com valores padrão
         cirurgias: getDefaultArray(body.cirurgias || pacienteExistente.cirurgias),
         quimioterapias: getDefaultArray(body.quimioterapias || pacienteExistente.quimioterapias),
         radioterapias: getDefaultArray(body.radioterapias || pacienteExistente.radioterapias),
         historicoSaude: getDefaultObject(body.historicoSaude, pacienteExistente.historicoSaude || {
            doencasFamilia: [],
            medicamentosUsoContinuo: "",
            localProcuraAtendimento: [],
            recebeBeneficio: 0,
            beneficioDescricao: ""
         }),
         
         // Dados socioeconômicos com valores padrão
         composicaoFamiliar: getDefaultArray(body.composicaoFamiliar || pacienteExistente.composicaoFamiliar),
         situacaoHabitacional: getDefaultObject(body.situacaoHabitacional, pacienteExistente.situacaoHabitacional || {
            comoAdquiriuCasa: 0,
            area: 0,
            numeroComodos: 0,
            material: 0,
            bens: [],
            meioDeTransporte: 0,
            meioDeComunicao: 0,
            possuiBanheiros: 0
         }),
         
         // Profissional responsável com valores padrão
         profissionalResponsavel: getDefaultObject(body.profissionalResponsavel, pacienteExistente.profissionalResponsavel || {
            nome: "",
            crm: ""
         })
      };

      await escreverLog("Atualizando paciente com estrutura embutida");
      const pacienteAtualizado = await editarPacienteRepository(id, dadosPaciente);

      await escreverLog(`Paciente atualizado com sucesso: ${pacienteAtualizado._id}`);

      return {
         success: true,
         data: pacienteAtualizado,
         message: "Paciente atualizado com sucesso"
      };
   } catch(e) {
      await escreverLog(`Erro ao editar paciente: ${e.message}`);
      console.log("Erro ao editar paciente", e);
      throw e; 
   }
};

const buscarPacientePorIdService = async (id) => {
   try {
      await escreverLog(`Buscando paciente por ID: ${id}`);
      
      const paciente = await buscarPacientePorIdRepository(id);
      if (!paciente) {
         throw new AppError('Paciente não encontrado', 404);
      }
      
      await escreverLog(`Paciente encontrado: ${paciente._id}`);
      
      return {
         success: true,
         data: paciente
      };
   } catch (e) {
      await escreverLog(`Erro ao buscar paciente por id: ${e.message}`);
      console.log("Erro ao buscar paciente por id", e);
      throw e;
   }
}

module.exports = { editarPacienteService, buscarPacientePorIdService }; 
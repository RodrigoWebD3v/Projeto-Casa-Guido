const { criarPacienteRepository } = require('../../repository/pacienteRepository');
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

const criarPacienteService = async (body) => {
   try {
      await escreverLog("Iniciando criação de paciente com estrutura embutida");
      
      // Verifica se os dados da pessoa paciente são válidos
      if (!body.pessoa || !body.pessoa.nome) {
         throw new AppError('Dados da pessoa paciente são obrigatórios', 400);
      }

      // Preparar dados do paciente com estrutura embutida e valores padrão
      const dadosPaciente = {
         // Dados da pessoa principal (paciente) - OBRIGATÓRIO
         pessoa: body.pessoa,
         
         // Dados básicos do paciente com valores padrão
         status: getDefaultBoolean(body.status, false),
         nomeMae: getDefaultString(body.nomeMae, ""),
         nomePai: getDefaultString(body.nomePai, ""),
         nomeOutro: getDefaultString(body.nomeOutro, ""),
         recebeRemuneracao: getDefaultNumber(body.recebeRemuneracao, 0),
         remuneracao: getDefaultString(body.remuneracao, ""),
         recebeBpc: getDefaultNumber(body.recebeBpc, 0),
         valorBpc: getDefaultString(body.valorBpc, ""),
         diagnostico: getDefaultString(body.diagnostico, ""),
         escolaNome: getDefaultString(body.escolaNome, ""),
         anoEscolar: getDefaultString(body.anoEscolar, ""),
         tamRoupa: getDefaultString(body.tamRoupa, ""),
         tamCalcado: getDefaultString(body.tamCalcado, ""),
         origenInfoOng: getDefaultString(body.origenInfoOng, ""),
         idBackend: getDefaultString(body.idBackend, ""),
         alterado: getDefaultBoolean(body.alterado, false),
         observacoes: getDefaultArray(body.observacoes),
         
         // Responsáveis com valores padrão
         responsavel: getDefaultObject(body.responsavel, {}),
         conjugeResponsavel: getDefaultObject(body.conjugeResponsavel, {}),
         outroResponsavel: getDefaultObject(body.outroResponsavel, {}),
         
         // UBS e CRAS com valores padrão
         ubs: getDefaultObject(body.ubs, { municipio: "", bairro: "" }),
         cras: getDefaultObject(body.cras, { municipio: "", bairro: "" }),
         
         // Histórico médico com valores padrão
         cirurgias: getDefaultArray(body.cirurgias),
         quimioterapias: getDefaultArray(body.quimioterapias),
         radioterapias: getDefaultArray(body.radioterapias),
         historicoSaude: getDefaultObject(body.historicoSaude, {
            doencasFamilia: [],
            medicamentosUsoContinuo: "",
            localProcuraAtendimento: [],
            recebeBeneficio: 0,
            beneficioDescricao: ""
         }),
         
         // Dados socioeconômicos com valores padrão
         composicaoFamiliar: getDefaultArray(body.composicaoFamiliar),
         situacaoHabitacional: getDefaultObject(body.situacaoHabitacional, {
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
         profissionalResponsavel: getDefaultObject(body.profissionalResponsavel, {
            nome: "",
            crm: ""
         })
      };

      await escreverLog("Salvando paciente com estrutura embutida");
      const pacienteSalvo = await criarPacienteRepository(dadosPaciente);

      await escreverLog(`Paciente criado com sucesso: ${pacienteSalvo._id}`);

      return {
         data: {
            nome: pacienteSalvo.pessoa.nome,
            id: pacienteSalvo._id,
         },
      };
   } catch(e) {
      await escreverLog(`Erro ao criar paciente: ${e.message}`);
      console.log("Erro ao criar paciente", e);
      throw e; 
   }
};

module.exports = { criarPacienteService };
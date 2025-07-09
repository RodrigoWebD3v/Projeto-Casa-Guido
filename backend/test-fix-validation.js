const mongoose = require('mongoose');
require('dotenv').config();

const { criarPacienteService } = require('./services/pacienteService/criarPacienteService');

const MONGODB_URI = process.env.MONGO_URI;
const DB_NAME = process.env.DB_NAME;

async function testValidationFix() {
  try {
    console.log('Conectando ao MongoDB...');
    await mongoose.connect(MONGODB_URI, {
      dbName: DB_NAME,
    });
    console.log('Conectado ao MongoDB com sucesso!');

    console.log('\n=== Testando correção de validação ===');

    // Teste 1: Criar paciente apenas com dados obrigatórios
    console.log('\n🔧 Teste 1: Criar paciente apenas com dados obrigatórios');
    const dadosMinimos = {
      pessoa: {
        nome: "João Silva Teste",
        cpf: "123.456.789-02",
        telefone: "(11) 99999-9999"
      }
    };

    try {
      const pacienteCriado = await criarPacienteService(dadosMinimos);
      console.log('✅ Paciente criado com sucesso (apenas dados obrigatórios)');
      console.log('- ID:', pacienteCriado.data._id);
      console.log('- Nome:', pacienteCriado.data.pessoa.nome);
      console.log('- Responsável:', pacienteCriado.data.responsavel ? 'Presente' : 'Não informado');
      console.log('- Cônjuge:', pacienteCriado.data.conjugeResponsavel ? 'Presente' : 'Não informado');
      console.log('- Outro:', pacienteCriado.data.outroResponsavel ? 'Presente' : 'Não informado');
    } catch (error) {
      console.error('❌ Erro ao criar paciente com dados mínimos:', error.message);
      return;
    }

    // Teste 2: Criar paciente com responsáveis opcionais
    console.log('\n🔧 Teste 2: Criar paciente com responsáveis opcionais');
    const dadosCompletos = {
      pessoa: {
        nome: "Maria Silva Teste",
        cpf: "987.654.321-01",
        telefone: "(11) 88888-8888"
      },
      responsavel: {
        nome: "João Silva Pai",
        telefone: "(11) 77777-7777"
      },
      conjugeResponsavel: {
        nome: "Ana Silva Mãe",
        telefone: "(11) 66666-6666"
      }
      // outroResponsavel não informado (deve ser opcional)
    };

    try {
      const pacienteComResponsaveis = await criarPacienteService(dadosCompletos);
      console.log('✅ Paciente criado com sucesso (com responsáveis)');
      console.log('- ID:', pacienteComResponsaveis.data._id);
      console.log('- Nome:', pacienteComResponsaveis.data.pessoa.nome);
      console.log('- Responsável:', pacienteComResponsaveis.data.responsavel.nome);
      console.log('- Cônjuge:', pacienteComResponsaveis.data.conjugeResponsavel.nome);
      console.log('- Outro:', pacienteComResponsaveis.data.outroResponsavel ? 'Presente' : 'Não informado');
    } catch (error) {
      console.error('❌ Erro ao criar paciente com responsáveis:', error.message);
      return;
    }

    // Teste 3: Criar paciente com dados parciais de responsáveis
    console.log('\n🔧 Teste 3: Criar paciente com dados parciais de responsáveis');
    const dadosParciais = {
      pessoa: {
        nome: "Pedro Silva Teste",
        cpf: "111.222.333-44",
        telefone: "(11) 55555-5555"
      },
      responsavel: {
        nome: "Responsável Parcial"
        // Sem telefone (deve ser opcional)
      }
      // Sem conjugeResponsavel e outroResponsavel
    };

    try {
      const pacienteParcial = await criarPacienteService(dadosParciais);
      console.log('✅ Paciente criado com sucesso (dados parciais)');
      console.log('- ID:', pacienteParcial.data._id);
      console.log('- Nome:', pacienteParcial.data.pessoa.nome);
      console.log('- Responsável:', pacienteParcial.data.responsavel.nome);
      console.log('- Responsável telefone:', pacienteParcial.data.responsavel.telefone || 'Não informado');
      console.log('- Cônjuge:', pacienteParcial.data.conjugeResponsavel ? 'Presente' : 'Não informado');
      console.log('- Outro:', pacienteParcial.data.outroResponsavel ? 'Presente' : 'Não informado');
    } catch (error) {
      console.error('❌ Erro ao criar paciente com dados parciais:', error.message);
      return;
    }

    console.log('\n🎉 Todos os testes de validação passaram!');
    console.log('✅ O problema de validação foi corrigido com sucesso!');

  } catch (error) {
    console.error('❌ Erro durante os testes:', error);
  } finally {
    await mongoose.disconnect();
    console.log('Desconectado do MongoDB');
  }
}

// Executar os testes
testValidationFix(); 
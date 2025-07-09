const mongoose = require('mongoose');
require('dotenv').config();

// Importar apenas o modelo Paciente (os outros foram convertidos para subschemas)
const Paciente = require('../models/Paciente');

const MONGODB_URI = process.env.MONGO_URI;
const DB_NAME = process.env.DB_NAME;

async function migrateToEmbedded() {
  try {
    console.log('Conectando ao MongoDB...');
    await mongoose.connect(MONGODB_URI, {
      dbName: DB_NAME,
    });
    console.log('Conectado ao MongoDB com sucesso!');

    console.log('\n=== AVISO: Migração já foi executada ===');
    console.log('As models antigas foram removidas e convertidas para subschemas embutidos.');
    console.log('Se você ainda tem dados antigos que precisam ser migrados,');
    console.log('execute este script antes de remover as models antigas.');
    console.log('\nPara verificar se a migração é necessária, verifique se há pacientes');
    console.log('com referências (ObjectIds) em vez de dados embutidos.');

    // Verificar se há pacientes que ainda precisam de migração
    const pacientesComReferencias = await Paciente.find({
      $or: [
        { pessoa: { $type: "objectId" } },
        { responsavel: { $type: "objectId" } },
        { conjugeResponsavel: { $type: "objectId" } },
        { outroResponsavel: { $type: "objectId" } },
        { ubs: { $type: "objectId" } },
        { cras: { $type: "objectId" } }
      ]
    }).lean();

    if (pacientesComReferencias.length > 0) {
      console.log(`\n⚠️  ATENÇÃO: Encontrados ${pacientesComReferencias.length} pacientes que ainda precisam de migração!`);
      console.log('Execute a versão completa do script de migração antes de remover as models antigas.');
      console.log('Os pacientes com referências não funcionarão corretamente com a nova estrutura.');
    } else {
      console.log('\n✅ Todos os pacientes já estão no formato embutido!');
      console.log('A migração foi concluída com sucesso e as models antigas podem ser removidas.');
    }

    console.log('\n=== Status da migração ===');
    const totalPacientes = await Paciente.countDocuments();
    console.log(`Total de pacientes: ${totalPacientes}`);
    console.log(`Pacientes com referências: ${pacientesComReferencias.length}`);
    console.log(`Pacientes migrados: ${totalPacientes - pacientesComReferencias.length}`);

  } catch (error) {
    console.error('Erro durante a verificação:', error);
  } finally {
    await mongoose.disconnect();
    console.log('Desconectado do MongoDB');
  }
}

// Executar a verificação
migrateToEmbedded(); 
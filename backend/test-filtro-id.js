const { buscarPacientesSemIdBackendService } = require('./services/pacienteService/buscarPacientesSemIdBackendService');
const { escreverLog } = require('./utils/escreverLog');

const testarFiltroID = async () => {
  try {
    console.log('=== TESTE: Filtro de ID ===\n');

    // Primeiro, buscar todos os pacientes para ver quais IDs existem
    console.log('1. Buscando todos os pacientes...');
    const todosPacientes = await buscarPacientesSemIdBackendService({ listaId: [] });
    console.log(`✅ Total de pacientes: ${todosPacientes.count}`);
    
    if (todosPacientes.count > 0) {
      // Pegar o primeiro ID para teste
      const primeiroID = todosPacientes.data[0]._id;
      console.log(`   Primeiro ID encontrado: ${primeiroID}\n`);

      // Teste: excluir o primeiro ID
      console.log('2. Testando exclusão do primeiro ID...');
      const resultadoFiltrado = await buscarPacientesSemIdBackendService({ 
        listaId: [primeiroID] 
      });
      console.log(`✅ Pacientes após exclusão: ${resultadoFiltrado.count}`);
      console.log(`   IDs excluídos: ${resultadoFiltrado.filtros.listaId.join(', ')}`);
      
      // Verificar se o paciente excluído realmente não está no resultado
      const pacienteExcluido = resultadoFiltrado.data.find(p => p._id.toString() === primeiroID);
      if (pacienteExcluido) {
        console.log('❌ ERRO: O paciente excluído ainda está no resultado!');
      } else {
        console.log('✅ SUCESSO: O paciente foi corretamente excluído do resultado!\n');
      }

      // Teste: excluir múltiplos IDs (se houver mais de um paciente)
      if (todosPacientes.count > 1) {
        const segundoID = todosPacientes.data[1]._id;
        console.log('3. Testando exclusão de múltiplos IDs...');
        const resultadoMultiplo = await buscarPacientesSemIdBackendService({ 
          listaId: [primeiroID, segundoID] 
        });
        console.log(`✅ Pacientes após exclusão múltipla: ${resultadoMultiplo.count}`);
        console.log(`   IDs excluídos: ${resultadoMultiplo.filtros.listaId.join(', ')}`);
        
        // Verificar se ambos os pacientes foram excluídos
        const paciente1Excluido = resultadoMultiplo.data.find(p => p._id.toString() === primeiroID);
        const paciente2Excluido = resultadoMultiplo.data.find(p => p._id.toString() === segundoID);
        
        if (paciente1Excluido || paciente2Excluido) {
          console.log('❌ ERRO: Pacientes excluídos ainda estão no resultado!');
        } else {
          console.log('✅ SUCESSO: Ambos os pacientes foram corretamente excluídos!\n');
        }
      }
    } else {
      console.log('⚠️  Nenhum paciente encontrado para teste');
    }

    console.log('=== TESTE CONCLUÍDO ===');

  } catch (error) {
    console.error('❌ Erro durante o teste:', error.message);
    await escreverLog(`Erro no teste: ${error.message}`);
  }
};

// Executar o teste
testarFiltroID(); 
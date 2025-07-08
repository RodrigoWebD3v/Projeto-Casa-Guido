const { buscarPacientesSemIdBackendService } = require('./services/pacienteService/buscarPacientesSemIdBackendService');
const { escreverLog } = require('./utils/escreverLog');

const testarBuscaPacientesID = async () => {
  try {
    console.log('=== TESTE: Busca de Pacientes por ID ===\n');

    // Teste 1: Lista vazia - deve retornar todos os pacientes
    console.log('1. Testando com lista de IDs vazia...');
    const resultado1 = await buscarPacientesSemIdBackendService({ listaId: [] });
    console.log(`✅ Resultado: ${resultado1.count} pacientes encontrados`);
    console.log(`   Filtros aplicados: ${resultado1.filtros.totalIDsFiltrados} IDs filtrados\n`);

    // Teste 2: Com IDs específicos - deve excluir esses IDs
    console.log('2. Testando com IDs específicos...');
    const idsParaExcluir = ['507f1f77bcf86cd799439011', '507f1f77bcf86cd799439012'];
    const resultado2 = await buscarPacientesSemIdBackendService({ 
      listaId: idsParaExcluir 
    });
    console.log(`✅ Resultado: ${resultado2.count} pacientes encontrados`);
    console.log(`   IDs excluídos: ${resultado2.filtros.listaId.join(', ')}`);
    console.log(`   Filtros aplicados: ${resultado2.filtros.totalIDsFiltrados} IDs filtrados\n`);

    // Teste 3: Sem body - deve retornar todos os pacientes
    console.log('3. Testando sem body...');
    const resultado3 = await buscarPacientesSemIdBackendService();
    console.log(`✅ Resultado: ${resultado3.count} pacientes encontrados`);
    console.log(`   Filtros aplicados: ${resultado3.filtros.totalIDsFiltrados} IDs filtrados\n`);

    // Teste 4: Body com listaId null
    console.log('4. Testando com listaId null...');
    const resultado4 = await buscarPacientesSemIdBackendService({ listaId: null });
    console.log(`✅ Resultado: ${resultado4.count} pacientes encontrados`);
    console.log(`   Filtros aplicados: ${resultado4.filtros.totalIDsFiltrados} IDs filtrados\n`);

    // Teste 5: Body com listaId undefined
    console.log('5. Testando com listaId undefined...');
    const resultado5 = await buscarPacientesSemIdBackendService({ listaId: undefined });
    console.log(`✅ Resultado: ${resultado5.count} pacientes encontrados`);
    console.log(`   Filtros aplicados: ${resultado5.filtros.totalIDsFiltrados} IDs filtrados\n`);

    console.log('=== TODOS OS TESTES CONCLUÍDOS COM SUCESSO ===');

  } catch (error) {
    console.error('❌ Erro durante os testes:', error.message);
    await escreverLog(`Erro no teste: ${error.message}`);
  }
};

// Executar o teste
testarBuscaPacientesID(); 
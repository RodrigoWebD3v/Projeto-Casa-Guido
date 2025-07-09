const mongoose = require('mongoose');
const { escreverLog } = require('../../utils/escreverLog');
const AppError = require('../../handlerException/appError');
const { buscarPacientesSemIdBackendRepository } = require('../../repository/pacienteRepository');

const buscarPacientesSemIdBackendService = async (body) => {
  try {
    await escreverLog("Iniciando busca de pacientes sem idBackend");
    
    // Extrair listaId do body e garantir que seja um array
    const { listaId } = body || {};
    const listaIdArray = Array.isArray(listaId) ? listaId : [];

    // Converter para ObjectId
    let listaIdObject = [];
    if (Array.isArray(listaIdArray)) {
      listaIdObject = listaIdArray
        .map(id => {
          try {
            return new mongoose.Types.ObjectId(id);
          } catch (e) {
            return null;
          }
        })
        .filter(id => id !== null);
    }

    console.log("Lista ID", listaIdObject);
    await escreverLog(`Lista de IDs recebida: ${listaIdObject.length} IDs`);
    
    let pacientes;
    
    // Se a lista de IDs estiver vazia, retornar todos os pacientes
    if (listaIdObject.length === 0) {
      await escreverLog("Lista de IDs vazia - retornando todos os pacientes");
      pacientes = await buscarPacientesSemIdBackendRepository();
    } else {
      // Se há IDs na lista, buscar pacientes cujo ID NÃO está na lista
      await escreverLog(`Buscando pacientes com IDs diferentes de: ${listaIdObject.join(', ')}`);
      
      // Criar filtro para excluir IDs da lista
      const filtroID = {
        $and: [
          // Manter o filtro original (sem idBackend)
          {
            $or: [
              { idBackend: { $exists: false } },
              { idBackend: null },
              { idBackend: '' }
            ]
          },
          // Excluir IDs da lista - usar $nin para IDs que NÃO estão na lista
          { _id: { $nin: listaIdObject } }
        ]
      };
      
      pacientes = await buscarPacientesSemIdBackendRepository(filtroID);
    }
    
    if (!pacientes) {
      throw new AppError("Erro ao buscar pacientes", 500);
    }
    
    await escreverLog(`Pacientes encontrados: ${pacientes.length}`);
    
    return {
      data: pacientes,
    };

  } catch (error) {
    await escreverLog(`Erro no serviço buscarPacientesSemIdBackendService: ${error.message}`);
    throw error;
  }
};

module.exports = { buscarPacientesSemIdBackendService };

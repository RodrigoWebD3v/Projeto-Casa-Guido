const { PrismaClient } = require('@prisma/client');

const prisma = new PrismaClient();

const criarHistoricoSaudeRepository = async (dtoHistoricoSaude) => {
    console.log("Historico de saude",dtoHistoricoSaude);
    return await prisma.historicoSaude.create({
        data: {
            ...dtoHistoricoSaude
        },
    });
}

module.exports = { criarHistoricoSaudeRepository };
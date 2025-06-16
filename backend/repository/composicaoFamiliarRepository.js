const { PrismaClient } = require('@prisma/client');

const prisma = new PrismaClient();

const criarComposicaoFamiliarRepository = async (dtoComposicaoFamiliar) => {
    return await prisma.composicaoFamiliar.create({
        data: {
            ...dtoComposicaoFamiliar
        },
    });
}

module.exports = { criarComposicaoFamiliarRepository };
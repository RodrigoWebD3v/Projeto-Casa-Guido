const { PrismaClient } = require('@prisma/client');

const prisma = new PrismaClient();

const criarSituacaoHabitacionalRepository = async (dtoSituacaoHabitacional) => {
    return await prisma.situacaoHabitacional.create({
        data: {
            ...dtoSituacaoHabitacional
        },
    });
}

module.exports = { criarSituacaoHabitacionalRepository };
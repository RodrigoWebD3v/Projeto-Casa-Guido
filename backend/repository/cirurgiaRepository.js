const { PrismaClient } = require('@prisma/client');

const prisma = new PrismaClient();

const criarCirurgiaRepository = async (dtoCirurgia) => {
    return await prisma.cirurgia.create({
        data: {
            ...dtoCirurgia
        },
    });
}

module.exports = { criarCirurgiaRepository };
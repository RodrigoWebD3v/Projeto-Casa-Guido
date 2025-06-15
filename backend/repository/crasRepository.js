const { PrismaClient } = require('@prisma/client');

const prisma = new PrismaClient();

const criarCrasRepository = async (dtoCras) => {
    return await prisma.cras.create({
        data: {
            ...dtoCras
        },
    });
}

module.exports = { criarCrasRepository };
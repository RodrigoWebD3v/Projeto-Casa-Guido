const { PrismaClient } = require('@prisma/client');

const prisma = new PrismaClient();

const criarQuimioterapiaRepository = async (dtoQuimioterapia) => {
    return await prisma.quimioterapia.create({
        data: {
            ...dtoQuimioterapia
        },
    });
}

module.exports = { criarQuimioterapiaRepository };
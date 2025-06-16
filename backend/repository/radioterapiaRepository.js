const { PrismaClient } = require('@prisma/client');

const prisma = new PrismaClient();

const criarRadioterapiaRepository = async (dtoRadioterapia) => {
    return await prisma.radioTerapia.create({
        data: {
            ...dtoRadioterapia
        },
    });
}

module.exports = { criarRadioterapiaRepository };
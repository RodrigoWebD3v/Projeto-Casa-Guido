const { PrismaClient } = require('@prisma/client');

const prisma = new PrismaClient();

const criarUbsRepository = async (dtoUbs) => {
    return await prisma.ubs.create({
        data: {
            ...dtoUbs
        },
    });
}

module.exports = { criarUbsRepository };
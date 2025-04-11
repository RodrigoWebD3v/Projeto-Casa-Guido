const { PrismaClient } = require('@prisma/client');

const prisma = new PrismaClient();

const criarEnderecoRepository = async (dtoEndereco) => {
    return await prisma.endereco.create({
        data: {
            ...dtoEndereco
        },
    });
}

module.exports = { criarEnderecoRepository };
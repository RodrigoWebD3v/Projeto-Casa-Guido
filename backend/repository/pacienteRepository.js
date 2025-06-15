const { PrismaClient } = require('@prisma/client');

const prisma = new PrismaClient();

const criarPacineteRepository = async (dtoPaciente) => {
    return await prisma.paciente.create({
        data: {
         ...dtoPaciente
        },
      });
}

const buscarUsuarioPorId = async (id) => {
    return await prisma.user.findUnique({ where: { id } });
}

module.exports = {criarPacineteRepository, buscarUsuarioPorId };
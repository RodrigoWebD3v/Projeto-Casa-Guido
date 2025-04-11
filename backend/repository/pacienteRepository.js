const { PrismaClient } = require('@prisma/client');

const prisma = new PrismaClient();

const criarPaciente = async (dtoPaciente) => {
    return await prisma.user.create({
        data: {
          name,
          email,
          password: hashedPassword,
        },
      });
}

const buscarPacientePorNome = async (email) => {
    return await prisma.user.findUnique({ where: { email } });
}

const buscarUsuarioPorId = async (id) => {
    return await prisma.user.findUnique({ where: { id } });
}

module.exports = { criarUsuario, buscarUsuarioPorEmail, buscarUsuarioPorId };
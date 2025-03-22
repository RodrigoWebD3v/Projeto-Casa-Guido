const { PrismaClient } = require('@prisma/client');

const prisma = new PrismaClient();

const criarUsuario = async (name, email, hashedPassword) => {
    return await prisma.user.create({
        data: {
          name,
          email,
          password: hashedPassword,
        },
      });
}

const buscarUsuarioPorEmail = async (email) => {
    return await prisma.user.findUnique({ where: { email } });
}

const buscarUsuarioPorId = async (id) => {
    return await prisma.user.findUnique({ where: { id } });
}

module.exports = { criarUsuario, buscarUsuarioPorEmail, buscarUsuarioPorId };
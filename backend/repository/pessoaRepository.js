const { PrismaClient } = require('@prisma/client');
const  PessoaError = require('../handlerException/criarPacienteException')

const prisma = new PrismaClient();

const criarPessoa = async (dtoPessoa) => {
    try{
        return await prisma.pessoa.create({
            data: {
                ...dtoPessoa
            },
          });
    }catch(error){
        if (error.code === 'P2002' && error.meta?.target?.includes('cpf')) {
            throw PessoaError.cpfDuplicado();
        }
        // Outros erros desconhecidos
        throw error;
    }
   
}

const buscarPessoaPorNome = async (dtoPessoa) => {
    return await prisma.pessoa.findUnique({ where:  dtoPessoa.nome  });
}

const buscarPessoaPorId = async (id) => {
    return await prisma.pessoa.findUnique({ where: dtoPessoa.id  });
}

module.exports = { criarPessoa, buscarPessoaPorNome, buscarPessoaPorId };
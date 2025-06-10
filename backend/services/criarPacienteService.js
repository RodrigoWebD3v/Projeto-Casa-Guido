const { PrismaClient } = require('@prisma/client');
const prisma = new PrismaClient();
const { pacienteDTO } = require('../dto/pacienteDTO/pacienteDTO');

const criarPacienteService = async (body) => {
    try {
        console.log( body.paciente.pessoa.endereco.localidade)
        const paciente = await prisma.$transaction(async (prisma) => {
            // 1. Cria Endereço
            const endereco = body.paciente.pessoa.endereco && await prisma.endereco.create({
                data: {
                    cep: body.paciente.pessoa.endereco.cep,
                    logradouro: body.paciente.pessoa.endereco.logradouro,
                    numero: body.paciente.pessoa.endereco.numero,
                    complemento: body.paciente.pessoa.endereco.complemento,
                    bairro: body.paciente.pessoa.endereco.bairro,
                    localidade: body.paciente.pessoa.endereco.localidade,
                    uf: body.paciente.pessoa.endereco.estado,
                    estado: body.paciente.pessoa.endereco.estado,
                    referencia: body.paciente.pessoa.endereco.referencia,
                }
            });

            // 2. Cria Pessoa
            const pessoa = await prisma.pessoa.create({
                data: {
                    nome: body.paciente.pessoa.nome,
                    telefone: body.paciente.pessoa.telefone,
                    dataNascimento: body.paciente.pessoa.dataNascimento,
                    cpf: body.paciente.pessoa.cpf,
                    identidade: body.paciente.pessoa.rg,
                    escolaridade: body.paciente.pessoa.escolaridade,
                    serie: body.paciente.pessoa.serie,
                    cartaoSus: body.paciente.pessoa.cartaoSus,
                    naturalidade: body.paciente.pessoa.naturalidade,
                    enderecoId: endereco?.id,
                    situacaoProfissional: body.paciente.pessoa.situacaoProfissional,
                    estadoCivil: body.paciente.pessoa.estadoCivil,
                    salario: body.paciente.pessoa.salario,
                    profissao: body.paciente.pessoa.profissao,
                    respPrincipal: body.paciente.pessoa.respPrincipal ? 1 : 0,
                }
            });

            // 3. Cria Responsáveis (se existirem)
            const criarResponsavel = async (responsavelBody) => {
                if (!responsavelBody) return null;
                
                const enderecoResp = responsavelBody.endereco && await prisma.endereco.create({
                    data: {
                        cep: responsavelBody.endereco.cep,
                        logradouro: responsavelBody.endereco.logradouro,
                        numero: responsavelBody.endereco.numero,
                        complemento: responsavelBody.endereco.complemento,
                        bairro: responsavelBody.endereco.bairro,
                        localidade: responsavelBody.endereco.localidade,
                        uf: responsavelBody.endereco.estado,
                        estado: responsavelBody.endereco.estado,
                    }
                });

                return await prisma.pessoa.create({
                    data: {
                        nome: responsavelBody.nome,
                        telefone: responsavelBody.telefone,
                        dataNascimento: responsavelBody.dataNascimento,
                        cpf: responsavelBody.cpf,
                        identidade: responsavelBody.rg,
                        naturalidade: responsavelBody.naturalidade,
                        escolaridade: responsavelBody.escolaridade,
                        serie: responsavelBody.serie,
                        salario: responsavelBody.renda,
                        cartaoSus: responsavelBody.cartaoSus,
                        profissao: responsavelBody.profissao,
                        enderecoId: enderecoResp?.id,
                        situacaoProfissional: responsavelBody.situacaoProfissional,
                        estadoCivil: responsavelBody.estadoCivil,
                    }
                });
            };

            const responsavel = await criarResponsavel(body.paciente.responsavel);
            const conjuge = await criarResponsavel(body.paciente.conjugeResponsavel);
            //const outroResponsavel = await criarResponsavel(body.paciente.outroResponsavel);

            // 4. Cria Paciente principal
            const pacienteCriado = await prisma.paciente.create({
                data: {
                    pessoaId: pessoa.id,
                    status: body.status !== false,
                    nomeMae: body.nomeMae,
                    nomePai: body.nomePai,
                    nomeOutro: body.nomeOutro,
                    remuneracao: body.remuneracao,
                    recebeBpc: body.bpc ? 1 : 0,
                    valorBpc: body.valorBpc,
                    aptoReceberBpc: body.aptoReceberBpc ? 1 : 0,
                    tipoEscola: body.tipoEscola,
                    escolaNome: body.escolaNome,
                    observacoes: body.observacoes || [],
                    responsavelId: responsavel?.id,
                    conjugeResponsavelId: conjuge?.id,
                   // outroResponsavelId: outroResponsavel?.id,
                }
            });

            // 5. Cria Relacionamentos
            const relacionamentos = [
                // Quimioterapias
                body.paciente.quimios?.length > 0 && prisma.quimioterapia.create({
                    data: body.paciente.quimios.map(q => ({
                        pacienteId: pacienteCriado.id,
                        dataInicio: q.dataInicio,
                        dataUltimaSessao: q.dataUltimaSessao
                    }))
                }),

                // Radioterapias
                body.paciente.radios?.length > 0 && prisma.radioterapia.create({
                    data: body.paciente.radios.map(r => ({
                        pacienteId: pacienteCriado.id,
                        dataInicio: r.dataInicio,
                        dataUltimaSessao: r.dataUltimaSessao
                    }))
                }),

                // Cirurgias
                body.paciente.cirurgias?.length > 0 && prisma.cirurgia.create({
                    data: body.paciente.cirurgias.map(c => ({
                        pacienteId: pacienteCriado.id,
                        nome: c.nome,
                        data: c.data,
                        cid: c.cid
                    }))
                }),

                // Composição Familiar
                body.paciente.composicaoFamiliar?.length > 0 && prisma.composicaoFamiliar.create({
                    data: body.composicaoFamiliar.map(f => ({
                        pacienteId: pacienteCriado.id,
                        nome: f.nome,
                        parentesco: f.parentesco,
                        data_nascimento: f.dataNascimento,
                        estudaOpc: f.estudaOpc,
                        escolaridade: f.escolaridade,
                        trabalhaOpc: f.trabalhaOpc,
                        renda: f.renda
                    }))
                }),

                // Histórico de Saúde
                body.paciente.historicoSaude && prisma.historicoSaude.create({
                    data: {
                        pacienteId: pacienteCriado.id,
                        recebe_beneficio: body.historicoSaude.recebeBeneficio ? 1 : 0,
                        beneficio_descricao: body.historicoSaude.beneficioDescricao,
                        medicamentos_uso_continuo: body.historicoSaude.medicamentosUsoContinuo,
                        local_procura_atendimento: body.historicoSaude.localProcuraAtendimento || [],
                        doencasFamilia: body.historicoSaude.doencasFamilia || []
                    }
                }),

                // Situação Habitacional
                body.paciente.situacaoHabitacional && prisma.situacaoHabitacional.create({
                    data: {
                        responsavelId: responsavel?.id,
                        comoAdquiriuCasa: body.situacaoHabitacional.comoAdquiriuCasa,
                        area: body.situacaoHabitacional.area,
                        numeroComodos: body.situacaoHabitacional.numeroComodos,
                        material: body.situacaoHabitacional.material,
                        bens: body.situacaoHabitacional.bens || [],
                        meioDeTransporte: body.situacaoHabitacional.meioDeTransporte,
                        meioDeComunicacao: body.situacaoHabitacional.meioDeComunicacao,
                        possuiBanheiros: body.situacaoHabitacional.possuiBanheiros ? 1 : 0
                    }
                }),

                // Profissional Responsável
                body.paciente.profissionalResponsavel && prisma.profissionalResponsavel.create({
                    data: {
                        pacienteId: pacienteCriado.id,
                        nome: body.profissionalResponsavel.nome,
                        crm: body.profissionalResponsavel.crm,
                        tipo: body.profissionalResponsavel.tipo
                    }
                }),

                // UBS e CRAS
                body.paciente.ubs && prisma.ubs.create({
                    data: {
                        pacienteId: pacienteCriado.id,
                        municipio: body.ubs.municipio,
                        bairro: body.ubs.bairro
                    }
                }),

                body.paciente.cras && prisma.cras.create({
                    data: {
                        pacienteId: pacienteCriado.id,
                        municipio: body.cras.municipio,
                        bairro: body.cras.bairro
                    }
                })
            ];

            await Promise.all(relacionamentos.filter(Boolean));

            // 6. Retorna o paciente completo
            return await prisma.paciente.findUnique({
                where: { id: pacienteCriado.id },
                include: {
                    pessoa: {
                        include: { endereco: true }
                    },
                    responsavel: {
                        include: { endereco: true }
                    },
                    conjugeResponsavel: {
                        include: { endereco: true }
                    },
                    outroResponsavel: {
                        include: { endereco: true }
                    },
                    quimioterapias: true,
                    radioterapias: true,
                    cirurgias: true,
                    composicaoFamiliar: true,
                    historicoSaude: true,
                    situacaoHabitacional: true,
                    profissionalResponsavel: true,
                    ubs: true,
                    cras: true
                }
            });
        });

        return pacienteDTO(paciente);

    } catch (error) {
        console.error("Erro ao criar paciente:", error);
        throw new Error(error.message || "Falha ao criar paciente");
    } finally {
        await prisma.$disconnect();
    }
};

module.exports = { criarPacienteService };
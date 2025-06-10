const pacienteDTO = (objetoPaciente) => {
    return {
        id: objetoPaciente.id,
        status: objetoPaciente.status,
        // Dados da Pessoa (aninhados)
        pessoa: {
            nome: objetoPaciente.pessoa.nome,
            telefone: objetoPaciente.pessoa.telefone,
            dataNascimento: objetoPaciente.pessoa.dataNascimento, // Mantido como string "dd/MM/yyyy"
            cpf: objetoPaciente.pessoa.cpf,
            rg: objetoPaciente.pessoa.identidade, // Mapeia "identidade" no schema para "rg" no JSON
            escolaridade: objetoPaciente.pessoa.escolaridade,
            serie: objetoPaciente.pessoa.serie,
            cartaoSus: objetoPaciente.pessoa.cartaoSus,
            naturalidade: objetoPaciente.pessoa.naturalidade,
            endereco: enderecoDTO(objetoPaciente.pessoa.endereco), // Reusa o DTO de endereço
        },
        // Dados específicos do Paciente
        nomeMae: objetoPaciente.nomeMae,
        nomePai: objetoPaciente.nomePai,
        nomeOutro: objetoPaciente.nomeOutro,
        remuneracao: objetoPaciente.remuneracao, // String (ex: "3000")
        recebeBpc: objetoPaciente.bpc === 1, // Converte 1/0 para boolean
        valorBpc: objetoPaciente.valorBpc,
        aptoReceberBpc: objetoPaciente.aptoReceberBpc === 1,
        tipoEscola: objetoPaciente.tipoEscola,
        escolaNome: objetoPaciente.escolaNome,
        // Relacionamentos aninhados
        responsavel: objetoPaciente.responsavel ? pessoaDTO(objetoPaciente.responsavel) : null,
        conjugeResponsavel: objetoPaciente.conjugeResponsavel ? pessoaDTO(objetoPaciente.conjugeResponsavel) : null,
        outroResponsavel: objetoPaciente.outroResponsavel ? pessoaDTO(objetoPaciente.outroResponsavel) : null,
        profissionalResponsavel: objetoPaciente.profissionalResponsavel ? {
            nome: objetoPaciente.profissionalResponsavel.nome,
            crm: objetoPaciente.profissionalResponsavel.crm,
        } : null,
        // Arrays de relacionamentos
        quimios: objetoPaciente.quimioterapias?.map(quimio => ({
            id: quimio.id,
            dataInicio: quimio.dataInicio,
            dataUltimaSessao: quimio.dataUltimaSessao,
        })) || [],
        radios: objetoPaciente.radioterapias?.map(radio => ({
            id: radio.id,
            dataInicio: radio.dataInicio,
            dataUltimaSessao: radio.dataUltimaSessao,
        })) || [],
        cirurgias: objetoPaciente.cirurgias?.map(cirurgia => ({
            id: cirurgia.id,
            nome: cirurgia.nome,
            data: cirurgia.data,
            cid: cirurgia.cid,
        })) || [],
        composicaoFamiliar: objetoPaciente.composicaoFamiliar?.map(familiar => ({
            id: familiar.id,
            nome: familiar.nome,
            parentesco: familiar.parentesco,
            dataNascimento: familiar.data_nascimento,
            estudaOpc: familiar.estudaOpc,
            escolaridade: familiar.escolaridade,
            trabalhaOpc: familiar.trabalhaOpc,
            renda: familiar.renda,
        })) || [],
        // Dados adicionais
        historicoSaude: objetoPaciente.historicoSaude ? {
            doencasFamilia: objetoPaciente.historicoSaude.doencasFamilia || [],
            medicamentosUsoContinuo: objetoPaciente.historicoSaude.medicamentosUsoContinuo,
            recebeBeneficio: objetoPaciente.historicoSaude.recebeBeneficio === 1,
            beneficioDescricao: objetoPaciente.historicoSaude.beneficioDescricao,
        } : null,
        situacaoHabitacional: objetoPaciente.situacaoHabitacional ? {
            comoAdquiriuCasa: objetoPaciente.situacaoHabitacional.comoAdquiriuCasa,
            area: objetoPaciente.situacaoHabitacional.area,
            numeroComodos: objetoPaciente.situacaoHabitacional.numeroComodos,
            possuiBanheiros: objetoPaciente.situacaoHabitacional.possuiBanheiros === 1,
        } : null,
        ubs: objetoPaciente.ubs ? {
            municipio: objetoPaciente.ubs.municipio,
            bairro: objetoPaciente.ubs.bairro,
        } : null,
        cras: objetoPaciente.cras ? {
            municipio: objetoPaciente.cras.municipio,
            bairro: objetoPaciente.cras.bairro,
        } : null,
    };
};

// DTO auxiliar para Pessoa (usado em responsáveis)
const pessoaDTO = (pessoa) => {
    return {
        id: pessoa.id,
        nome: pessoa.nome,
        telefone: pessoa.telefone,
        dataNascimento: pessoa.dataNascimento,
        cpf: pessoa.cpf,
        rg: pessoa.identidade,
        naturalidade: pessoa.naturalidade,
        escolaridade: pessoa.escolaridade,
        serie: pessoa.serie,
        salario: pessoa.salario,
        cartaoSus: pessoa.cartaoSus,
        profissao: pessoa.profissao,
        endereco: pessoa.endereco ? enderecoDTO(pessoa.endereco) : null,
    };
};

module.exports = {
    pacienteDTO,
    pessoaDTO, // Exporta para reuso
};
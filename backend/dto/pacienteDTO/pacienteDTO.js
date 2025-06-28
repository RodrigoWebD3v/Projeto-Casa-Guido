const pacienteDTO = (paciente, pessoaPaciente,  pessoaPai = null, pessoaMae = null, pessoaOutro = null) => {

  return {
    pessoa                  : pessoaPaciente, 
    status                  : paciente.status         || null, 
    nomeMae                 : paciente.nomeMae        || null, 
    nomePai                 : paciente.nomePai        || null, 
    nomeOutro               : paciente.nomeOutro      || null, 
    remuneracao             : paciente.remuneracao    || null, 
    recebeBpc               : paciente.recebeBpc      || null, 
    valorBpc                : paciente.valorBpc       || null,
    aptoReceberBpc          : paciente.aptoReceberBpc || null,
    tipoEscola              : paciente.tipoEscola     || null,
    escolaNome              : paciente.escolaNome     || null,
    origenInfoOng           : paciente.origenInfoOng  || null,
    observacoes             : paciente.observacoes    || null,
    responsavel             : pessoaPai,
    conjugeResponsavel      : pessoaMae,
    outroResponsavel        : pessoaOutro,
  };
};

module.exports = {
  pacienteDTO,
};

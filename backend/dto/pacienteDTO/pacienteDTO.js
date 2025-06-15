const pacienteDTO = (objetoPaciente, pessoaPaciente,  pessoaPai = null, pessoaMae = null, pessoaOutro = null) => {
  
  console.log("PacieteDTO", pessoaPaciente);
  console.log("PacieteDTO", pessoaPai);
  console.log("PacieteDTO", pessoaMae);
  console.log("PacieteDTO", pessoaOutro);

  return {
    pessoaId                : pessoaPaciente, 
    status                  : objetoPaciente.status, 
    nomeMae                 : objetoPaciente.nomeMae, 
    nomePai                 : objetoPaciente.nomePai, 
    nomeOutro               : objetoPaciente.nomeOutro, 
    remuneracao             : objetoPaciente.remuneracao, 
    recebeBpc               : objetoPaciente.recebeBpc, 
    valorBpc                : objetoPaciente.valorBpc,
    aptoReceberBpc          : objetoPaciente.aptoReceberBpc,
    tipoEscola              : objetoPaciente.tipoEscola,
    escolaNome              : objetoPaciente.escolaNome,
    origenInfoOng           : objetoPaciente.origenInfoOng,
    observacoes             : objetoPaciente.observacoes,
    responsavelId           : pessoaPai,
    conjugeResponsavelId    : pessoaMae,
    outroResponsavelId      : pessoaOutro,
  };
};

module.exports = {
  pacienteDTO,
};

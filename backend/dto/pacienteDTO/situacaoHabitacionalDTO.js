const situacaoHabitacionalDTO = (objetoSituacaoHabitacional, pacienteId) => {
    return {
        pacienteId         : pacienteId,
        comoAdquiriuCasa   : objetoSituacaoHabitacional.comoAdquiriuCasa,  
        area               : objetoSituacaoHabitacional.area,
        numeroComodos      : objetoSituacaoHabitacional.numeroComodos,
        material           : objetoSituacaoHabitacional.material,
        bens               : objetoSituacaoHabitacional.bens,
        meioDeTransporte   : objetoSituacaoHabitacional.meioDeTransporte,
        meioDeComunicacao  : objetoSituacaoHabitacional.meioDeComunicacao,
        possuiBanheiros    : objetoSituacaoHabitacional.possuiBanheiros,
        dentroDeCasa       : objetoSituacaoHabitacional.dentroDeCasa,
    }
};

module.exports = {
    situacaoHabitacionalDTO,
};
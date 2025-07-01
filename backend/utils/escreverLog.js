const escreverLog =  (mensagem) => {
    const data = new Date();
    const hora = data.getHours();
    const minuto = data.getMinutes();
    const segundo = data.getSeconds();
    const milissegundo = data.getMilliseconds();
    const log = `${hora}:${minuto}:${segundo}.${milissegundo} - ${mensagem}`;
    console.log(log);
    return log;
}

module.exports = { escreverLog };
// errors/PessoaError.js
const AppError = require("../handlerException/appError");

class PessoaError extends AppError {
    constructor(message, statusCode = 400) {
        super(message, statusCode);
    }

    static cpfDuplicado() {
        return new PessoaError("CPF já está cadastrado no sistema.", 409);
    }
}

module.exports = PessoaError;

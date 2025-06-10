-- CreateTable
CREATE TABLE "User" (
    "id" TEXT NOT NULL,
    "name" TEXT NOT NULL,
    "email" TEXT NOT NULL,
    "password" TEXT NOT NULL,
    "createdAt" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT "User_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Pessoa" (
    "id" TEXT NOT NULL,
    "nome" TEXT NOT NULL,
    "contato" TEXT,
    "dataNascimento" TEXT NOT NULL,
    "cpf" TEXT,
    "identidade" TEXT,
    "naturalidade" TEXT,
    "genero" TEXT,
    "escolaridade" INTEGER,
    "serie" INTEGER,
    "estadoCivil" INTEGER,
    "situacaoProfissional" INTEGER,
    "salario" TEXT,
    "enderecoId" TEXT,
    "telefone" TEXT,
    "cartaoSus" TEXT,
    "respPrincipal" INTEGER,
    "profissao" TEXT,

    CONSTRAINT "Pessoa_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Endereco" (
    "id" TEXT NOT NULL,
    "cep" TEXT NOT NULL,
    "logradouro" TEXT NOT NULL,
    "numero" TEXT NOT NULL,
    "complemento" TEXT NOT NULL,
    "unidade" TEXT,
    "bairro" TEXT NOT NULL,
    "localidade" TEXT NOT NULL,
    "uf" TEXT NOT NULL,
    "estado" TEXT NOT NULL,
    "regiao" TEXT,
    "referencia" TEXT,
    "pais" TEXT DEFAULT 'Brasil',

    CONSTRAINT "Endereco_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Paciente" (
    "id" TEXT NOT NULL,
    "pessoaId" TEXT NOT NULL,
    "status" BOOLEAN,
    "nomeMae" TEXT,
    "nomePai" TEXT,
    "nomeOutro" TEXT,
    "remuneracao" TEXT,
    "recebeBpc" INTEGER,
    "valorBpc" TEXT,
    "aptoReceberBpc" INTEGER,
    "tipoEscola" INTEGER,
    "escolaNome" TEXT,
    "recebeRemuneracao" INTEGER,
    "diagnostico" TEXT,
    "profissionalResponsavel" TEXT,
    "origenInfoOng" TEXT,
    "observacoes" TEXT[],
    "responsavelId" TEXT,
    "conjugeResponsavelId" TEXT,
    "outroResponsavelId" TEXT,

    CONSTRAINT "Paciente_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Entrevista" (
    "id" TEXT NOT NULL,
    "pacienteId" TEXT NOT NULL,
    "entrevistadoId" TEXT,
    "cidade" TEXT,
    "data" TEXT,
    "como_soube" TEXT,
    "observacoes" TEXT,
    "entrevistador" TEXT,

    CONSTRAINT "Entrevista_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "HistoricoSaude" (
    "id" TEXT NOT NULL,
    "pacienteId" TEXT NOT NULL,
    "recebe_beneficio" INTEGER,
    "beneficio_descricao" TEXT,
    "medicamentos_uso_continuo" TEXT,
    "local_procura_atendimento" INTEGER[] DEFAULT ARRAY[]::INTEGER[],
    "doencasFamilia" INTEGER[] DEFAULT ARRAY[]::INTEGER[],

    CONSTRAINT "HistoricoSaude_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Quimioterapia" (
    "id" TEXT NOT NULL,
    "pacienteId" TEXT NOT NULL,
    "dataInicio" TEXT,
    "dataUltimaSessao" TEXT,

    CONSTRAINT "Quimioterapia_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "RadioTerapia" (
    "id" TEXT NOT NULL,
    "pacienteId" TEXT NOT NULL,
    "dataInicio" TEXT,
    "dataUltimaSessao" TEXT,

    CONSTRAINT "RadioTerapia_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Tratamento" (
    "id" TEXT NOT NULL,
    "pacienteId" TEXT NOT NULL,
    "tipo" TEXT,
    "data_inicio" TEXT,
    "data_ultima_sessao" TEXT,
    "observacoes" TEXT,
    "outros_tratamentos" TEXT,

    CONSTRAINT "Tratamento_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "ProfissionalResponsavel" (
    "id" TEXT NOT NULL,
    "pacienteId" TEXT NOT NULL,
    "tipo" TEXT,
    "nome" TEXT NOT NULL,
    "crm" TEXT,

    CONSTRAINT "ProfissionalResponsavel_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Cirurgia" (
    "id" TEXT NOT NULL,
    "pacienteId" TEXT NOT NULL,
    "nome" TEXT,
    "data" TEXT,
    "cid" TEXT,

    CONSTRAINT "Cirurgia_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "ComposicaoFamiliar" (
    "id" TEXT NOT NULL,
    "pacienteId" TEXT NOT NULL,
    "nome" TEXT,
    "parentesco" TEXT,
    "data_nascimento" TEXT,
    "estudaOpc" INTEGER,
    "escolaridade" TEXT,
    "trabalhaOpc" INTEGER,
    "renda" TEXT,
    "idade" INTEGER,

    CONSTRAINT "ComposicaoFamiliar_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "SituacaoHabitacional" (
    "id" TEXT NOT NULL,
    "responsavelId" TEXT,
    "comoAdquiriuCasa" INTEGER,
    "area" INTEGER,
    "numeroComodos" INTEGER,
    "material" INTEGER,
    "bens" INTEGER[],
    "meioDeTransporte" INTEGER,
    "meioDeComunicacao" INTEGER,
    "possuiBanheiros" INTEGER,
    "tipoMoradia" TEXT,
    "propriedade" BOOLEAN,
    "caracteristicas" TEXT,
    "eletrodomesticos" TEXT,
    "dentroDeCasa" BOOLEAN,
    "destinoLixo" TEXT,
    "tipoAgua" TEXT,
    "valorTotal" TEXT,

    CONSTRAINT "SituacaoHabitacional_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Ubs" (
    "id" TEXT NOT NULL,
    "pacienteId" TEXT,
    "municipio" TEXT,
    "bairro" TEXT,

    CONSTRAINT "Ubs_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Cras" (
    "id" TEXT NOT NULL,
    "pacienteId" TEXT,
    "municipio" TEXT,
    "bairro" TEXT,

    CONSTRAINT "Cras_pkey" PRIMARY KEY ("id")
);

-- CreateIndex
CREATE UNIQUE INDEX "User_email_key" ON "User"("email");

-- CreateIndex
CREATE UNIQUE INDEX "Paciente_pessoaId_key" ON "Paciente"("pessoaId");

-- CreateIndex
CREATE UNIQUE INDEX "Ubs_pacienteId_key" ON "Ubs"("pacienteId");

-- CreateIndex
CREATE UNIQUE INDEX "Cras_pacienteId_key" ON "Cras"("pacienteId");

-- AddForeignKey
ALTER TABLE "Pessoa" ADD CONSTRAINT "Pessoa_enderecoId_fkey" FOREIGN KEY ("enderecoId") REFERENCES "Endereco"("id") ON DELETE SET NULL ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Paciente" ADD CONSTRAINT "Paciente_pessoaId_fkey" FOREIGN KEY ("pessoaId") REFERENCES "Pessoa"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Entrevista" ADD CONSTRAINT "Entrevista_pacienteId_fkey" FOREIGN KEY ("pacienteId") REFERENCES "Paciente"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "HistoricoSaude" ADD CONSTRAINT "HistoricoSaude_pacienteId_fkey" FOREIGN KEY ("pacienteId") REFERENCES "Paciente"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Quimioterapia" ADD CONSTRAINT "Quimioterapia_pacienteId_fkey" FOREIGN KEY ("pacienteId") REFERENCES "Paciente"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "RadioTerapia" ADD CONSTRAINT "RadioTerapia_pacienteId_fkey" FOREIGN KEY ("pacienteId") REFERENCES "Paciente"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Tratamento" ADD CONSTRAINT "Tratamento_pacienteId_fkey" FOREIGN KEY ("pacienteId") REFERENCES "Paciente"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "ProfissionalResponsavel" ADD CONSTRAINT "ProfissionalResponsavel_pacienteId_fkey" FOREIGN KEY ("pacienteId") REFERENCES "Paciente"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Cirurgia" ADD CONSTRAINT "Cirurgia_pacienteId_fkey" FOREIGN KEY ("pacienteId") REFERENCES "Paciente"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "ComposicaoFamiliar" ADD CONSTRAINT "ComposicaoFamiliar_pacienteId_fkey" FOREIGN KEY ("pacienteId") REFERENCES "Paciente"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Ubs" ADD CONSTRAINT "Ubs_pacienteId_fkey" FOREIGN KEY ("pacienteId") REFERENCES "Paciente"("id") ON DELETE SET NULL ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Cras" ADD CONSTRAINT "Cras_pacienteId_fkey" FOREIGN KEY ("pacienteId") REFERENCES "Paciente"("id") ON DELETE SET NULL ON UPDATE CASCADE;

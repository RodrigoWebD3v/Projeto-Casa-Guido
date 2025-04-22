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
    "escolaridade" TEXT,
    "estadoCivil" INTEGER,
    "situacaoProfissional" INTEGER,
    "salario" TEXT,
    "enderecoId" TEXT,
    "telefone" TEXT,
    "cartaoSus" TEXT,

    CONSTRAINT "Pessoa_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Endereco" (
    "id" TEXT NOT NULL,
    "cep" TEXT NOT NULL,
    "logradouro" TEXT NOT NULL,
    "numero" TEXT NOT NULL,
    "complemento" TEXT NOT NULL,
    "unidade" TEXT NOT NULL,
    "bairro" TEXT NOT NULL,
    "estado" TEXT NOT NULL,
    "uf" TEXT NOT NULL,
    "regiao" TEXT NOT NULL,
    "localidade" TEXT NOT NULL,
    "referencia" TEXT NOT NULL,
    "pais" TEXT,

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
    "recebeRemuneracao" INTEGER,
    "remuneracao" TEXT,
    "recebeBpc" INTEGER,
    "valorBpc" TEXT,
    "diagnostico" TEXT,
    "profissionalResponsavel" TEXT,
    "escolaNome" TEXT,
    "anoEscolar" TEXT,
    "tamRoupa" TEXT,
    "tamCalcado" TEXT,
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
    "local_procura_atendimento" INTEGER[],
    "doencasFamilia" INTEGER[],

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
    "descricao" TEXT,

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
    "tipo_moradia" TEXT,
    "propriedade" BOOLEAN,
    "area" TEXT,
    "caracteristicas" TEXT,
    "eletrodomesticos" TEXT,
    "bens_imovel" TEXT,
    "meios_transporte" TEXT,
    "meios_comunicacao" TEXT,
    "possui_banheiro" BOOLEAN,
    "dentro_de_casa" BOOLEAN,
    "destino_lixo" TEXT,
    "tipo_agua" TEXT,
    "valor_total" TEXT,

    CONSTRAINT "SituacaoHabitacional_pkey" PRIMARY KEY ("id")
);

-- CreateIndex
CREATE UNIQUE INDEX "User_email_key" ON "User"("email");

-- CreateIndex
CREATE UNIQUE INDEX "Paciente_pessoaId_key" ON "Paciente"("pessoaId");

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

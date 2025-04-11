-- CreateTable
CREATE TABLE "Pessoa" (
    "id" TEXT NOT NULL,
    "nome" TEXT NOT NULL,
    "data_nascimento" TIMESTAMP(3) NOT NULL,
    "cpf" TEXT,
    "identidade" TEXT,
    "naturalidade" TEXT,
    "genero" TEXT,
    "escolaridade" TEXT,
    "contato" TEXT,
    "enderecoId" TEXT,

    CONSTRAINT "Pessoa_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Endereco" (
    "id" TEXT NOT NULL,
    "cep" TEXT NOT NULL,
    "logradouro" TEXT NOT NULL,
    "bairro" TEXT NOT NULL,
    "cidade" TEXT NOT NULL,
    "estado" CHAR(2) NOT NULL,
    "referencia" TEXT,
    "complemento" TEXT,

    CONSTRAINT "Endereco_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Paciente" (
    "id" TEXT NOT NULL,
    "pessoaId" TEXT NOT NULL,
    "cartao_sus" TEXT,
    "remuneracao" DECIMAL(65,30),
    "recebe_bpc" BOOLEAN,
    "bpc_valor" DECIMAL(65,30),
    "diagnostico" TEXT,
    "medico_responsavel" TEXT,
    "escola" TEXT,
    "tamanho_roupa" TEXT,
    "tamanho_calcado" TEXT,
    "ano_escolar" TEXT,
    "origem_info_ong" TEXT,
    "observacoes" TEXT,

    CONSTRAINT "Paciente_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Responsavel" (
    "id" TEXT NOT NULL,
    "pessoaId" TEXT NOT NULL,
    "pacienteId" TEXT NOT NULL,
    "tipo_responsavel" TEXT,
    "estado_civil" INTEGER,
    "situacao_profissional" INTEGER,
    "salario" DECIMAL(65,30),

    CONSTRAINT "Responsavel_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Conjuge" (
    "id" TEXT NOT NULL,
    "responsavelId" TEXT NOT NULL,
    "pessoaId" TEXT NOT NULL,

    CONSTRAINT "Conjuge_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "OutroResponsavel" (
    "id" TEXT NOT NULL,
    "pacienteId" TEXT NOT NULL,
    "pessoaId" TEXT NOT NULL,
    "tipo_vinculo" TEXT,

    CONSTRAINT "OutroResponsavel_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Cirurgia" (
    "id" TEXT NOT NULL,
    "pacienteId" TEXT NOT NULL,
    "data" TIMESTAMP(3),
    "descricao" TEXT,

    CONSTRAINT "Cirurgia_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Tratamento" (
    "id" TEXT NOT NULL,
    "pacienteId" TEXT NOT NULL,
    "tipo" TEXT,
    "data_inicio" TIMESTAMP(3),
    "data_ultima_sessao" TIMESTAMP(3),
    "observacoes" TEXT,

    CONSTRAINT "Tratamento_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "HistoricoSaude" (
    "id" TEXT NOT NULL,
    "pacienteId" TEXT NOT NULL,
    "recebe_beneficio" BOOLEAN,
    "beneficio_descricao" TEXT,
    "medicamentos_uso_continuo" TEXT,
    "local_procura_ajuda" TEXT,
    "doencas" JSONB,

    CONSTRAINT "HistoricoSaude_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "SituacaoHabitacional" (
    "id" TEXT NOT NULL,
    "responsavelId" TEXT NOT NULL,
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
    "valor_total" DECIMAL(65,30),

    CONSTRAINT "SituacaoHabitacional_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "ComposicaoFamiliar" (
    "id" TEXT NOT NULL,
    "pacienteId" TEXT NOT NULL,
    "nome" TEXT,
    "parentesco" TEXT,
    "data_nascimento" TIMESTAMP(3),
    "escolaridade" TEXT,
    "trabalha" BOOLEAN,
    "renda" DECIMAL(65,30),

    CONSTRAINT "ComposicaoFamiliar_pkey" PRIMARY KEY ("id")
);

-- CreateIndex
CREATE UNIQUE INDEX "Pessoa_cpf_key" ON "Pessoa"("cpf");

-- CreateIndex
CREATE UNIQUE INDEX "Paciente_pessoaId_key" ON "Paciente"("pessoaId");

-- CreateIndex
CREATE UNIQUE INDEX "Responsavel_pessoaId_key" ON "Responsavel"("pessoaId");

-- AddForeignKey
ALTER TABLE "Pessoa" ADD CONSTRAINT "Pessoa_enderecoId_fkey" FOREIGN KEY ("enderecoId") REFERENCES "Endereco"("id") ON DELETE SET NULL ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Paciente" ADD CONSTRAINT "Paciente_pessoaId_fkey" FOREIGN KEY ("pessoaId") REFERENCES "Pessoa"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Responsavel" ADD CONSTRAINT "Responsavel_pessoaId_fkey" FOREIGN KEY ("pessoaId") REFERENCES "Pessoa"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Responsavel" ADD CONSTRAINT "Responsavel_pacienteId_fkey" FOREIGN KEY ("pacienteId") REFERENCES "Paciente"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Conjuge" ADD CONSTRAINT "Conjuge_responsavelId_fkey" FOREIGN KEY ("responsavelId") REFERENCES "Responsavel"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Conjuge" ADD CONSTRAINT "Conjuge_pessoaId_fkey" FOREIGN KEY ("pessoaId") REFERENCES "Pessoa"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "OutroResponsavel" ADD CONSTRAINT "OutroResponsavel_pacienteId_fkey" FOREIGN KEY ("pacienteId") REFERENCES "Paciente"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "OutroResponsavel" ADD CONSTRAINT "OutroResponsavel_pessoaId_fkey" FOREIGN KEY ("pessoaId") REFERENCES "Pessoa"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Cirurgia" ADD CONSTRAINT "Cirurgia_pacienteId_fkey" FOREIGN KEY ("pacienteId") REFERENCES "Paciente"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Tratamento" ADD CONSTRAINT "Tratamento_pacienteId_fkey" FOREIGN KEY ("pacienteId") REFERENCES "Paciente"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "HistoricoSaude" ADD CONSTRAINT "HistoricoSaude_pacienteId_fkey" FOREIGN KEY ("pacienteId") REFERENCES "Paciente"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "SituacaoHabitacional" ADD CONSTRAINT "SituacaoHabitacional_responsavelId_fkey" FOREIGN KEY ("responsavelId") REFERENCES "Responsavel"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "ComposicaoFamiliar" ADD CONSTRAINT "ComposicaoFamiliar_pacienteId_fkey" FOREIGN KEY ("pacienteId") REFERENCES "Paciente"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

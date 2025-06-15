-- CreateTable
CREATE TABLE "users" (
    "id" TEXT NOT NULL,
    "name" TEXT NOT NULL,
    "email" TEXT NOT NULL,
    "password" TEXT NOT NULL,
    "createdAt" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT "users_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "pessoas" (
    "id" TEXT NOT NULL,
    "nome" TEXT NOT NULL,
    "contato" TEXT,
    "data_nascimento" TEXT NOT NULL,
    "cpf" TEXT,
    "identidade" TEXT,
    "naturalidade" TEXT,
    "genero" TEXT,
    "escolaridade" INTEGER,
    "serie" INTEGER,
    "estado_civil" INTEGER,
    "situacao_profissional" INTEGER,
    "salario" TEXT,
    "endereco_id" TEXT,
    "telefone" TEXT,
    "cartao_sus" TEXT,
    "profissao" TEXT,

    CONSTRAINT "pessoas_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "enderecos" (
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

    CONSTRAINT "enderecos_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "pacientes" (
    "id" TEXT NOT NULL,
    "pessoa_id" TEXT NOT NULL,
    "status" BOOLEAN,
    "nome_mae" TEXT,
    "nome_pai" TEXT,
    "nome_outro" TEXT,
    "remuneracao" TEXT,
    "recebe_bpc" INTEGER,
    "valor_bpc" TEXT,
    "apto_receber_bpc" INTEGER,
    "tipo_escola" INTEGER,
    "escola_nome" TEXT,
    "observacoes" TEXT[],
    "responsavel_id" TEXT,
    "conjugeResponsavel_id" TEXT,
    "outroResponsavel_id" TEXT,

    CONSTRAINT "pacientes_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "historicos_saude" (
    "id" TEXT NOT NULL,
    "paciente_id" TEXT NOT NULL,
    "doencas_familia" INTEGER[] DEFAULT ARRAY[]::INTEGER[],

    CONSTRAINT "historicos_saude_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "quimioterapias" (
    "id" TEXT NOT NULL,
    "paciente_id" TEXT NOT NULL,
    "data_inicio" TEXT,
    "data_ultimaSessao" TEXT,

    CONSTRAINT "quimioterapias_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "radioterapias" (
    "id" TEXT NOT NULL,
    "paciente_id" TEXT NOT NULL,
    "data_inicio" TEXT,
    "data_ultimaSessao" TEXT,

    CONSTRAINT "radioterapias_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "profissionais_responsaveis" (
    "id" TEXT NOT NULL,
    "paciente_id" TEXT NOT NULL,
    "tipo" TEXT,
    "nome" TEXT NOT NULL,
    "crm" TEXT,

    CONSTRAINT "profissionais_responsaveis_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "cirurgias" (
    "id" TEXT NOT NULL,
    "paciente_id" TEXT NOT NULL,
    "nome" TEXT,
    "data" TEXT,
    "cid" TEXT,

    CONSTRAINT "cirurgias_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "composicoes_familiar" (
    "id" TEXT NOT NULL,
    "paciente_id" TEXT NOT NULL,
    "nome" TEXT,
    "parentesco" TEXT,
    "data_nascimento" TEXT,
    "estudaOpc" INTEGER,
    "escolaridade" TEXT,
    "trabalhaOpc" INTEGER,
    "renda" TEXT,
    "idade" INTEGER,

    CONSTRAINT "composicoes_familiar_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "situacoes_habitacional" (
    "id" TEXT NOT NULL,
    "como_adquiriu_casa" INTEGER,
    "area" INTEGER,
    "numero_comodos" INTEGER,
    "material" INTEGER,
    "bens" INTEGER[],
    "meio_de_transporte" INTEGER,
    "meio_de_comunicacao" INTEGER,
    "possui_banheiros" INTEGER,
    "dentro_de_casa" BOOLEAN,

    CONSTRAINT "situacoes_habitacional_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "ubs" (
    "id" TEXT NOT NULL,
    "paciente_id" TEXT,
    "municipio" TEXT,
    "bairro" TEXT,

    CONSTRAINT "ubs_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "cras" (
    "id" TEXT NOT NULL,
    "paciente_id" TEXT,
    "municipio" TEXT,
    "bairro" TEXT,

    CONSTRAINT "cras_pkey" PRIMARY KEY ("id")
);

-- CreateIndex
CREATE UNIQUE INDEX "users_email_key" ON "users"("email");

-- CreateIndex
CREATE UNIQUE INDEX "pacientes_pessoa_id_key" ON "pacientes"("pessoa_id");

-- CreateIndex
CREATE UNIQUE INDEX "ubs_paciente_id_key" ON "ubs"("paciente_id");

-- CreateIndex
CREATE UNIQUE INDEX "cras_paciente_id_key" ON "cras"("paciente_id");

-- AddForeignKey
ALTER TABLE "pessoas" ADD CONSTRAINT "pessoas_endereco_id_fkey" FOREIGN KEY ("endereco_id") REFERENCES "enderecos"("id") ON DELETE SET NULL ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "pacientes" ADD CONSTRAINT "pacientes_pessoa_id_fkey" FOREIGN KEY ("pessoa_id") REFERENCES "pessoas"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "historicos_saude" ADD CONSTRAINT "historicos_saude_paciente_id_fkey" FOREIGN KEY ("paciente_id") REFERENCES "pacientes"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "quimioterapias" ADD CONSTRAINT "quimioterapias_paciente_id_fkey" FOREIGN KEY ("paciente_id") REFERENCES "pacientes"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "radioterapias" ADD CONSTRAINT "radioterapias_paciente_id_fkey" FOREIGN KEY ("paciente_id") REFERENCES "pacientes"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "profissionais_responsaveis" ADD CONSTRAINT "profissionais_responsaveis_paciente_id_fkey" FOREIGN KEY ("paciente_id") REFERENCES "pacientes"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "cirurgias" ADD CONSTRAINT "cirurgias_paciente_id_fkey" FOREIGN KEY ("paciente_id") REFERENCES "pacientes"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "composicoes_familiar" ADD CONSTRAINT "composicoes_familiar_paciente_id_fkey" FOREIGN KEY ("paciente_id") REFERENCES "pacientes"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "ubs" ADD CONSTRAINT "ubs_paciente_id_fkey" FOREIGN KEY ("paciente_id") REFERENCES "pacientes"("id") ON DELETE SET NULL ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "cras" ADD CONSTRAINT "cras_paciente_id_fkey" FOREIGN KEY ("paciente_id") REFERENCES "pacientes"("id") ON DELETE SET NULL ON UPDATE CASCADE;

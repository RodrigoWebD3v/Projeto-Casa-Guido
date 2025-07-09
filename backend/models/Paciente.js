const mongoose = require('mongoose');

// Subschema de Endereço
const EnderecoSchema = new mongoose.Schema({
  cep: String,
  logradouro: String,
  numero: String,
  complemento: String,
  unidade: String,
  bairro: String,
  localidade: String,
  uf: String,
  estado: String,
  regiao: String,
  referencia: String,
  pais: { type: String, default: 'Brasil' }
}, { _id: false });

// Subschema de Pessoa (obrigatória - para o paciente)
const PessoaSchema = new mongoose.Schema({
  nome: { type: String, required: true },
  telefone: String,
  dataNascimento: String,
  cpf: { type: String, unique: true, sparse: true },
  rg: { type: String, unique: true, sparse: true },
  naturalidade: String,
  genero: String,
  escolaridade: Number,
  serie: Number,
  estadoCivil: Number,
  situacaoProfissional: Number,
  salario: String,
  cartaoSus: String,
  respPrincipal: Number,
  profissao: String,
  endereco: EnderecoSchema
}, { _id: false });

// Subschema de Pessoa Opcional (para responsáveis)
const PessoaOpcionalSchema = new mongoose.Schema({
  nome: String, // Não é required
  telefone: String,
  dataNascimento: String,
  cpf: String, // Removido unique e sparse para responsáveis
  rg: String, // Removido unique e sparse para responsáveis
  naturalidade: String,
  genero: String,
  escolaridade: Number,
  serie: Number,
  estadoCivil: Number,
  situacaoProfissional: Number,
  salario: String,
  cartaoSus: String,
  respPrincipal: Number,
  profissao: String,
  endereco: EnderecoSchema
}, { _id: false });

// Subschema de Cirurgia
const CirurgiaSchema = new mongoose.Schema({
  nome: { type: String, required: true },
  data: { type: String, required: true },
  cid: { type: String },
  observacoes: { type: String }
}, { _id: false });

// Subschema de Composição Familiar
const ComposicaoFamiliarSchema = new mongoose.Schema({
  nome: String,
  parentesco: String,
  dataNascimento: String,
  estudaOpcional: Number,
  serie: String,
  trabalhaOpcional: Number,
  renda: String
}, { _id: false });

// Subschema de Histórico de Saúde
const HistoricoSaudeSchema = new mongoose.Schema({
  doencasFamilia: [Number],
  medicamentosUsoContinuo: String,
  localProcuraAtendimento: [Number],
  recebeBeneficio: Number,
  beneficioDescricao: String
}, { _id: false });

// Subschema de Situação Habitacional
const SituacaoHabitacionalSchema = new mongoose.Schema({
  comoAdquiriuCasa: Number,
  area: Number,
  numeroComodos: Number,
  material: Number,
  bens: [Number],
  meioDeTransporte: Number,
  meioDeComunicao: Number,
  possuiBanheiros: Number
}, { _id: false });

// Subschema de Quimioterapia
const QuimioterapiaSchema = new mongoose.Schema({
  dataInicio: { type: String, required: true },
  dataUltimaSessao: { type: String },
  observacoes: { type: String }
}, { _id: false });

// Subschema de Radioterapia
const RadioTerapiaSchema = new mongoose.Schema({
  dataInicio: { type: String, required: true },
  dataUltimaSessao: { type: String },
  observacoes: { type: String }
}, { _id: false });

// Subschema de UBS
const UbsSchema = new mongoose.Schema({
  municipio: String,
  bairro: String
}, { _id: false });

// Subschema de CRAS
const CrasSchema = new mongoose.Schema({
  municipio: String,
  bairro: String
}, { _id: false });

// Subschema de Profissional Responsável
const ProfissionalResponsavelSchema = new mongoose.Schema({
  nome: String,
  crm: String
}, { _id: false });

// Schema principal do Paciente
const PacienteSchema = new mongoose.Schema({
  // Dados da pessoa principal (paciente) - OBRIGATÓRIO
  pessoa: { type: PessoaSchema, required: true },
  
  // Dados básicos do paciente
  status: Boolean,
  nomeMae: String,
  nomePai: String,
  nomeOutro: String,
  recebeRemuneracao: Number,
  remuneracao: String,
  recebeBpc: Number,
  valorBpc: String,
  diagnostico: String,
  profissionalResponsavel: ProfissionalResponsavelSchema,
  escolaNome: String,
  anoEscolar: String,
  tamRoupa: String,
  tamCalcado: String,
  origenInfoOng: String,
  idBackend: String,
  alterado: { type: Boolean, default: false },
  observacoes: [String],
  
  // Responsáveis (OPCIONAIS) - usando PessoaOpcionalSchema
  responsavel: PessoaOpcionalSchema,
  conjugeResponsavel: PessoaOpcionalSchema,
  outroResponsavel: PessoaOpcionalSchema,
  
  // UBS e CRAS (agora como subschemas)
  ubs: UbsSchema,
  cras: CrasSchema,
  
  // Histórico médico (agora como subschemas)
  cirurgias: [CirurgiaSchema],
  quimioterapias: [QuimioterapiaSchema],
  radioterapias: [RadioTerapiaSchema],
  historicoSaude: HistoricoSaudeSchema,
  
  // Dados socioeconômicos (agora como subschemas)
  composicaoFamiliar: [ComposicaoFamiliarSchema],
  situacaoHabitacional: SituacaoHabitacionalSchema
  
}, { timestamps: true });

module.exports = mongoose.model('Paciente', PacienteSchema); 
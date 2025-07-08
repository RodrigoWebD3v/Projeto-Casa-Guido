# Refatoração das Models - Subschemas Embutidos

## 📋 Resumo das Mudanças

Refatoramos todas as models para serem subschemas embutidos da model principal `Paciente`. Isso significa que agora, quando você buscar um paciente, todos os dados relacionados virão embutidos no documento, eliminando a necessidade de fazer múltiplas consultas ou usar `.populate()`.

## 🔄 Antes vs Depois

### ❌ Antes (Referências)
```javascript
// Model Paciente com referências
const PacienteSchema = new mongoose.Schema({
  pessoa: { type: mongoose.Schema.Types.ObjectId, ref: 'Pessoa', required: true },
  responsavel: { type: mongoose.Schema.Types.ObjectId, ref: 'Pessoa' },
  cirurgias: [{ type: mongoose.Schema.Types.ObjectId, ref: 'Cirurgia' }],
  // ... outras referências
});

// Para buscar dados completos, precisava:
const paciente = await Paciente.findById(id)
  .populate('pessoa')
  .populate('responsavel')
  .populate('cirurgias')
  .populate('composicaoFamiliar')
  // ... muitas outras populações
```

### ✅ Depois (Subschemas Embutidos)
```javascript
// Model Paciente com subschemas embutidos
const PacienteSchema = new mongoose.Schema({
  pessoa: { type: PessoaSchema, required: true },
  responsavel: PessoaSchema,
  cirurgias: [CirurgiaSchema],
  // ... todos os dados embutidos
});

// Para buscar dados completos, apenas:
const paciente = await Paciente.findById(id);
// Todos os dados já vêm embutidos!
```

## 🏗️ Nova Estrutura

### Subschemas Criados:
1. **EnderecoSchema** - Dados de endereço
2. **PessoaSchema** - Dados de pessoa (paciente, responsáveis)
3. **CirurgiaSchema** - Dados de cirurgias
4. **ComposicaoFamiliarSchema** - Dados da composição familiar
5. **HistoricoSaudeSchema** - Histórico de saúde
6. **SituacaoHabitacionalSchema** - Situação habitacional
7. **QuimioterapiaSchema** - Dados de quimioterapia
8. **RadioTerapiaSchema** - Dados de radioterapia
9. **UbsSchema** - Dados da UBS
10. **CrasSchema** - Dados do CRAS
11. **ProfissionalResponsavelSchema** - Dados do profissional responsável

### Campos da Model Paciente:
```javascript
{
  // Dados da pessoa principal (paciente)
  pessoa: PessoaSchema,
  
  // Dados básicos do paciente
  status: Boolean,
  nomeMae: String,
  nomePai: String,
  // ... outros campos básicos
  
  // Responsáveis (agora como subschemas)
  responsavel: PessoaSchema,
  conjugeResponsavel: PessoaSchema,
  outroResponsavel: PessoaSchema,
  
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
}
```

## 🚀 Benefícios

### 1. **Performance**
- ✅ Uma única consulta ao banco
- ✅ Sem necessidade de `.populate()`
- ✅ Dados sempre disponíveis

### 2. **Simplicidade**
- ✅ Código mais limpo
- ✅ Menos complexidade nas consultas
- ✅ Dados sempre completos

### 3. **Consistência**
- ✅ Dados sempre sincronizados
- ✅ Sem problemas de referências quebradas
- ✅ Transações atômicas

## 📁 Arquivos Modificados

### 1. **Model Principal**
- `backend/models/Paciente.js` - Refatorado com todos os subschemas

### 2. **Repositório**
- `backend/repository/pacienteRepository.js` - Removidas referências

### 3. **Serviços**
- `backend/services/buscarPacientesSemIdBackendService.js` - Simplificado

### 4. **Scripts**
- `backend/scripts/migrateToEmbedded.js` - Script de migração
- `backend/test-embedded-structure.js` - Script de teste

## 🔧 Como Usar

### 1. **Executar Migração** (se houver dados existentes)
```bash
cd backend
node scripts/migrateToEmbedded.js
```

### 2. **Testar Nova Estrutura**
```bash
cd backend
node test-embedded-structure.js
```

### 3. **Usar na API**
```javascript
// Agora é muito simples!
const pacientes = await buscarPacientesSemIdBackendService();
// Todos os dados já vêm embutidos!
```

## 📊 Exemplo de Resposta da API

```json
{
  "success": true,
  "data": [
    {
      "_id": "507f1f77bcf86cd799439011",
      "pessoa": {
        "nome": "João Silva",
        "cpf": "123.456.789-00",
        "telefone": "(11) 99999-9999",
        "endereco": {
          "logradouro": "Rua das Flores",
          "numero": "123",
          "bairro": "Centro"
        }
      },
      "responsavel": {
        "nome": "Maria Silva",
        "cpf": "987.654.321-00"
      },
      "cirurgias": [
        {
          "nome": "Apendicectomia",
          "data": "2023-01-15",
          "cid": "K35.9"
        }
      ],
      "composicaoFamiliar": [
        {
          "nome": "Pedro Silva",
          "parentesco": "Irmão",
          "dataNascimento": "2010-05-20"
        }
      ],
      "historicoSaude": {
        "medicamentosUsoContinuo": "Dipirona",
        "recebeBeneficio": 1
      }
    }
  ],
  "count": 1
}
```

## ⚠️ Considerações Importantes

### 1. **Tamanho do Documento**
- Os documentos agora são maiores
- Considere o limite de 16MB do MongoDB por documento

### 2. **Atualizações**
- Para atualizar dados embutidos, use operadores de array do MongoDB
- Exemplo: `$push` para adicionar cirurgias

### 3. **Índices**
- Considere criar índices nos campos mais consultados
- Exemplo: `pessoa.cpf`, `pessoa.nome`

## 🎯 Próximos Passos

1. ✅ Executar migração dos dados existentes
2. ✅ Testar nova estrutura
3. ✅ Atualizar frontend para usar nova estrutura
4. ✅ Remover models antigas (opcional)
5. ✅ Criar índices otimizados

## 📞 Suporte

Se encontrar problemas durante a migração ou uso da nova estrutura, verifique:
1. Logs do script de migração
2. Estrutura dos dados no MongoDB
3. Validação dos subschemas 
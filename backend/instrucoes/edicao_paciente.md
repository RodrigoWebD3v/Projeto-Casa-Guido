# Funcionalidade de Edição de Paciente

## Visão Geral
Esta funcionalidade permite editar todos os dados de um paciente existente, incluindo informações pessoais, endereço, responsáveis, histórico de saúde, situação habitacional, cirurgias, quimioterapias e radioterapias.

## Endpoint
```
PUT /pacientes/:id
```

## Parâmetros
- `id` (path parameter): ID do paciente a ser editado

## Body da Requisição
O body deve seguir a mesma estrutura da criação de paciente, mas com os dados atualizados:

```json
{
  "pessoa": {
    "nome": "Nome Atualizado",
    "cpf": "12345678901",
    "dataNascimento": "1990-01-01",
    "sexo": "M",
    "endereco": {
      "logradouro": "Rua Atualizada",
      "numero": "123",
      "bairro": "Bairro Atualizado",
      "cidade": "Cidade Atualizada",
      "estado": "SP",
      "cep": "12345-678"
    }
  },
  "responsavel": {
    "nome": "Nome do Responsável",
    "cpf": "98765432109",
    "dataNascimento": "1960-01-01",
    "sexo": "M"
  },
  "historicoSaude": {
    "doencas": ["Doença 1", "Doença 2"],
    "medicamentos": ["Medicamento 1"],
    "alergias": ["Alergia 1"]
  },
  "situacaoHabitacional": {
    "tipoMoradia": "Casa",
    "condicoes": "Boa"
  },
  "cirurgias": [
    {
      "id": "cirurgia_existente_id", // Para editar cirurgia existente
      "tipo": "Cirurgia Atualizada",
      "data": "2023-01-01",
      "hospital": "Hospital Atualizado"
    },
    {
      "tipo": "Nova Cirurgia", // Para adicionar nova cirurgia
      "data": "2023-02-01",
      "hospital": "Novo Hospital"
    }
  ],
  "quimios": [
    {
      "id": "quimio_existente_id", // Para editar quimioterapia existente
      "tipo": "Quimioterapia Atualizada",
      "dataInicio": "2023-01-01",
      "dataFim": "2023-06-01"
    }
  ],
  "radios": [
    {
      "id": "radio_existente_id", // Para editar radioterapia existente
      "tipo": "Radioterapia Atualizada",
      "dataInicio": "2023-01-01",
      "dataFim": "2023-03-01"
    }
  ]
}
```

## Comportamento

### Dados Obrigatórios
- `pessoa.nome`: Nome da pessoa paciente é obrigatório

### Atualização de Dados
1. **Pessoa Paciente**: Sempre atualizada com os novos dados
2. **Responsáveis**: 
   - Se existir responsável anterior, atualiza os dados
   - Se não existir e novos dados forem fornecidos, cria novo responsável
3. **Endereço**: Atualizado automaticamente junto com a pessoa
4. **Histórico de Saúde**: 
   - Se existir, atualiza os dados
   - Se não existir e novos dados forem fornecidos, cria novo registro
5. **Situação Habitacional**: 
   - Se existir, atualiza os dados
   - Se não existir e novos dados forem fornecidos, cria novo registro
6. **Cirurgias/Quimioterapias/Radioterapias**:
   - Se o item tem `id`, atualiza o registro existente
   - Se o item não tem `id`, cria novo registro

## Resposta de Sucesso
```json
{
  "id": "paciente_id",
  "pessoa": {
    "id": "pessoa_id",
    "nome": "Nome Atualizado",
    "cpf": "12345678901",
    "dataNascimento": "1990-01-01T00:00:00.000Z",
    "sexo": "M",
    "endereco": "endereco_id"
  },
  "responsavel": "responsavel_id",
  "conjugeResponsavel": "conjuge_id",
  "outroResponsavel": "outro_id",
  "createdAt": "2023-01-01T00:00:00.000Z",
  "updatedAt": "2023-01-01T00:00:00.000Z"
}
```

## Códigos de Erro
- `400`: ID do paciente é obrigatório
- `404`: Paciente não encontrado
- `500`: Erro interno do servidor

## Exemplo de Uso

```javascript
const response = await fetch('/api/pacientes/64f1a2b3c4d5e6f7g8h9i0j1', {
  method: 'PUT',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify({
    pessoa: {
      nome: "João Silva Atualizado",
      cpf: "12345678901",
      dataNascimento: "1990-01-01",
      sexo: "M",
      endereco: {
        logradouro: "Rua das Flores",
        numero: "123",
        bairro: "Centro",
        cidade: "São Paulo",
        estado: "SP",
        cep: "01234-567"
      }
    }
  })
});

const pacienteAtualizado = await response.json();
```

## Arquivos Criados/Modificados

### Novos Arquivos:
- `backend/api/domainController/editarPacienteController.js`
- `backend/services/editarPacienteService.js`

### Arquivos Modificados:
- `backend/routes/pacienteRoutes.js` - Adicionada rota PUT
- `backend/services/pessoaService.js` - Adicionada função editarPessoaService
- `backend/services/enderecoService.js` - Adicionada função editarEnderecoService
- `backend/services/historicoSaudeService.js` - Adicionada função editarHistoricoSaudeService
- `backend/services/situacaoHabitacionalService.js` - Adicionada função editarSituacaoHabitacionalService
- `backend/services/cirurgiaService.js` - Adicionada função editarCirurgiaService
- `backend/services/quimioterapiaService.js` - Adicionada função editarQuimioterapiaService
- `backend/services/radioterapiaService.js` - Adicionada função editarRadioterapiaService

### Repositórios Modificados:
- `backend/repository/historicoSaudeRepository.js`
- `backend/repository/situacaoHabitacionalRepository.js`
- `backend/repository/cirurgiaRepository.js`
- `backend/repository/quimioterapiaRepository.js`
- `backend/repository/radioterapiaRepository.js` 
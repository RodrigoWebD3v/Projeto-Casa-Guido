# Refatoração da Busca de Pacientes por ID

## Resumo das Mudanças

A função `buscarPacientesSemIdBackendService` foi refatorada para aceitar um body com uma lista de IDs e retornar pacientes baseado nessa lista.

## Funcionalidade

### Comportamento da Função

1. **Lista de IDs vazia**: Retorna todos os pacientes sem idBackend
2. **Lista com IDs**: Retorna pacientes sem idBackend cujo ID NÃO está na lista fornecida
3. **Sem body**: Retorna todos os pacientes sem idBackend

### Estrutura do Body

```json
{
  "listaId": ["507f1f77bcf86cd799439011", "507f1f77bcf86cd799439012"]
}
```

### Resposta da API

```json
{
  "success": true,
  "data": [...], // Array de pacientes
  "count": 5,    // Quantidade de pacientes encontrados
  "filtros": {
    "listaId": ["507f1f77bcf86cd799439011", "507f1f77bcf86cd799439012"],
    "totalIDsFiltrados": 2
  }
}
```

## Mudanças nos Arquivos

### 1. `backend/services/pacienteService/buscarPacientesSemIdBackendService.js`

- **Antes**: Função sem parâmetros
- **Depois**: Função que recebe `body` como parâmetro
- **Novo**: Lógica para extrair `listaId` do body
- **Novo**: Filtro condicional baseado na lista de IDs
- **Novo**: Resposta estruturada com metadados

### 2. `backend/repository/pacienteRepository.js`

- **Antes**: Função com filtro fixo para `idBackend`
- **Depois**: Função que aceita filtro adicional opcional
- **Novo**: Lógica para combinar filtros quando necessário

### 3. `backend/routes/paciente.routes.js`

- **Antes**: Rota GET `/sem-idbackend`
- **Depois**: Rota POST `/sem-idbackend`
- **Novo**: Passa `req.body` para o serviço
- **Novo**: Retorna resposta estruturada do serviço

## Filtros Aplicados

### Filtro Base (Sempre Aplicado)
```javascript
{
  $or: [
    { idBackend: { $exists: false } },
    { idBackend: null },
    { idBackend: '' }
  ]
}
```

### Filtro de ID (Quando listaId não está vazia)
```javascript
{
  $or: [
    { _id: { $exists: false } },
    { _id: null },
    { _id: { $nin: listaId } }
  ]
}
```

## Casos de Uso

### 1. Buscar Todos os Pacientes
```javascript
// Body vazio ou listaId vazia
const resultado = await buscarPacientesSemIdBackendService({ listaId: [] });
```

### 2. Excluir IDs Específicos
```javascript
// Excluir pacientes com IDs específicos
const resultado = await buscarPacientesSemIdBackendService({
  listaId: ['507f1f77bcf86cd799439011', '507f1f77bcf86cd799439012']
});
```

### 3. Sem Body (Compatibilidade)
```javascript
// Funciona como antes
const resultado = await buscarPacientesSemIdBackendService();
```

## Testes

Execute o script de teste para validar a funcionalidade:

```bash
node test-buscar-pacientes-id.js
```

## Endpoint da API

**Método**: POST  
**URL**: `/pacientes/sem-idbackend`  
**Body**: 
```json
{
  "listaId": ["id1", "id2", "id3"]
}
```

## Logs

A função registra logs detalhados:
- Quantidade de IDs recebidos
- IDs sendo filtrados
- Quantidade de pacientes encontrados
- Erros durante a execução

## Compatibilidade

- ✅ Mantém compatibilidade com chamadas sem body
- ✅ Trata casos de listaId null/undefined
- ✅ Preserva filtro original (sem idBackend)
- ✅ Adiciona nova funcionalidade de filtro por ID 
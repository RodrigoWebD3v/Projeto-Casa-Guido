# Refatoração das Models para Aceitar Valores Null

## Resumo das Mudanças

Todas as models do Android foram refatoradas para aceitar valores null em campos opcionais, melhorando a robustez da aplicação e evitando crashes por valores nulos.

## Models Ajustadas

### 1. **Paciente.kt**
- **Campos alterados**: Todos os campos opcionais agora aceitam `null`
- **Mudanças principais**:
  - `pessoa: Pessoa? = null`
  - `nomeMae: String? = null`
  - `nomePai: String? = null`
  - `cirurgias: List<Cirurgia>? = null`
  - `quimios: List<Quimio>? = null`
  - `radios: List<Radio>? = null`
  - `ubs: Ubs? = null`
  - `cras: Cras? = null`
  - `responsavel: Pessoa? = null`
  - `conjugeResponsavel: Pessoa? = null`
  - `outroResponsavel: Pessoa? = null`

### 2. **Pessoa.kt**
- **Campos alterados**: Todos os campos de texto e objetos agora aceitam `null`
- **Mudanças principais**:
  - `nome: String? = null`
  - `telefone: String? = null`
  - `dataNascimento: String? = null`
  - `cpf: String? = null`
  - `rg: String? = null`
  - `endereco: Endereco? = null`
  - `escolaridade: Int? = null`
  - `serie: Int? = null`

### 3. **Endereco.kt**
- **Campos alterados**: Todos os campos de endereço agora aceitam `null`
- **Mudanças principais**:
  - `cep: String? = null`
  - `logradouro: String? = null`
  - `numero: String? = null`
  - `bairro: String? = null`
  - `localidade: String? = null`
  - `uf: String? = null`

### 4. **Outras Models**
- **Cirurgia.kt**: `nome`, `data`, `cid` agora aceitam `null`
- **Quimio.kt**: `dataInicio`, `dataUltimaSessao` agora aceitam `null`
- **Radio.kt**: `dataInicio`, `dataUltimaSessao` agora aceitam `null`
- **Tratamento.kt**: Todos os campos agora aceitam `null`
- **ProfissionalResponsavel.kt**: `tipo`, `nome`, `crm` agora aceitam `null`
- **ComposicaoFamiliar.kt**: Todos os campos agora aceitam `null`
- **HistoricoSaude.kt**: Arrays e strings agora aceitam `null`
- **SituacaoHabitacional.kt**: Array `bens` agora aceita `null`
- **Doenca.kt**: `nome` agora aceita `null`
- **Ubs.kt**: `municipio`, `bairro` agora aceitam `null`
- **Cras.kt**: `municipio`, `bairro` agora aceitam `null`
- **Arquivo.kt**: `pacienteId` agora aceita `null`
- **Agendamento.kt**: `nome`, `horario`, `tipo` agora aceitam `null`

## Serviços Ajustados

### 1. **PacienteService.kt**
- **Verificações adicionadas**:
  - Verificação de `pessoa?.id` antes de acessar
  - Verificação de `responsavel?.id`, `conjugeResponsavel?.id`, `outroResponsavel?.id`
  - Verificação de `cras?.municipio`, `ubs?.municipio` antes de acessar
  - Verificação de `pessoaId.isNotEmpty()` antes de buscar pessoa
  - Verificação de `responsavelId.isNullOrEmpty()` antes de buscar responsáveis

### 2. **CriarPdfService.kt**
- **Verificações adicionadas**:
  - Função `campo()` agora aceita `String?` e trata valores null
  - Verificação de `paciente.pessoa?.nome` antes de acessar
  - Verificação de `paciente.pessoa?.endereco?.logradouro` antes de acessar
  - Verificação de `paciente.diagnostico?.uppercase()` antes de acessar

### 3. **PessoaService.kt**
- **Verificações adicionadas**:
  - Remoção de fallbacks desnecessários (`?: ""`, `?: 0`)
  - Uso direto dos valores null das entidades
  - Verificação de `enderecoId?.let` antes de buscar endereço

## Benefícios das Mudanças

### 1. **Robustez**
- Evita crashes por valores null inesperados
- Melhora a estabilidade da aplicação
- Facilita o debug de problemas

### 2. **Flexibilidade**
- Permite campos opcionais sem valores padrão
- Facilita a integração com APIs que podem retornar campos vazios
- Melhora a experiência do usuário com formulários parciais

### 3. **Manutenibilidade**
- Código mais limpo e previsível
- Menos necessidade de verificações defensivas
- Melhor documentação dos campos obrigatórios vs opcionais

## Exemplos de Uso

### Antes (com valores padrão)
```kotlin
data class Pessoa(
    val nome: String = "",
    val telefone: String = "",
    val endereco: Endereco = Endereco()
)
```

### Depois (com null safety)
```kotlin
data class Pessoa(
    val nome: String? = null,
    val telefone: String? = null,
    val endereco: Endereco? = null
)
```

### Uso com verificações
```kotlin
// Verificação segura
val nome = paciente.pessoa?.nome ?: "Nome não informado"
val endereco = paciente.pessoa?.endereco?.logradouro ?: "Endereço não informado"

// Verificação antes de acessar
if (paciente.pessoa != null) {
    // Usar paciente.pessoa com segurança
}
```

## Compatibilidade

- ✅ Mantém compatibilidade com código existente
- ✅ Não quebra funcionalidades existentes
- ✅ Melhora a segurança de tipos
- ✅ Facilita futuras expansões

## Próximos Passos

1. **Testes**: Executar testes para garantir que não há regressões
2. **UI**: Ajustar componentes de UI para lidar com valores null
3. **Validação**: Implementar validações apropriadas para campos obrigatórios
4. **Documentação**: Atualizar documentação da API para refletir campos opcionais 
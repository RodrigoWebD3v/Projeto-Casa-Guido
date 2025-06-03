package br.com.casa_guido.screens.components.escolaridade

data class EscolaridadeUiState(
    var escolaridade: Pair<String, Int> = Pair("Selecione", 0),
    var serie: Pair<String, Int> = Pair("Selecione", 0),
    var escolaridadeOptions: List<Pair<String, Int>> = listOf(
        Pair("Selecione", 0),
        Pair("Fundamental", 1),
        Pair("Médio", 2)
    ),
    var serieMedioOptions: List<Pair<String, Int>> = listOf(
        Pair("Selecione", 0),
        Pair("1º", 1),
        Pair("2º", 2),
        Pair("3º", 3)
    ),
    var serieFundamentalOptions: List<Pair<String, Int>> = listOf(
        Pair("Selecioneº", 1),
        Pair("1º", 1),
        Pair("2º", 2),
        Pair("3º", 3),
        Pair("4º", 4),
        Pair("5º", 5),
        Pair("6º", 6),
        Pair("7º", 7),
        Pair("8º", 8),
        Pair("9º", 9)
    ),
    var default: List<Pair<String, Int>> = listOf(Pair("Selecione", 0))
)
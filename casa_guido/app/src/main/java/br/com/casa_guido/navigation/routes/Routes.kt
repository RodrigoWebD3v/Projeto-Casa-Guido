
sealed class Routes (
    val route: String
){

    data object DashBoardScreenRoute: Routes(
        route = "dashboard"
    )

    data object PacientesScreenRoute : Routes(
        route = "pacientes"
    )

    data object AgendaScreenRoute: Routes(
        route = "agenda"
    )

    data object ConfiguracoesScreenRoute: Routes(
        route = "configuracoes"
    )

    data object CadastroScreenRoute : Routes(
        route = "cadastro"
    )
}
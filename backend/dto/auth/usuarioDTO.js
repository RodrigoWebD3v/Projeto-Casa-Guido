const consultaUsuarioDTO = (objetoUsuario) => {
    return {
        id: objetoUsuario.id,
        name: objetoUsuario.name,
        email: objetoUsuario.email,
        createdAt: objetoUsuario.createdAt,
    }
}

module.exports =  { consultaUsuarioDTO };
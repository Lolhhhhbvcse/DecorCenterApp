package com.decorcenter.dao;

import com.decorcenter.model.Usuario;

/**
 * PATRÓN DE DISEÑO 2: DAO (Data Access Object)
 * ------------------------------------------------------------
 * Define el contrato de acceso a datos para la entidad Usuario,
 * desacoplando la lógica de negocio/controladores de los detalles
 * de persistencia (SQL / JDBC).
 */
public interface UsuarioDAO {

    Usuario buscarPorEmail(String email);

    Usuario login (String email, String password);

    boolean registrar(Usuario usuario);
    
    boolean existeEmail(String email);
    
    

}


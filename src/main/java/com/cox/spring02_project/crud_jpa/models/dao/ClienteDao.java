package com.cox.spring02_project.crud_jpa.models.dao;

import com.cox.spring02_project.crud_jpa.models.entity.Cliente;

import java.util.List;

public interface ClienteDao {

    // Read - Leer *findAll -> buscar todos
    public List<Cliente> findAll();

    // Update - Editar *findOne -> buscar uno solo
    public Cliente findOne(Long id);

    // Create - Crear Datos
    public void save(Cliente cliente);

    // DELETE - Eliminar
    public void delete(Long id);
}

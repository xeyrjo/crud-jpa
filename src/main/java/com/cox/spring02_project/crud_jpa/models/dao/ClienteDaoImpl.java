package com.cox.spring02_project.crud_jpa.models.dao;

import com.cox.spring02_project.crud_jpa.models.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository()
public class ClienteDaoImpl implements ClienteDao {

    @PersistenceContext
    private EntityManager entityManager;

    // Read - Leer
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Cliente> findAll() {

        return entityManager.createQuery("from Cliente").getResultList();
    }

    // Busca solo una instancia segun su atributo id
    @Transactional(readOnly = true)
    @Override
    public Cliente findOne(Long id) {

        // Obtiene el objeto de Cliente mediante su id
        return entityManager.find(Cliente.class, id);
    }

    // Create - Crear && UPDATE - Editar
    @Transactional
    @Override
    public void save(Cliente cliente) {

        // Validacion si el cliente ya esta en la bd
        if(cliente.getId() != null && cliente.getId() > 0) {
            // Ya esta en bd lo de edita - UPDATE
            entityManager.merge(cliente);
        } else {
            // No esta en la bd lo registra - CREATE
            entityManager.persist(cliente);
        }

    }

    // Delete -Eliminar
    @Transactional
    @Override
    public void delete(Long id) {

        // Obtener el cliente
        //Cliente cliente = findOne(id);

        entityManager.remove(findOne(id));
    }
}

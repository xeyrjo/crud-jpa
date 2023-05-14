package com.cox.spring02_project.crud_jpa.controllers;

import com.cox.spring02_project.crud_jpa.models.dao.ClienteDao;
import com.cox.spring02_project.crud_jpa.models.entity.Cliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("cliente")
public class Clientecontroller {

    @Autowired
    private ClienteDao clienteDao;

    // --> Read - Leer
    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clienteDao.findAll());
        return "listar";
    }

    // Formulario - Vista
    @RequestMapping(value = "/crear", method = RequestMethod.GET)
    public String crear(Model model) {

        Cliente cliente = new Cliente();

        model.addAttribute("titulo", "Formulario de Clientes");
        model.addAttribute("cliente", cliente);
        return "crear";
    }

    // Create - Crear
    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    public String guardar(@Valid  Cliente cliente, BindingResult result, Model model, SessionStatus status) {

        //Validacion
        if(result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Clientes");
            return "crear";
        }

        clienteDao.save(cliente);
        // Elimina el objeto cliente de la session
        status.setComplete();

        // redirecciona a la vista - listar
        return "redirect:listar";
    }

    // Update - Editar
    @RequestMapping(value="/crear/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model){

        Cliente cliente = null;
        // Validar el id > 0
        if(id > 0){
            cliente = clienteDao.findOne(id);
        } else {
            return "redirect:/listar";
        }

        // Pasar los datos a la vista
        model.put("cliente", cliente);
        model.put("titulo", "Editar Clientes");

        return "crear";
    }

    // Delete - Eliminar
    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id) {

        // Validamos que el id > 0
        if(id > 0) {
            clienteDao.delete(id);
        }

        return "redirect:/listar";
    }
}

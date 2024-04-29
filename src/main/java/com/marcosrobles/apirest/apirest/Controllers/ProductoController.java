package com.marcosrobles.apirest.apirest.Controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.marcosrobles.apirest.apirest.Repositories.ProductoRepository;

import com.marcosrobles.apirest.apirest.Entities.Producto;


@RestController // Indica que es un controlador rest (trnsferencia de datos)
@RequestMapping("/productos") // Indica la ruta de la API
public class ProductoController {
    //Http methods
    //GET, POST, PUT, DELETE
    //CRUD
    //Crear, Leer, Actualizar, Eliminar
    //Method get is for read data
    //Method post is for create data
    //Method put is for update data
    //Method delete is for delete data
    //method patch is for update data
    //diference between put and patch is that put update all data and patch update only the data that you want to update

    @Autowired // Inyeccion de dependencias
    private ProductoRepository productoRepository;

    @GetMapping // Indica que es un metodo get
    public List<Producto>   obtenerProductos(){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id){
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el id:" + id));
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto detallesProducto){
        Producto existeProducto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el id:" + id));
        
        existeProducto.setNombre(detallesProducto.getNombre());
        existeProducto.setPrecio(detallesProducto.getPrecio());

        return productoRepository.save(existeProducto);
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id){
        Producto existeProducto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el id:" + id));
        
        productoRepository.delete(existeProducto);
        return "El producto con el id:" + id + " ha sido eliminado";
    }

}

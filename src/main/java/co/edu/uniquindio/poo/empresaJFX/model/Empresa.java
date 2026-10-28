package co.edu.uniquindio.poo.empresaJFX.model;
import java.util.Collection;
import java.util.LinkedList;

public class Empresa {
    private String nombre;
    private Collection<Cliente> clientes;
    private Collection<Producto> productos = new LinkedList<>();

    public Empresa(String nombre) {
        this.nombre = nombre;
        clientes = new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Collection<Cliente> clientes) {
        this.clientes = clientes;
    }

    public boolean agregarCliente(Cliente cliente) {
        boolean centinela = false;
        if (!verificarCliente(cliente.getCedula())) {
            clientes.add(cliente);
            centinela = true;
        }
        return centinela;
    }
    public boolean eliminarCliente(String cedula) {
        boolean centinela = false;
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula)) {
                clientes.remove(cliente);
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean actualizarCliente(String cedula, Cliente actualizado) {
        boolean centinela = false;
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula)) {
                cliente.setCedula(actualizado.getCedula());
                cliente.setNombre(actualizado.getNombre());
                cliente.setApellido(actualizado.getApellido());
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean verificarCliente(String cedula) {
        boolean centinela = false;
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula)) {
                centinela = true;
            }
        }
        return centinela;
    }
    public Collection<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Collection<Producto> productos) {
        this.productos = productos;
    }

    public boolean agregarProducto(Producto producto) {
        boolean centinela = false;
        if (!verificarProducto(producto.getCodigo())) {
            productos.add(producto);
            centinela = true;
        }
        return centinela;
    }

    public boolean eliminarProducto(String codigo) {
        boolean centinela = false;
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                productos.remove(producto);
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean actualizarProducto(String codigo, Producto actualizado) {
        boolean centinela = false;
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                producto.setCodigo(actualizado.getCodigo());
                producto.setNombre(actualizado.getNombre());
                producto.setPrecio(actualizado.getPrecio());
                producto.setCantidad(actualizado.getCantidad());
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean verificarProducto(String codigo) {
        boolean centinela = false;
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                centinela = true;
                break;
            }
        }
        return centinela;
    }
}



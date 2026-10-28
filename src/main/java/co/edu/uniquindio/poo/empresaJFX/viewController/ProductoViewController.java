package co.edu.uniquindio.poo.empresaJFX.viewController;

import co.edu.uniquindio.poo.empresaJFX.App;
import co.edu.uniquindio.poo.empresaJFX.controller.ProductoController;
import co.edu.uniquindio.poo.empresaJFX.model.Producto;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductoViewController {

    ProductoController productoController;
    ObservableList<Producto> listProductos = FXCollections.observableArrayList();
    Producto selectedProducto;

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtCantidad;

    @FXML private Button btnAgregarProducto;
    @FXML private Button btnActualizarProducto;
    @FXML private Button btnLimpiar;
    @FXML private Button btnEliminar;

    @FXML private TableView<Producto> tblListProducto;
    @FXML private TableColumn<Producto, String> tbcCodigo;
    @FXML private TableColumn<Producto, String> tbcNombre;
    @FXML private TableColumn<Producto, Double> tbcPrecio;
    @FXML private TableColumn<Producto, Integer> tbcCantidad;

    private App app;

    @FXML
    void onAgregarProducto() {
        agregarProducto();
    }

    @FXML
    void onActualizarProducto() {
        actualizarProducto();
    }

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void onEliminar() {
        eliminarProducto();
    }

    @FXML
    void initialize() {
        // Se deja vacío porque la vista se inicializa cuando App nos asigna la instancia
    }

    private void initView() {
        initDataBinding();
        obtenerProductos();

        tblListProducto.getItems().clear();
        tblListProducto.setItems(listProductos);

        listenerSelection();
    }

    private void initDataBinding() {
        tbcCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbcPrecio.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPrecio()));
        tbcCantidad.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCantidad()));
    }

    private void obtenerProductos() {
        listProductos.addAll(productoController.obtenerListaProductos());
    }

    private void listenerSelection() {
        tblListProducto.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedProducto = newSelection;
            mostrarInformacionProducto(selectedProducto);
        });
    }

    private void mostrarInformacionProducto(Producto producto) {
        if (producto != null) {
            txtCodigo.setText(producto.getCodigo());
            txtNombre.setText(producto.getNombre());
            txtPrecio.setText(String.valueOf(producto.getPrecio()));
            txtCantidad.setText(String.valueOf(producto.getCantidad()));
        }
    }

    private void agregarProducto() {
        Producto producto = buildProducto();
        if (producto != null && productoController.crearProducto(producto)) {
            listProductos.add(producto);
            limpiarCamposProducto();
        }
    }

    private Producto buildProducto() {
        try {
            double precio = Double.parseDouble(txtPrecio.getText());
            int cantidad = Integer.parseInt(txtCantidad.getText());
            return new Producto(txtCodigo.getText(), txtNombre.getText(), precio, cantidad);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void eliminarProducto() {
        if (productoController.eliminarProducto(txtCodigo.getText())) {
            listProductos.remove(selectedProducto);
            limpiarCamposProducto();
            limpiarSeleccion();
        }
    }

    private void actualizarProducto() {
        Producto productoActualizado = buildProducto();
        if (selectedProducto != null && productoActualizado != null &&
                productoController.actualizarProducto(selectedProducto.getCodigo(), productoActualizado)) {

            int index = listProductos.indexOf(selectedProducto);
            if (index >= 0) {
                listProductos.set(index, productoActualizado);
            }

            tblListProducto.refresh();
            limpiarSeleccion();
            limpiarCamposProducto();
        }
    }

    private void limpiarSeleccion() {
        tblListProducto.getSelectionModel().clearSelection();
        limpiarCamposProducto();
    }

    private void limpiarCamposProducto() {
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtCantidad.clear();
    }

    public void setApp(App app) {
        this.app = app;
        this.productoController = new ProductoController(app.empresa);
        initView();
    }
}
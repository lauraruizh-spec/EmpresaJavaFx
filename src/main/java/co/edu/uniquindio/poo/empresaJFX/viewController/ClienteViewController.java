package co.edu.uniquindio.poo.empresaJFX.viewController;

import co.edu.uniquindio.poo.empresaJFX.App;
import co.edu.uniquindio.poo.empresaJFX.controller.ClienteController;
import co.edu.uniquindio.poo.empresaJFX.model.Cliente;
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

public class ClienteViewController {

    private ClienteController clienteController;
    private ObservableList<Cliente> listClientes = FXCollections.observableArrayList();
    private Cliente selectedCliente;
    private App app;

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    @FXML private TextField txtCedula;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;

    @FXML private Button btbAgregarCliente;
    @FXML private Button btnActualizarCliente;
    @FXML private Button btnEliminar;
    @FXML private Button btnLimpiar;

    @FXML private TableView<Cliente> tblListCliente;
    @FXML private TableColumn<Cliente, String> tbcCedula;
    @FXML private TableColumn<Cliente, String> tbcNombre;
    @FXML private TableColumn<Cliente, String> tbcApellido;

    @FXML
    void onAgregarCliente() {
        agregarCliente();
    }

    @FXML
    void onActualizarCliente() {
        actualizarCliente();
    }

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void onEliminar() {
        eliminarCliente();
    }

    @FXML
    void initialize() {

    }

    private void initView() {
        initDataBinding();
        obtenerClientes();

        tblListCliente.getItems().clear();
        tblListCliente.setItems(listClientes);

        listenerSelection();
    }

    private void initDataBinding() {
        tbcCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));
        tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbcApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
    }

    private void obtenerClientes() {
        listClientes.addAll(clienteController.obtenerListaClientes());
    }

    private void listenerSelection() {
        tblListCliente.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedCliente = newSelection;
            mostrarInformacionCliente(selectedCliente);
        });
    }

    private void mostrarInformacionCliente(Cliente cliente) {
        if (cliente != null) {
            txtCedula.setText(cliente.getCedula());
            txtNombre.setText(cliente.getNombre());
            txtApellido.setText(cliente.getApellido());
        }
    }

    private void agregarCliente() {
        Cliente cliente = buildCliente();
        if (clienteController.crearCliente(cliente)) {
            listClientes.add(cliente);
            limpiarCamposCliente();
        }
    }

    private Cliente buildCliente() {
        return new Cliente(txtCedula.getText(), txtNombre.getText(), txtApellido.getText());
    }

    private void eliminarCliente() {
        if (selectedCliente != null && clienteController.eliminarCliente(txtCedula.getText())) {
            listClientes.remove(selectedCliente);
            limpiarCamposCliente();
            limpiarSeleccion();
        }
    }

    private void actualizarCliente() {
        Cliente clienteActualizado = buildCliente();
        if (selectedCliente != null &&
                clienteController.actualizarCliente(selectedCliente.getCedula(), clienteActualizado)) {

            int index = listClientes.indexOf(selectedCliente);
            if (index >= 0) {
                listClientes.set(index, clienteActualizado);
            }

            tblListCliente.refresh();
            limpiarSeleccion();
            limpiarCamposCliente();
        }
    }

    private void limpiarSeleccion() {
        tblListCliente.getSelectionModel().clearSelection();
        limpiarCamposCliente();
    }

    private void limpiarCamposCliente() {
        txtCedula.clear();
        txtNombre.clear();
        txtApellido.clear();
    }

    public void setApp(App app) {
        this.app = app;
        this.clienteController = new ClienteController(app.empresa);
        initView();
    }
}
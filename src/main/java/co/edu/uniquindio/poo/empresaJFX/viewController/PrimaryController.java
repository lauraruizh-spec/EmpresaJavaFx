package co.edu.uniquindio.poo.empresaJFX.viewController;

import co.edu.uniquindio.poo.empresaJFX.App;
import javafx.fxml.FXML;

public class PrimaryController {

    private App app;

    @FXML
    void onOpenCrudCliente() {
        app.openCrudCliente();
    }


    @FXML
    void onOpenCrudProducto() {
        app.openCrudProducto();
    }

    public void setApp(App app) {
        this.app = app;
    }
}

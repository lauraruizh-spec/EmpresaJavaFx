module co.edu.uniquindio.poo.empresaJFX {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.uniquindio.poo.empresaJFX to javafx.fxml;
    exports co.edu.uniquindio.poo.empresaJFX;

    exports co.edu.uniquindio.poo.empresaJFX.controller;
    opens co.edu.uniquindio.poo.empresaJFX.controller to javafx.fxml;

    exports co.edu.uniquindio.poo.empresaJFX.model;
    opens co.edu.uniquindio.poo.empresaJFX.model to javafx.fxml;

    exports co.edu.uniquindio.poo.empresaJFX.viewController;
    opens co.edu.uniquindio.poo.empresaJFX.viewController to javafx.fxml;
}
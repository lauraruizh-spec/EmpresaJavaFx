module co.edu.uniquindio.poo.empresajavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.empresajavafx to javafx.fxml;
    exports co.edu.uniquindio.poo.empresajavafx;
}
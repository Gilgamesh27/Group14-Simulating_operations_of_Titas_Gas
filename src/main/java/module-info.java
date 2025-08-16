module com.oop.groupfourteen.group14simulating_operations_of_titas_gas {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.itextpdf.kernel;
    requires com.itextpdf.layout;
    requires com.itextpdf.io;
    requires kernel;
    requires layout;

    opens com.oop.groupfourteen.group14simulating_operations_of_titas_gas to javafx.fxml;

    exports com.oop.groupfourteen.group14simulating_operations_of_titas_gas;
}

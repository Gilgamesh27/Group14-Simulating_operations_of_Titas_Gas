module com.oop.groupfourteen.group14simulating_operations_of_titas_gas {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.oop.groupfourteen.group14simulating_operations_of_titas_gas to javafx.fxml;
    opens com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif to javafx.fxml;
    opens com.oop.groupfourteen.group14simulating_operations_of_titas_gas.KhalidurRahmanEfty to javafx.fxml;
    opens com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha to javafx.fxml;
    opens com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Tunaz to javafx.fxml;

    exports com.oop.groupfourteen.group14simulating_operations_of_titas_gas;
    exports com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;
    exports com.oop.groupfourteen.group14simulating_operations_of_titas_gas.KhalidurRahmanEfty;
    exports com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha;
    exports com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Tunaz;
}

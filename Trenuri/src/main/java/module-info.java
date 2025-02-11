module ubb.scs.map.trenuri {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ubb.scs.map.trenuri to javafx.fxml;
    exports ubb.scs.map.trenuri;
}
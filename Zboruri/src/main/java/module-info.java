module ubb.scs.map.zboruri {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ubb.scs.map.zboruri to javafx.fxml;
    exports ubb.scs.map.zboruri;
}
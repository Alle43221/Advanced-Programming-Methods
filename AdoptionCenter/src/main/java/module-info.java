module ubb.scs.map.template {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ubb.scs.map.template to javafx.fxml;
    exports ubb.scs.map.template;
}
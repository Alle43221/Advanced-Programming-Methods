module ubb.scs.map.faptebune {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens ubb.scs.map.faptebune to javafx.fxml;
    exports ubb.scs.map.faptebune;
}
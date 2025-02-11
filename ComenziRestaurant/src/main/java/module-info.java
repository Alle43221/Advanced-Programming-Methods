module ubb.scs.map.comenzirestaurant {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ubb.scs.map.comenzirestaurant to javafx.fxml;
    exports ubb.scs.map.comenzirestaurant;
}
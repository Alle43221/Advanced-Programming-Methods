module ubb.scs.map.ofertedevacanta {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ubb.scs.map.ofertedevacanta to javafx.fxml;
    exports ubb.scs.map.ofertedevacanta;
}
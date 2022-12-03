module com.example.mohammedsalam_daroshirani_comp228lab5 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql.rowset;
    requires java.desktop;

    opens com.example.mohammedsalam_daroshirani_comp228lab5 to javafx.fxml;
    exports com.example.mohammedsalam_daroshirani_comp228lab5;
}
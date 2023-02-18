module JavaFX{
    requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
    requires javafx.fxml;
    requires mongo.java.driver;
    exports beans;
    exports Affichage;
    exports Controller;
    exports Models;
}

module com.example.pokemondemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;
    requires javafx.graphics;

    opens com.example.pokemondemo to javafx.fxml;
    exports com.example.pokemondemo;
}
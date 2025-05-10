module com.example.restaurantevirtual {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.restaurantevirtual to javafx.fxml;
    exports com.example.restaurantevirtual;
}
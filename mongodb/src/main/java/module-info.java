module com.mycompany.mongodb {
    requires javafx.controls;
    requires javafx.fxml;
       requires mongo.java.driver;

    opens com.mycompany.mongodb to javafx.fxml;
    exports com.mycompany.mongodb;
}

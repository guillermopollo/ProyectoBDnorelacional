package com.mycompany.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.bson.Document;

public class SecondaryController  implements Initializable{
     
       
   PrimaryController c1 = new PrimaryController();

   MongoClient con; 
 ObservableList<String> TiposAlmendras = FXCollections.observableArrayList("Ninguno", "Avellana", "Almendra", "Anacardo", "Castañas de Brasil", "Nuez", "Pistacho", "macedonia");
    ObservableList<String> TiposChocolate = FXCollections.observableArrayList("Agridulce", "Puro", "Negro", "Leche", "blanco","ninguno");
    ObservableList<String> TiposRellenos = FXCollections.observableArrayList("Mora", "Mazapan", "Fresa", "Baya", "Mermelada", "Cereza Entera", "Ninguno", "Amaretto", "Crema decereza", "Mantequilla", "Coco");
  @FXML
  TextField Nombre;
    @FXML
   TextField Codigo;
      @FXML
  TextField Descripcion;
  @FXML
   ComboBox TipoAlmedra;
    @FXML
     ComboBox TipoChocolate;
      @FXML
     ComboBox TipoRelleno;
      @FXML
      private Button volver;
      
  @FXML
    private void MODIFICA() throws IOException {
      
         con = ConectionBD.Conection_DB.conectar();
        MongoDatabase database = con.getDatabase("bombon");
        MongoCollection<Document> collection = database.getCollection("Bombones");

   try {
        compruebaDatos(Codigo.getText(), TipoChocolate.getValue().toString(), TipoAlmedra.getValue().toString(), TipoRelleno.getValue().toString());
        
            Document third = collection.find(Filters.eq("Cod", Codigo.getText())).first();

            collection.updateOne(new Document("Cod", Codigo.getText()),
                    new Document("$set", new Document("Nombre", Nombre.getText())
                            .append("Descripcion", Descripcion.getText())
                            .append("TipoChocolate", TipoChocolate.getValue())
                            .append("TipoAlmendra", TipoAlmedra.getValue())
                            .append("TipoRelleno", TipoRelleno.getValue())
                            
                    )

            );
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

 
    
    
}
           public void compruebaDatos(String cod, String choco, String almendra, String rell ) throws Exception {
        if (choco != null && almendra != null && rell != null) {
            if (Pattern.matches("[A-Z]{1}[0-9]{2}", cod)) {
                getCodigos(c1.codigoChocolate);
                if (c1.codigoChocolate.contains(cod) == false) {
                      System.out.println("El codigo xd");
                }
            }
        } else {
            System.out.println("El codigo es incorrecto");
            throw new Exception("El codigo es incorrecto");
        }

    }
        private void getCodigos(ArrayList codigoChocolate) {
        for (int i = 0; i <c1.losChocolates.size(); i++) {
            String codigo = c1.losChocolates.get(i).getCod();
            if (codigoChocolate.contains(codigo) == false) {
                codigoChocolate.add(codigo);
            }
        }
    }
     
     @FXML
    private void BORRA() throws IOException {
   Nombre.setText(null);
     Codigo.setText(null);
      Descripcion.setText(null);
TipoChocolate.setValue(TiposChocolate.get(5));
TipoAlmedra.setValue(TiposAlmendras.get(0));
TipoRelleno.setValue(TiposRellenos.get(6));

 
    
    
}
      @FXML
    private void ELIMINAR() throws IOException {
   
con = ConectionBD.Conection_DB.conectar();
        MongoDatabase database = con.getDatabase("bombon");
        MongoCollection<Document> collection = database.getCollection("Bombones");
          try {
              collection.deleteMany(Filters.eq("Cod", Codigo.getText()));
          } catch (Exception e) {
          }
    
    
}
    @FXML
    private void VOLVER() throws IOException {
   Stage stage = new Stage();



       Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
        Scene scene = new Scene(root);
        stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
        //Para cerrar el login
        Stage loginStage = (Stage) this.volver.getScene().getWindow();
        loginStage.close();

 
    
    
}
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       TipoChocolate.getItems().addAll(TiposChocolate);
        TipoAlmedra.getItems().addAll(TiposAlmendras);
        TipoRelleno.getItems().addAll(TiposRellenos);
    }


}
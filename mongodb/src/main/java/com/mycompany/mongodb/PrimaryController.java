package com.mycompany.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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

public class PrimaryController implements   Initializable {
       ArrayList<Chocolate> losChocolates = new ArrayList();
    ArrayList<String> codigoChocolate = new ArrayList();
 MongoClient con; 
    @FXML
    private TextField Nombre;
    @FXML
    private TextField Codigo;
    @FXML
    private TextField Descripcion;
    @FXML
    private ComboBox TipoAlmedra;
    @FXML
    private ComboBox TipoChocolate;
    @FXML
    private ComboBox TipoRelleno;
    @FXML
            private Button Modificar;
  
    
 ObservableList<String> TiposAlmendras = FXCollections.observableArrayList("Ninguno", "Avellana", "Almendra", "Anacardo", "Castañas de Brasil", "Nuez", "Pistacho", "macedonia");
    ObservableList<String> TiposChocolate = FXCollections.observableArrayList("Agridulce", "Puro", "Negro", "Leche", "blanco","ninguno");
    ObservableList<String> TiposRellenos = FXCollections.observableArrayList("Mora", "Mazapan", "Fresa", "Baya", "Mermelada", "Cereza Entera", "Ninguno", "Amaretto", "Crema decereza", "Mantequilla", "Coco");
 
    
    public ArrayList<Chocolate> getCola(){
        if(this.losChocolates == null){
            losChocolates = new ArrayList<Chocolate>();
        }
    return this.losChocolates;
}
     public ArrayList<String> getcodigoChocolate(){
        if(this.codigoChocolate == null){
            codigoChocolate = new ArrayList<String>();
        }
    return this.codigoChocolate;
}
    @FXML
    private void BORRAR() throws IOException {
    Nombre.setText(null);
     Codigo.setText(null);
      Descripcion.setText(null);
TipoChocolate.setValue(TiposChocolate.get(5));
TipoAlmedra.setValue(TiposAlmendras.get(0));
TipoRelleno.setValue(TiposRellenos.get(6));

    
    }
    @FXML
    private void MODIFICAR() throws IOException {
    Stage stage = new Stage();



       Parent root = FXMLLoader.load(getClass().getResource("secondary.fxml"));
        Scene scene = new Scene(root);
        stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
        //Para cerrar el login
        Stage loginStage = (Stage) this.Modificar.getScene().getWindow();
        loginStage.close();

    
    }
    @FXML
    private void GUARDAR() throws IOException {
 con = ConectionBD.Conection_DB.conectar();
        MongoDatabase database = con.getDatabase("bombon");
        MongoCollection<Document> collection = database.getCollection("Bombones");

        try {
            compruebaDatos(Codigo.getText(), TipoChocolate.getValue().toString(), TipoAlmedra.getValue().toString(), TipoRelleno.getValue().toString(),codigoChocolate);
            generarChocolate(Codigo.getText(), Nombre.getText(), TipoChocolate.getValue().toString(), TipoAlmedra.getValue().toString(), TipoRelleno.getValue().toString(), Descripcion.getText(),losChocolates);

            Document a = new Document();
            a.append("Cod", Codigo.getText())
                    .append("Nombre", Nombre.getText())
                    .append("TipoChocolate", TipoChocolate.getValue())
                    .append("TipoAlmendra", TipoAlmedra.getValue())
                    .append("TipoRelleno", TipoRelleno.getValue())
                    .append("Descripcion", Descripcion.getText());
            collection.insertOne(a);
            System.out.println("CORRECTO");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
      public void generarChocolate(String cod, String nombre, String tipoAlme, String tipoRell, String tipoChoco, String desc,ArrayList losChocolates) {
        Chocolate elChocolate = new Chocolate(cod, nombre, tipoAlme, tipoRell, cod, desc);
        losChocolates.add(elChocolate);
        Collections.sort(losChocolates);
        codigoChocolate.add(cod);

    }
      public void compruebaDatos(String cod, String choco, String almendra, String rell ,ArrayList codigoChocolate) throws Exception {
        if (choco != null && almendra != null && rell != null) {
            if (Pattern.matches("[A-Z]{1}[0-9]{2}", cod)) {
                getCodigos(codigoChocolate);
                if (codigoChocolate.contains(cod) == false) {
                      System.out.println("El codigo xd");
                }
            }
        } else {
            System.out.println("El codigo es incorrecto");
            throw new Exception("El codigo es incorrecto");
        }

    }
        private void getCodigos(ArrayList codigoChocolate) {
        for (int i = 0; i < losChocolates.size(); i++) {
            String codigo = losChocolates.get(i).getCod();
            if (codigoChocolate.contains(codigo) == false) {
                codigoChocolate.add(codigo);
            }
        }
    }
          

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       TipoChocolate.getItems().addAll(TiposChocolate);
        TipoAlmedra.getItems().addAll(TiposAlmendras);
        TipoRelleno.getItems().addAll(TiposRellenos);
     
      

       
    }

}

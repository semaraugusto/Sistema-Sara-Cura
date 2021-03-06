/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drapo.dashboard;
import java.util.*;
import java.io.IOException;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
/**
 *
 * @author Henrique
 */
public class ConsultaController implements Initializable {    
    
    @FXML private Label entradaCliente;
    @FXML private Label entradaMedico;
    @FXML private Label entradaTelefone;
    @FXML private Label entradaValor;
    @FXML private Label entradaExame;
    

    @FXML private TextField edicaoValor;
    @FXML private TextField edicaoMedico;
    @FXML private TextField edicaoTelefone;
    @FXML private TextField edicaoCliente;
    @FXML private TextField edicaoExame;
    
    @FXML private Button botaoEditar;
    
    @FXML
    private void botao_editar(ActionEvent event) {  
        if(Objects.equals(botaoEditar.getText(), "Editar")){
            entradaValor.setVisible(false);
            entradaMedico.setVisible(false);
            entradaCliente.setVisible(false);
            entradaExame.setVisible(false);
            entradaTelefone.setVisible(false);         
            
            edicaoValor.setText(entradaValor.getText());
            edicaoExame.setText(entradaExame.getText());
            edicaoMedico.setText(entradaMedico.getText());
            edicaoCliente.setText(entradaCliente.getText());
            edicaoTelefone.setText(entradaTelefone.getText());

            edicaoValor.setVisible(true);
            edicaoExame.setVisible(true);
            edicaoMedico.setVisible(true);
            edicaoCliente.setVisible(true);
            edicaoTelefone.setVisible(true);
            
            botaoEditar.setText("Salvar");
        }else{
            edicaoValor.setVisible(false);
            edicaoExame.setVisible(false);
            edicaoMedico.setVisible(false);
            edicaoCliente.setVisible(false);
            edicaoTelefone.setVisible(false);
            
            entradaValor.setText(edicaoValor.getText());
            entradaExame.setText(edicaoExame.getText());
            entradaMedico.setText(edicaoMedico.getText());
            entradaCliente.setText(edicaoCliente.getText());
            entradaTelefone.setText(edicaoTelefone.getText());
            
            entradaValor.setVisible(true);
            entradaExame.setVisible(true);
            entradaMedico.setVisible(true);
            entradaCliente.setVisible(true);
            entradaTelefone.setVisible(true);
            
            botaoEditar.setText("Editar");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        edicaoValor.setVisible(false);
        edicaoTelefone.setVisible(false);
        edicaoExame.setVisible(false);
        edicaoMedico.setVisible(false);
        edicaoCliente.setVisible(false);
    }   
    
}

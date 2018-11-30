/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drapo.dashboard;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
/**
 *
 * @author oXCToo
 */
public class HomeController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private VBox pnl_scroll;
    
    @FXML
    private void painel_agendar_consulta(ActionEvent event) {            
        pnl_scroll.getChildren().clear();
    }
    @FXML
    private void painel_agendar_exame(ActionEvent event) {            
        pnl_scroll.getChildren().clear();
    }
    @FXML
    private void painel_pesquisar_consulta(ActionEvent event) {            
        atualizar_consultas();
    }
    @FXML
    private void painel_pesquisar_exame(ActionEvent event) {            
        atualizar_exames();
    }
    @FXML
    private void painel_cadastrar_medico(ActionEvent event) {            
        pnl_scroll.getChildren().clear();
    }
    @FXML
    private void painel_cadastrar_equipamento(ActionEvent event) {            
        pnl_scroll.getChildren().clear();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void atualizar_consultas()
    {
        pnl_scroll.getChildren().clear();
        
        Node [] nodes = new  Node[15];
        
        for(int i = 0; i<3; i++)
        {
            try {
                nodes[i] = (Node)FXMLLoader.load(getClass().getResource("Consulta.fxml"));
               pnl_scroll.getChildren().add(nodes[i]);
                
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }  
    }
    
    private void atualizar_exames()
    {
        pnl_scroll.getChildren().clear();
        
        Node [] nodes = new  Node[15];
        
        for(int i = 0; i<3; i++)
        {
            try {
                nodes[i] = (Node)FXMLLoader.load(getClass().getResource("Exame.fxml"));
               pnl_scroll.getChildren().add(nodes[i]);
                
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }  
    }
    
}

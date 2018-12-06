/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drapo.dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class Agendar_ExameController implements Initializable {
    
    @FXML private RadioButton rb_particular;
    @FXML private RadioButton rb_convenio;
    @FXML private RadioButton rb_cartao;
    @FXML private RadioButton rb_cheque;
    @FXML private RadioButton rb_dinheiro;
    
    @FXML private Label label_convenio;
    @FXML private Label label_matricula;    
    
    @FXML private TextField tf_convenio;
    @FXML private TextField tf_matricula;
    @FXML private TextField tf_cliente;
    @FXML private TextField tf_telefone; 
    @FXML private TextField tf_data; 
    
    @FXML private ComboBox cb_equipamento;
    @FXML private ComboBox cb_medico;
    @FXML private ComboBox cb_hora;
    @FXML private ComboBox cb_min;
    
    @FXML
    private void particular_clique(){
        if(rb_convenio.isSelected()){
            rb_convenio.setSelected(false);
            
            label_convenio.setVisible(false);
            label_matricula.setVisible(false);
            tf_convenio.setVisible(false);
            tf_matricula.setVisible(false);
        }
        
        rb_particular.setSelected(true);
        rb_cartao.setVisible(true);
        rb_dinheiro.setVisible(true);
        rb_cheque.setVisible(true);
    }
    
     @FXML
    private void convenio_clique(){
        if(rb_particular.isSelected()){
            rb_particular.setSelected(false);
            
            rb_cartao.setVisible(false);
            rb_dinheiro.setVisible(false);
            rb_cheque.setVisible(false);
        }
        
        rb_convenio.setSelected(true);
        label_convenio.setVisible(true);
        label_matricula.setVisible(true);
        tf_convenio.setVisible(true);
        tf_matricula.setVisible(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

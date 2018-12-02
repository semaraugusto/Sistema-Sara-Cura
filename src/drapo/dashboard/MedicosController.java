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
import javafx.scene.input.*;
/**
 *
 * @author Henrique
 */
public class MedicosController implements Initializable {    
    
    private int cbSelected = 0;
    List<String> medicosNomes = new ArrayList<String>();
    List<String> medicosEspecialidades = new ArrayList<String>();
    String horasInicio[] = { "07:00", "08:00", "09:00", "10:00", "11:00", "12:00" };
    String horasFim[] = { "15:00", "16:00", "17:00", "18:00", "19:00", "20:00" };
    
    @FXML private TextField textfield_nome;
    
    @FXML private ChoiceBox choicebox_horaInicio;
    @FXML private ChoiceBox choicebox_horaFim;
    
    @FXML private RadioButton rb_15;
    @FXML private RadioButton rb_20;
    @FXML private RadioButton rb_30;
    
    @FXML private CheckBox cb_seg;
    @FXML private CheckBox cb_ter;
    @FXML private CheckBox cb_qua;
    @FXML private CheckBox cb_qui;
    @FXML private CheckBox cb_sex;
    @FXML private CheckBox cb_sab;
    @FXML private CheckBox cb_dom;
    
    @FXML private ComboBox cb_especialidades;
    
    @FXML
    private void especialidades_enter_press(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            medicosEspecialidades.add((String)cb_especialidades.getValue());
            cb_especialidades.setItems(FXCollections.observableArrayList(medicosEspecialidades));
            cb_especialidades.setValue("");
        }
    }
    
    @FXML
    private void botao_salvar(ActionEvent event) {  
//        medicosNomes.add(textfield_nome.getText());
        System.out.println(textfield_nome.getText());
    }
    
    @FXML
    private void rb_clique(ActionEvent event) {  
        RadioButton rb_clicado;
        
        rb_15.setSelected(false);
        rb_20.setSelected(false);
        rb_30.setSelected(false);
        
        rb_clicado = (RadioButton)event.getSource();
        rb_clicado.setSelected(true);
    }
    
    @FXML
    private void cb_clique(ActionEvent event) {  
        CheckBox cb_clicada;
        cb_clicada = (CheckBox)event.getSource();
        
        if(cb_clicada.isSelected()){
            if(cbSelected < 3){
                cbSelected++;
            }else{
                cb_clicada.setSelected(false);
            }
        }else{
            cbSelected--;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        choicebox_horaInicio.setItems(FXCollections.observableArrayList(horasInicio));
        choicebox_horaFim.setItems(FXCollections.observableArrayList(horasFim));
    }   
    
}

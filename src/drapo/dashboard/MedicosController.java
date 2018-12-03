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
    List<String> medicosEspecialidades = new ArrayList<String>();
    String horas[] = { "07:00-13:00", "08:00-14:00", "09:00-15:00", "10:00-16:00", "11:00-17:00", "12:00-18:00" };
    List<Medico> medicos = new ArrayList<Medico>();
    
    @FXML private TextField textfield_nome;
    
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
    @FXML private ComboBox cb_hora;
    
    @FXML
    private void especialidades_enter_press(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            medicosEspecialidades.add((String)cb_especialidades.getValue());
            cb_especialidades.setItems(FXCollections.observableArrayList(medicosEspecialidades));
            cb_especialidades.setValue("");
        }
    }
    
    private boolean entradaOK(int tempo, int horario, boolean dias[], String nome, List<String> especialidades){
        if(tempo == -1)
            return false;
        
        if(horario == -1)
            return false;
        
        int cntDias=0;
        for(boolean dia : dias)
            if(dia)
                cntDias++;
        if(cntDias != 3)
            return false;
        
        if(Objects.equals("", nome))
            return false;
        
        if(especialidades.isEmpty())
            return false;
        
        return true;
    }
    
    @FXML
    private void botao_salvar(ActionEvent event) {
        int tempo=-1, horario=-1, i;
        boolean dias[] = new boolean[7];
        String nome = "";
        List<String> especialidades = new ArrayList<String>();
        
        if(rb_15.isSelected())
            tempo = 15;
        else if(rb_20.isSelected())
            tempo = 20;
        else if(rb_30.isSelected())
            tempo = 30;
        
        for(i=0;i<6;i++)
            if(cb_hora.getValue() == horas[i]){
                horario = i;
                break;
            }
        
        if(cb_seg.isSelected())
            dias[0] = true;
        if(cb_ter.isSelected())
            dias[1] = true;
        if(cb_qua.isSelected())
            dias[2] = true;
        if(cb_qui.isSelected())
            dias[3] = true;
        if(cb_sex.isSelected())
            dias[4] = true;
        if(cb_sab.isSelected())
            dias[5] = true;
        if(cb_dom.isSelected())
            dias[6] = true;
        
        nome = textfield_nome.getText();
        
        ObservableList<String> items = cb_especialidades.getItems();
        for(String item : items){
            especialidades.add(item);
        }
        
        if(entradaOK(tempo, horario, dias, nome, especialidades)){
            Medico novo = new Medico(tempo, horario, dias, nome, especialidades);
            System.out.println(novo.toString());
        }else{
            System.out.println("DEU MERDA");
        }
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
        cb_hora.setItems(FXCollections.observableArrayList(horas));
    }   
    
}

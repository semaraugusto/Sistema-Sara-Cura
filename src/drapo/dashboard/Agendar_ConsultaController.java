/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drapo.dashboard;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Henrique
 */
public class Agendar_ConsultaController implements Initializable {

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
    @FXML private TextField tf_valor;
    @FXML private TextField tf_data; 
    
    @FXML private ComboBox cb_especialidade;
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
    
    private void limpaCampos(){
        
    }
    
    private void verificar_particular() throws Exception{
        if(rb_cartao.isSelected() || rb_cheque.isSelected()){
            int random = (int)(Math.random() * 10 + 1);
            if(random == 1){
                throw new Exception();
            }
        }else if(!rb_dinheiro.isSelected())
            JOptionPane.showMessageDialog(null, "Nenhum método de pagamento foi selecionado.", "Pagamento inconsistente", JOptionPane.ERROR_MESSAGE);
    }
    
    private void verificar_convenio() throws Exception{
        int random = (int)(Math.random() * 12 + 1);
        if(random == 1){
            throw new Exception();
        }
    }
    
    private boolean entradaOK(){
        if(Objects.equals(tf_cliente.getText(), "")){
            JOptionPane.showMessageDialog(null, "O nome do cliente não foi inserido.", "Dados inconsistentes", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(Objects.equals(tf_telefone.getText(), "")){
            JOptionPane.showMessageDialog(null, "O telefone do cliente não foi inserido.", "Dados inconsistentes", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(Objects.equals(tf_valor.getText(), "")){
            JOptionPane.showMessageDialog(null, "O valor da consulta não foi inserido.", "Dados inconsistentes", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(Objects.equals(cb_especialidade.getValue(), "")){
            JOptionPane.showMessageDialog(null, "A especialidade não foi selecionada.", "Dados inconsistentes", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(Objects.equals(cb_medico.getValue(), "")){
            JOptionPane.showMessageDialog(null, "O médico não foi selecionado.", "Dados inconsistentes", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(!rb_particular.isSelected() && !rb_convenio.isSelected()){
            JOptionPane.showMessageDialog(null, "O método de pagamento não foi selecionado.", "Dados inconsistentes", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    @FXML
    private void verificar_clique(){
        if(entradaOK()){
            if(rb_particular.isSelected()){
                try{
                    verificar_particular();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Pagamento recusado.", "Pagamento inconsistente", JOptionPane.ERROR_MESSAGE);
                    limpaCampos();
                }
            }else{
                try{
                    verificar_convenio();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Autorização pelo convênio " + tf_convenio.getText() + " para o cliente " + tf_cliente.getText() + " negada.", "Pagamento inconsistente", JOptionPane.ERROR_MESSAGE);
                    limpaCampos();
                }
            }
        }
    }
    
    @FXML
    private void agendar_clique(){
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

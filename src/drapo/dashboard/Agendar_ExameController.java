/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drapo.dashboard;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

public class Agendar_ExameController implements Initializable {
    
    boolean dataNaoUsada;
    String forma_de_atendimento;
    
    String arrMin15[] = {"00", "15", "30", "45"};
    String arrMin20[] = {"00", "20", "40"};
    String arrMin30[] = {"00", "30"};
    String arrHora[] = {"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"};
    List<String> horasDisponiveis = new ArrayList<>();
    List<String> minsDisponiveis = new ArrayList<>();
    
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
    
    @FXML private TextArea ta_especificacoes;
    
    @FXML private ComboBox cb_equipamento;
    @FXML private ComboBox cb_medico;
    @FXML private ComboBox cb_hora;
    @FXML private ComboBox cb_min;
    
    @FXML
    private void cheque_clique(){
        if(rb_cheque.isSelected()){
            rb_cartao.setSelected(false);
            rb_dinheiro.setSelected(false);
        }
    }
    
    @FXML
    private void cartao_clique(){
        if(rb_cartao.isSelected()){
            rb_cheque.setSelected(false);
            rb_dinheiro.setSelected(false);
        }
    }
    
    @FXML
    private void dinheiro_clique(){
        if(rb_dinheiro.isSelected()){
            rb_cheque.setSelected(false);
            rb_cartao.setSelected(false);
        }
    }
    
    @FXML
    private void particular_clique(){
        if(rb_convenio.isSelected()){
            forma_de_atendimento = "Particular";
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
            forma_de_atendimento = "Convênio";
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
        
        if(Objects.equals(cb_equipamento.getValue(), "")){
            JOptionPane.showMessageDialog(null, "O equipamento não foi selecionado.", "Dados inconsistentes", JOptionPane.ERROR_MESSAGE);
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
    
    private boolean formatoDataOK(String data){
        if(data.length() != 10)
            return false;
        if(!Objects.equals(data.charAt(2),'/') || !Objects.equals(data.charAt(5),'/'))
            return false;
        if(!Character.isDigit(data.charAt(0)) || !Character.isDigit(data.charAt(1)))
            return false;
        if(!Character.isDigit(data.charAt(3)) || !Character.isDigit(data.charAt(4)))
            return false;
        if(!Character.isDigit(data.charAt(6)) || !Character.isDigit(data.charAt(7)) || !Character.isDigit(data.charAt(8)) || !Character.isDigit(data.charAt(9)))
            return false;
        
        return true;
    }
    
    private int congruenciaZeller(int dia, int mes, int ano){
        if(mes == 1){
            mes = 13;
            ano--;
        }else if(mes == 2){
            mes = 14;
            ano--;
        }
        int q = dia;
        int m = mes;
        int k = ano%100;
        int j = ano/100;
        int h = q + 13*(m+1)/5 + k + k/4 + j/4 + 5*j;
        h = h%7;

        return h;
    }
    
    private boolean dataOK(String data, boolean dias[]){
        if(formatoDataOK(data)){
            String nomeDias[] = {"Sábado", "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
            int dia = Integer.parseInt(data.substring(0,2));
            int mes = Integer.parseInt(data.substring(3,5));
            int ano = Integer.parseInt(data.substring(6));
            int diaSemana = congruenciaZeller(dia, mes, ano);
            if(dias[(diaSemana-2)%7]){
                return true;
            }else{
                //JOptionPane.showMessageDialog(null, "A data inserida é " + nomeDias[diaSemana] + " e o médico selecionado...", "Dados inconsistentes", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }else{
            JOptionPane.showMessageDialog(null, "O formato de data usado está incorreto (usar dd/mm/aaaa).", "Dados inconsistentes", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    @FXML
    private void checa_dataFim(KeyEvent e){
        String data = tf_data.getText();
        if(data.length() == 9){
            if(!formatoDataOK(data + e.getCharacter())){
                JOptionPane.showMessageDialog(null, "O formato de data usado está incorreto (usar dd/mm/aaaa).", "Dados inconsistentes", JOptionPane.ERROR_MESSAGE);
                tf_data.setText("");
                cb_equipamento.setDisable(true);
                e.consume();
            }else{
                cb_equipamento.setDisable(false);
            }
        }else if(data.length()>=10)
            e.consume();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataNaoUsada = true;
        cb_medico.setDisable(true);
        cb_equipamento.setDisable(true);
        cb_hora.setDisable(true);
        cb_min.setDisable(true);
        
        cb_medico.getItems().clear();
        cb_equipamento.getItems().clear();
        cb_hora.getItems().clear();
        cb_min.getItems().clear();
        
        tf_cliente.setText("");
        tf_telefone.setText("");
        tf_data.setText("");
        tf_valor.setText("");
        
        ta_especificacoes.setText("");
        
        rb_particular.setSelected(true);
        forma_de_atendimento = "Particular";
        particular_clique();
        
    }    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drapo.dashboard;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        
//            case 0 : cout << "Saturday \n"; break; 
//            case 1 : cout << "Sunday \n"; break; 
//            case 2 : cout << "Monday \n"; break; 
//            case 3 : cout << "Tuesday \n"; break; 
//            case 4 : cout << "Wednesday \n"; break; 
//            case 5 : cout << "Thursday \n"; break; 
//            case 6 : cout << "Friday \n"; break; 

        return h;
    }
    
    private boolean dataOK(String data, boolean dias[]){
        if(formatoDataOK(data)){
            String nomeDias[] = {"Sábado", "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
            int dia = Integer.parseInt(data.substring(0,2));
            int mes = Integer.parseInt(data.substring(3,5));
            int ano = Integer.parseInt(data.substring(6));
            int diaSemana = congruenciaZeller(dia, mes, ano);
            System.out.println(diaSemana);
            if(dias[diaSemana]){
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "A data inserida é " + nomeDias[diaSemana] + " e o médico selecionado...", "Dados inconsistentes", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }else{
            JOptionPane.showMessageDialog(null, "O formato de data usado está incorreto (usar dd/mm/aaaa).", "Dados inconsistentes", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    @FXML
    private void agendar_clique(){
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
                    Medico med = null;
                    
                    for(Medico it : HomeController.medicos)
                        if(Objects.equals(it.nome, cb_medico.getValue()))
                            med = it;
                    
                    if(med != null){
                        if(dataOK(tf_data.getText(), med.dias))
                            med.marcaConsulta();
                    }else{
                        JOptionPane.showMessageDialog(null, "Médico não cadastrado.", "Dados inconsistentes", JOptionPane.ERROR_MESSAGE);
                    }                    
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Autorização pelo convênio " + tf_convenio.getText() + " para o cliente " + tf_cliente.getText() + " negada.", "Pagamento inconsistente", JOptionPane.ERROR_MESSAGE);
                    limpaCampos();
                }
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<String> medicos = new ArrayList<>();
        List<String> especialidades = new ArrayList<>();
        
        for(Medico it : HomeController.medicos){
            medicos.add(it.nome);
            for(String itt : it.especialidades)
                especialidades.add(itt);
        }
        cb_medico.setItems(FXCollections.observableArrayList(medicos));
        cb_especialidade.setItems(FXCollections.observableArrayList(especialidades));
    }    
    
}

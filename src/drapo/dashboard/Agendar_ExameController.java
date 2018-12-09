package drapo.dashboard;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
    String horario;
    String equipamento;
    
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
        
        if(Objects.equals(ta_especificacoes.getText(), "")){
            JOptionPane.showMessageDialog(null, "As especificações não foram dadas.", "Dados inconsistentes", JOptionPane.ERROR_MESSAGE);
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
    
    @FXML
    private void filtraMedicos(){
        cb_medico.getItems().clear();
        
        String especialidade = "";
        for(Equipamento it : HomeController.equipamentos){
                if(Objects.equals(it.equipamento, cb_equipamento.getValue())){
                    especialidade = it.especialidade;
                    break;
                }
        }
        
        List<String> medicos = new ArrayList<>();
        for(Medico it : HomeController.medicos){
            for(String itt : it.especialidades)
                if(Objects.equals(itt, especialidade) && dataOK(tf_data.getText(), it.dias))
                    medicos.add(it.nome);
        }
        cb_medico.setItems(FXCollections.observableArrayList(medicos));
        
        cb_medico.setDisable(false);
    }
    
    @FXML
    private void filtraHora(){
        horasDisponiveis.clear();
        minsDisponiveis.clear();
        Medico med = null;

        for(Medico it : HomeController.medicos)
            if(Objects.equals(it.nome, cb_medico.getValue()))
                med = it;

        if(med != null){
            if(dataOK(tf_data.getText(), med.dias)){
                int i;
                for(i=med.horario;i<med.horario+6;i++)
                    horasDisponiveis.add(arrHora[i]);
                cb_hora.setItems(FXCollections.observableArrayList(horasDisponiveis));
                cb_hora.setDisable(false);
            }else{
                filtraMedicos();
            }
        }
    }
    
    @FXML
    private void filtraMin(){
        minsDisponiveis.clear();
        Medico med = null;
        int horaAtual, intervaloAtual;

        for(Medico it : HomeController.medicos)
            if(Objects.equals(it.nome, cb_medico.getValue()))
                med = it;

        if(med != null){
            if(dataOK(tf_data.getText(), med.dias)){
                for(DiaTrabalho dia : med.agenda){
                    if(Objects.equals(dia.data, tf_data.getText())){
                        horaAtual = 0;
                        for(boolean hora[] : dia.horarios){
                            if(Objects.equals(cb_hora.getValue(), arrHora[med.horario+horaAtual])){
                                intervaloAtual = 0;
                                for(boolean marcada : hora){
                                    if(!marcada){
                                        if(med.tempo == 15)
                                            minsDisponiveis.add(arrMin15[intervaloAtual]);
                                        else if(med.tempo == 20)
                                            minsDisponiveis.add(arrMin20[intervaloAtual]);
                                        else
                                            minsDisponiveis.add(arrMin30[intervaloAtual]);
                                    }
                                    intervaloAtual++;
                                }
                            }
                            horaAtual++;
                        }
                        dataNaoUsada = false;
                    }
                }
                if(dataNaoUsada){
                    if(med.tempo == 15)
                        cb_min.setItems(FXCollections.observableArrayList(arrMin15));
                    else if(med.tempo == 20)
                        cb_min.setItems(FXCollections.observableArrayList(arrMin20));
                    else
                        cb_min.setItems(FXCollections.observableArrayList(arrMin30));
                }else{
                    cb_min.setItems(FXCollections.observableArrayList(minsDisponiveis));
                }
                cb_min.setDisable(false);
            }else{
                filtraMedicos();
            }
        }
    }
    
    private void limpaCampos(){
        dataNaoUsada = true;
        cb_equipamento.setDisable(true);
        cb_medico.setDisable(true);
        cb_hora.setDisable(true);
        cb_min.setDisable(true);
        
        List<String> especialidades = new ArrayList<>();
        cb_medico.getItems().clear();
        cb_equipamento.getItems().clear();
        cb_hora.getItems().clear();
        cb_min.getItems().clear();
        
        tf_cliente.setText("");
        tf_telefone.setText("");
        tf_data.setText("");
        tf_valor.setText("");
        tf_convenio.setText("");
        tf_matricula.setText("");
        
        List<String> equipamentos = new ArrayList<>();
        for(Equipamento it : HomeController.equipamentos){
            if(!equipamentos.contains(it.equipamento))
                    equipamentos.add(it.equipamento);
        }
        cb_equipamento.setItems(FXCollections.observableArrayList(equipamentos));
        
        rb_particular.setSelected(true);
        forma_de_atendimento = "Particular";
        particular_clique();
    }
    
    @FXML
    private void agendar_clique(){
        if(entradaOK()){
            if(rb_particular.isSelected()){
                try{
                    verificar_particular();
                    Medico med = null;
                    
                    for(Medico it : HomeController.medicos)
                        if(Objects.equals(it.nome, cb_medico.getValue()))
                            med = it;
                    
                    if(med != null){
                        if(dataOK(tf_data.getText(), med.dias)){
                            med.marcaConsulta(tf_data.getText(), (String)cb_hora.getValue(), (String)cb_min.getValue());
                            horario = (String)cb_hora.getValue() + ":" + (String)cb_min.getValue();
                            Exame exame = new Exame(tf_cliente.getText(), tf_telefone.getText(), tf_data.getText(), (String)cb_equipamento.getValue(), (String)cb_medico.getValue(), horario, ta_especificacoes.getText(), tf_valor.getText(), forma_de_atendimento);
                            HomeController.exames.add(exame);
                            JOptionPane.showMessageDialog(null, "Exame agendado com sucesso!", "Sucesso", JOptionPane.PLAIN_MESSAGE);
                            limpaCampos();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Médico não cadastrado.", "Dados inconsistentes", JOptionPane.ERROR_MESSAGE);
                    } 
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
                        if(dataOK(tf_data.getText(), med.dias)){
                            med.marcaConsulta(tf_data.getText(), (String)cb_hora.getValue(), (String)cb_min.getValue());
                            horario = (String)cb_hora.getValue() + ":" + (String)cb_min.getValue();
                            Exame exame = new Exame(tf_cliente.getText(), tf_telefone.getText(), tf_data.getText(), (String)cb_equipamento.getValue(), (String)cb_medico.getValue(), horario, ta_especificacoes.getText(), tf_valor.getText(), forma_de_atendimento);
                            HomeController.exames.add(exame);
                            JOptionPane.showMessageDialog(null, "Exame agendado com sucesso!", "Sucesso", JOptionPane.PLAIN_MESSAGE);
                            limpaCampos();
                        }
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
        dataNaoUsada = true;
        cb_medico.setDisable(true);
        cb_hora.setDisable(true);
        cb_min.setDisable(true);
        cb_equipamento.setDisable(true);
        
        List<String> equipamentos = new ArrayList<>();
        for(Equipamento it : HomeController.equipamentos){
            if(!equipamentos.contains(it.equipamento))
                    equipamentos.add(it.equipamento);
        }
        cb_equipamento.setItems(FXCollections.observableArrayList(equipamentos));
        
        rb_particular.setSelected(true);
        forma_de_atendimento = "Particular";
        particular_clique();
        
    }    
    
}

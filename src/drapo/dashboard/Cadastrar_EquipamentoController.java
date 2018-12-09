package drapo.dashboard;
import java.util.*;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javax.swing.JOptionPane;
/**
 * @author Samuel Aguiar
 */
public class Cadastrar_EquipamentoController implements Initializable {    
    
    String funcionamento[] = {"07:00-13:00", "08:00-14:00", "09:00-15:00", "10:00-16:00", "11:00-17:00", "12:00-18:00", "13:00-19:00", "14:00-20:00"};
    String status[] = {"Ativo", "Em manutenção"};
    
    @FXML private TextField tf_equipamento;
    @FXML private TextField tf_especialidade;
    
    @FXML private ComboBox cb_funcionamento;
    @FXML private ComboBox cb_status;
    
    @FXML private RadioButton rb_20;
    
    @FXML private Button botao_salvar;
    
    private void limpaCampos(){
        tf_equipamento.setText("");
        tf_especialidade.setText("");
        
        cb_funcionamento.setValue("");
        cb_status.setValue("");     
    }
    
    private boolean entradaOK(String equipamento, String especialidade, int tempo, int horario, String status){
       if(Objects.equals("", equipamento)){
            JOptionPane.showMessageDialog(null, "O nome do equipamento não foi inserido.", "Dados inconsistentes", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(Objects.equals("", especialidade)){
            JOptionPane.showMessageDialog(null, "Nenhuma especialidade foi inserida.", "Dados inconsistentes", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(tempo == -1){
            JOptionPane.showMessageDialog(null, "O tempo de consulta não foi selecionado.", "Dados inconsistentes", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(horario == -1){
            JOptionPane.showMessageDialog(null, "O horário de funcionamento não foi estabelecido.", "Dados inconsistentes", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(Objects.equals("", especialidade)){
            JOptionPane.showMessageDialog(null, "Nenhuma especialidade foi inserida.", "Dados inconsistentes", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    @FXML
    private void botao_salvar(ActionEvent event) {
        int tempo=-1, horario=-1;
        String equipamento = "";
        String especialidade = "";
        String status = "";
        String horario_de_funcionamento = "";
        
        
        if(rb_20.isSelected())
            tempo = 20;
        
        for(int i=0;i<8;i++)
            if(cb_funcionamento.getValue() == funcionamento[i]){
                horario = i;
                horario_de_funcionamento = funcionamento[i];
                break;
            }
             
        equipamento = tf_equipamento.getText();
        especialidade = tf_especialidade.getText();
        status = (String) cb_status.getValue();
        
        if(entradaOK(equipamento, especialidade, tempo, horario, status)){
            Equipamento novo = new Equipamento(equipamento, especialidade, "20", horario_de_funcionamento, status);
            JOptionPane.showMessageDialog(null, "O equipamento " + equipamento + " foi cadastrado com sucesso!", "Sucesso", JOptionPane.PLAIN_MESSAGE);
            HomeController.equipamentos.add(novo);
            limpaCampos();
        }
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cb_funcionamento.setItems(FXCollections.observableArrayList(funcionamento));
        cb_status.setItems(FXCollections.observableArrayList(status));
        rb_20.setSelected(true);
    }
    
}

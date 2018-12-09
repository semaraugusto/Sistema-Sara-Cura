package drapo.dashboard;
import java.util.*;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
/**
 *
 * @author Henrique
 */
public class ConsultaController implements Initializable {    
    
    @FXML private Label entradaReferencia;
    @FXML private Label entradaCliente;
    @FXML private Label entradaMedico;
    @FXML private Label entradaTelefone;
    @FXML private Label entradaValor;
    @FXML private Label entradaExame;
    @FXML private Label entradaData;
    @FXML private Label entradaHorario;
    

    @FXML private TextField edicaoValor;
    @FXML private TextField edicaoMedico;
    @FXML private TextField edicaoTelefone;
    @FXML private TextField edicaoCliente;
    @FXML private TextField edicaoExame;
    
    @FXML private Button botaoEditar;
    @FXML private Button botaoDesmarcar;
    
    @FXML
    private void botao_desmarcar(ActionEvent event) {  
        Consulta consulta = null;                    
        for(Consulta it : HomeController.consultas){
            if(Objects.equals(it.ref, entradaReferencia.getText())){
                HomeController.consultas.remove(it);
                break;
            }
        }
        botaoEditar.setDisable(true);
        botaoDesmarcar.setDisable(true);
        botaoDesmarcar.setText("Desmarcada");            
    }
    
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
            
            Consulta consulta = null;                    
            for(Consulta it : HomeController.consultas){
                if(Objects.equals(it.ref, entradaReferencia.getText())){
                    it.valor = edicaoValor.getText();
                    it.forma_de_atendimento = edicaoExame.getText();
                    it.medico = edicaoMedico.getText();
                    it.paciente = edicaoCliente.getText();
                    it.telefone = edicaoTelefone.getText();
                }
            }
            
            entradaValor.setVisible(true);
            entradaExame.setVisible(true);
            entradaMedico.setVisible(true);
            entradaCliente.setVisible(true);
            entradaTelefone.setVisible(true);
            
            botaoEditar.setText("Editar");
        }
    }
    
    public void iniciar(String referencia, String paciente, String telefone, String medico, String valor, String forma_de_atendimento, String data, String horario) {
        entradaReferencia.setText(referencia);
        entradaCliente.setText(paciente);
        entradaMedico.setText(medico);
        entradaTelefone.setText(telefone);
        entradaValor.setText(valor);
        entradaExame.setText(forma_de_atendimento);
        entradaData.setText(data);
        entradaHorario.setText(horario);        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        edicaoValor.setVisible(false);
        edicaoTelefone.setVisible(false);
        edicaoExame.setVisible(false);
        edicaoMedico.setVisible(false);
        edicaoCliente.setVisible(false);
    }   
    
}

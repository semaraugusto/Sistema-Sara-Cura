/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drapo.dashboard;
import java.util.*;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
/**
 *
 * @author Henrique
 */
public class ExameController implements Initializable {    
    
    @FXML private Label entradaReferencia;
    @FXML private Label entradaCliente;
    @FXML private Label entradaMedico;
    @FXML private Label entradaExame;
    @FXML private Label entradaValor;
    @FXML private Label entradaTelefone;
    @FXML private Label entradaEquipamento;
    @FXML private Text entradaEspecificacoes;
    @FXML private Label entradaData;
    @FXML private Label entradaHorario;

    @FXML private TextField edicaoCliente;
    @FXML private TextField edicaoMedico;
    @FXML private TextField edicaoTelefone;
    @FXML private TextField edicaoValor;
    @FXML private TextField edicaoExame;
    
    @FXML private TextArea edicaoEspecificacoes;
    
    @FXML private Button botaoEditar;
    @FXML private Button botaoDesmarcar;
    
    @FXML
    private void botao_editar(ActionEvent event) {  
        if(Objects.equals(botaoEditar.getText(), "Editar")){
            entradaValor.setVisible(false);
            entradaExame.setVisible(false);
            entradaMedico.setVisible(false);
            entradaCliente.setVisible(false);
            entradaTelefone.setVisible(false);
            entradaEspecificacoes.setVisible(false);

            edicaoValor.setText(entradaValor.getText());
            edicaoExame.setText(entradaExame.getText());
            edicaoMedico.setText(entradaMedico.getText());
            edicaoCliente.setText(entradaCliente.getText());
            edicaoTelefone.setText(entradaTelefone.getText());
            edicaoEspecificacoes.setText(entradaEspecificacoes.getText());

            edicaoValor.setVisible(true);
            edicaoExame.setVisible(true);
            edicaoMedico.setVisible(true);
            edicaoCliente.setVisible(true);
            edicaoTelefone.setVisible(true);
            edicaoEspecificacoes.setVisible(true);
            
            botaoEditar.setText("Salvar");
        }else{
            edicaoValor.setVisible(false);
            edicaoExame.setVisible(false);
            edicaoMedico.setVisible(false);
            edicaoCliente.setVisible(false);
            edicaoTelefone.setVisible(false);
            edicaoEspecificacoes.setVisible(false);
            
            entradaValor.setText(edicaoValor.getText());
            entradaExame.setText(edicaoExame.getText());
            entradaMedico.setText(edicaoMedico.getText());
            entradaCliente.setText(edicaoCliente.getText());
            entradaTelefone.setText(edicaoTelefone.getText());
            entradaEspecificacoes.setText(edicaoEspecificacoes.getText());
            
            Exame exame = null;                    
            for(Exame it : HomeController.exames){
                if(Objects.equals(it.ref, entradaReferencia.getText())){
                    it.paciente = edicaoCliente.getText();
                    it.medico = edicaoMedico.getText();
                    it.telefone = edicaoTelefone.getText();
                    it.valor = edicaoValor.getText();
                    it.forma_de_atendimento = edicaoExame.getText();
                    it.especificacoes = edicaoEspecificacoes.getText();
                }
            }
            
            entradaValor.setVisible(true);
            entradaExame.setVisible(true);
            entradaMedico.setVisible(true);
            entradaCliente.setVisible(true);
            entradaTelefone.setVisible(true);
            entradaEspecificacoes.setVisible(true);
            
            botaoEditar.setText("Editar");
        }
    }
    
    public void iniciar(String referencia, String paciente, String telefone, String data, String equipamento, String medico, String horario, String especificacoes, String valor, String forma_de_atendimento) {
        entradaReferencia.setText(referencia);
        entradaCliente.setText(paciente);
        entradaMedico.setText(medico);
        entradaTelefone.setText(telefone);
        entradaValor.setText(valor);
        entradaEquipamento.setText(equipamento);
        entradaExame.setText(forma_de_atendimento);
        entradaData.setText(data);
        entradaHorario.setText(horario);
        entradaEspecificacoes.setText(especificacoes);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        edicaoValor.setVisible(false);
        edicaoExame.setVisible(false);
        edicaoMedico.setVisible(false);
        edicaoCliente.setVisible(false);
        edicaoTelefone.setVisible(false);
        edicaoEspecificacoes.setVisible(false);
    }   
    
}

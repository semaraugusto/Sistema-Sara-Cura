/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drapo.dashboard;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author oXCToo
 */
public class HomeController implements Initializable {    
    
    public static List<Medico> medicos;
    public static List<Equipamento> equipamentos;
    public static List<Consulta> consultas;
    public static List<Exame> exames;
    public static JSON dadosMedicos;
    public static JSON dadosConsultas;
    public static JSON dadosExames;
    
    ObservableList<String> choicebox_hora_lista = FXCollections.observableArrayList("01", "02", "03");
    
    @FXML
    private Label label;
    
    @FXML
    private VBox pnl_scroll;
    
    @FXML
    private final ChoiceBox choicebox_hora = new ChoiceBox(FXCollections.observableArrayList("01", "02", "03"));
    
    @FXML
    private void painel_agendar_consulta(ActionEvent event) {            
        agendar_consulta();
    }
    @FXML
    private void painel_agendar_exame(ActionEvent event) {            
        agendar_exame();
    }
    @FXML
    private void painel_pesquisar_consulta(ActionEvent event) {            
        atualizar_consultas();
    }
    @FXML
    private void painel_pesquisar_exame(ActionEvent event) {            
        atualizar_exames();
    }
    @FXML
    private void painel_cadastrar_medico(ActionEvent event) {            
        cadastrar_medico();
    }
    @FXML
    private void painel_cadastrar_equipamento(ActionEvent event) {            
        cadastrar_equipamento();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        medicos = new ArrayList<>();
        equipamentos = new ArrayList<>();
        consultas = new ArrayList<>();
        exames = new ArrayList<>();
        JSONParser parser = new JSONParser();
        
            try {
                JSONArray ex = (JSONArray) parser.parse(new FileReader("exames.json"));
                for(Object o : ex){
                    JSONObject exame = (JSONObject) o;
                    Exame exameTemp = new Exame();
                    exameTemp.data = (String) exame.get("data");
                    exameTemp.equipamento = (String) exame.get("equipamento");
                    exameTemp.especificacoes = (String) exame.get("especificacoes");
                    exameTemp.forma_de_atendimento = (String) exame.get("forma_de_atendimento");
                    exameTemp.horario = (String) exame.get("horario");
                    exameTemp.medico = (String) exame.get("medico");
                    exameTemp.paciente = (String) exame.get("paciente");
                    exameTemp.ref = (String) exame.get("ref");
                    exameTemp.telefone = (String) exame.get("telefone");
                    exameTemp.valor = (String) exame.get("valor");
                    exames.add(exameTemp);
                }
                
                JSONArray cons = (JSONArray) parser.parse(new FileReader("consultas.json"));
                int i = 1;
                for(Object o : cons){
                    JSONObject consulta = (JSONObject) o;
                    Consulta provisoria = new Consulta();
                    provisoria.data = (String) consulta.get("data");
                    provisoria.medico = (String) consulta.get("medico");
                    provisoria.ref = "Ref: " + String.valueOf(i);
                    provisoria.forma_de_atendimento = (String) consulta.get("forma_de_atendimento");
                    provisoria.paciente = (String) consulta.get("paciente");
                    provisoria.horario = (String) consulta.get("horario");
                    provisoria.valor = (String) consulta.get("valor");
                    provisoria.telefone = (String) consulta.get("telefone");
                    consultas.add(provisoria);
                    i++;
                }
                
                JSONArray geral = (JSONArray) parser.parse(new FileReader("medicos.json"));
                for(Object o : geral)
                {   
                    Medico provisorio = new Medico();
                    JSONObject medico = (JSONObject) o;
                    
                    provisorio.nome = (String) medico.get("nome");
                    provisorio.horario = (int)(long) medico.get("horario");
                    provisorio.tempo = (int)(long) medico.get("tempo");
                    JSONArray dias = (JSONArray) medico.get("dias");
                    i = 0;
                    for(Object d : dias){
                        provisorio.dias[i] = Boolean.valueOf(d.toString());
                        i++;
                    }
                    
                    JSONArray especialidades = (JSONArray) medico.get("especialidades");
                    
                    for(Object e : especialidades){
                        provisorio.especialidades.add((String)e);
                    }
                    
                    JSONArray agenda = (JSONArray)medico.get("agenda");
                    i = 0;
                    for(Object a : agenda){
                         DiaTrabalho dt = new DiaTrabalho(provisorio.tempo);
                         JSONObject teste = (JSONObject) a;
                         JSONArray horarios = (JSONArray) teste.get("horarios");
                         for(int j = 0; j<6; j++){
                             JSONArray h = (JSONArray) horarios.get(j);
                             for(int k = 0; k < (60/provisorio.tempo); k++){
                                 dt.horarios[j][k] = Boolean.valueOf(h.get(k).toString());
                             }
                         }
                         dt.data = (String) teste.get("data");
                         provisorio.agenda.add(dt);              
                    }
                   
                    medicos.add(provisorio);
                }
            
            } catch (IOException | ParseException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            dadosMedicos = new JSON("medicos.json");
            dadosConsultas = new JSON("consultas.json");
            dadosExames = new JSON("exames.json");
    }
    
    private void atualizar_consultas()
    {
        pnl_scroll.getChildren().clear();
        
        Node node = null;
        
        for(Consulta it : HomeController.consultas){
            try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("Consulta.fxml"));
               node = (Node)loader.load();
               ConsultaController controller = loader.getController();
               controller.iniciar(it.ref, it.paciente, it.telefone, it.medico, it.valor, it.forma_de_atendimento, it.data, it.horario);
               pnl_scroll.getChildren().add(node);
                
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
    private void atualizar_exames()
    {
        pnl_scroll.getChildren().clear();
        
        Node node = null;
        
        for(Exame it : HomeController.exames){
            try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("Exame.fxml"));
               node = (Node)loader.load();
               ExameController controller = loader.getController();
               controller.iniciar(it.ref, it.paciente, it.telefone, it.data, it.equipamento, it.medico, it.horario, it.especificacoes, it.valor, it.forma_de_atendimento);
               pnl_scroll.getChildren().add(node);
                
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
    
    private void cadastrar_medico()
    {
        pnl_scroll.getChildren().clear();
        
        Node node;
        try {
            node = (Node)FXMLLoader.load(getClass().getResource("Cadastrar_Medico.fxml"));
            pnl_scroll.getChildren().add(node);
                
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cadastrar_equipamento()
    {
        pnl_scroll.getChildren().clear();
        
        Node node;
        try {
            node = (Node)FXMLLoader.load(getClass().getResource("Cadastrar_Equipamento.fxml"));
            pnl_scroll.getChildren().add(node);
                
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void agendar_consulta()
    {
        pnl_scroll.getChildren().clear();
        
        Node node;
        try {
            node = (Node)FXMLLoader.load(getClass().getResource("Agendar_Consulta.fxml"));
            pnl_scroll.getChildren().add(node);
                
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void agendar_exame()
    {
        pnl_scroll.getChildren().clear();
        
        Node node;
        try {
            node = (Node)FXMLLoader.load(getClass().getResource("Agendar_Exame.fxml"));
            pnl_scroll.getChildren().add(node);
                
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

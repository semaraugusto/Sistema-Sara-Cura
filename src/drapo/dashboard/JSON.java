package drapo.dashboard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author Henrique
 */
public class JSON {    
    public JSONParser parser;
    public FileWriter arquivo;
    JSONObject read;
    JSONObject write;
    JSONArray saida = new JSONArray();
    public JSON(String nomeArq) {
        File f = new File(nomeArq);
        if(f.exists()){
            try {
                arquivo = new FileWriter(f,false);
            } catch (IOException ex) {
                Logger.getLogger(JSON.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                arquivo = new FileWriter(nomeArq);
            } catch (IOException ex) {
                Logger.getLogger(JSON.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void salvaMedico(Medico med){
        this.write = new JSONObject();

        JSONArray listEspecialidades = new JSONArray();
        for(String especialidade : med.especialidades)
            listEspecialidades.add(especialidade);
        this.write.put("especialidades", listEspecialidades);

        this.write.put("tempo", med.tempo);

        this.write.put("horario", med.horario);

        JSONArray listDias = new JSONArray();
        for(boolean dia : med.dias)
            listDias.add(dia);
        this.write.put("dias", listDias);
        
        this.write.put("nome", med.nome);
        
        JSONArray agenda = new JSONArray();
        for(DiaTrabalho dia : med.agenda){
            JSONObject diaObj = new JSONObject();
            diaObj.put("data", dia.data);
            JSONArray hora = new JSONArray();
            for(boolean horaArr[] : dia.horarios){
                JSONArray mins = new JSONArray();
                for(boolean intervalo : horaArr){
                    mins.add(intervalo);
                }
                hora.add(mins);
            }
            diaObj.put("horarios", hora);
            agenda.add(diaObj);
        }
        this.write.put("agenda", agenda);
        
        this.saida.add(this.write);
    }
    
    
    public void salvaConsulta(Consulta consulta){
        this.write = new JSONObject();
        this.write.put("ref", consulta.ref);
        this.write.put("data", consulta.data);
        this.write.put("forma_de_atendimento", consulta.forma_de_atendimento);
        this.write.put("horario", consulta.horario);
        this.write.put("medico", consulta.medico);
        this.write.put("paciente", consulta.paciente);
        this.write.put("telefone", consulta.telefone);
        this.write.put("valor", consulta.valor);
        this.saida.add(this.write);
    }

    
    public void salvaExame(Exame exame){
        this.write = new JSONObject();
        this.write.put("data", exame.data);
        this.write.put("equipamento", exame.equipamento);
        this.write.put("especificacoes", exame.especificacoes);
        this.write.put("forma_de_atendimento", exame.forma_de_atendimento);
        this.write.put("horario", exame.horario);
        this.write.put("medico", exame.medico);
        this.write.put("paciente", exame.paciente);
        this.write.put("ref", exame.ref);
        this.write.put("telefone", exame.telefone);
        this.write.put("valor", exame.valor);
        this.saida.add(this.write);

    }


    
     public void salvaEquipamento(Equipamento equip){
        this.write = new JSONObject();
        this.write.put("data", equip.equipamento);
        this.write.put("equipamento", equip.especialidade);
        this.write.put("especificacoes", equip.horario_de_funcionamento);
        this.write.put("forma_de_atendimento", equip.status);
        this.write.put("horario", equip.tempo);
        
        this.saida.add(this.write);

    }
     
    public void escreve(){
        try{
            this.arquivo.write(this.saida.toString());
            this.arquivo.flush();
        } catch (IOException ex) {
            Logger.getLogger(JSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
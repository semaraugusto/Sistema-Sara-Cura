/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drapo.dashboard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
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
                arquivo = new FileWriter(f,true);
            } catch (IOException ex) {
                Logger.getLogger(JSON.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("seilaoq");
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
    
    public void escreveMedicos(){
        try{
            this.arquivo.write(this.saida.toString());
            this.arquivo.flush();
        } catch (IOException ex) {
            Logger.getLogger(JSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

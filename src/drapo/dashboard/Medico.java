/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drapo.dashboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Henrique
 */
public class Medico {    
    boolean dias[];
    int tempo, horario;
    String nome;
    List<String> especialidades;
    List<DiaTrabalho> agenda;
    
    Medico(int tempo, int horario, boolean dias[], String nome, List<String> especialidades){
        this.tempo = tempo;
        this.horario = horario;
        this.dias = dias;
        this.nome = nome;
        this.especialidades = especialidades;
        this.agenda = new ArrayList<>();
    }

    public void marcaConsulta(String data, String hora, String min){
        String arrMin15[] = {"00", "15", "30", "45"};
        String arrMin20[] = {"00", "20", "40"};
        String arrMin30[] = {"00", "30"};
        String arrHora[] = {"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"};
        int indexHora=0, indexMin=0, horaAtual, minAtual;
        boolean dataNaoUsada = true;
        
        if(this.tempo == 15){
            minAtual = 0;
            for(String imin : arrMin15){
                if(Objects.equals(imin, min))
                    indexMin = minAtual;
                minAtual++;
            }
        }else if(this.tempo == 20){
            minAtual = 0;
            for(String imin : arrMin20){
                if(Objects.equals(imin, min))
                    indexMin = minAtual;
                minAtual++;
            }
        }else{
            minAtual = 0;
            for(String imin : arrMin30){
                if(Objects.equals(imin, min))
                    indexMin = minAtual;
                minAtual++;
            }
        }
        
        horaAtual = 0;
        for(String ihora : arrHora){
            if(Objects.equals(ihora, hora))
                indexHora = horaAtual - this.horario;
            horaAtual++;
        }
        
        for(DiaTrabalho dia : this.agenda){
            if(Objects.equals(dia.data, data)){
                dia.horarios[indexHora][indexMin] = true;
                dataNaoUsada = false;
            }
        }
        if(dataNaoUsada){
            DiaTrabalho e = new DiaTrabalho(this.tempo);
            e.data = data;
            e.horarios[indexHora][indexMin] = true;
            this.agenda.add(e);
        }
        System.out.println(indexHora);
        System.out.println(this.horario);
    }
    
    @Override
    public String toString(){
        String outString = "";
        
        outString += "Nome: " + this.nome + ", Especialidades: ";
        
        for(String item : this.especialidades)
            outString += item + ", ";
        
        outString += "Tempo: " + this.tempo + ", ";
        
        outString += "Horario: " + this.horario + ", Dias: ";
        
        for(boolean dia : this.dias)
            outString += dia + ", ";
        
        return outString;
    } 
}

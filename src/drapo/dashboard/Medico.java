/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drapo.dashboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class Medico {
    public class DiaTrabalho {
        String data;
        boolean horarios[];
    }
    
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

    public boolean marcaConsulta(){
        System.out.println("marcaConsulta");
        return true;
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

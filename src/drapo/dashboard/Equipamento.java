package drapo.dashboard;

/**
 * @author Samuel Aguiar
 */
public class Equipamento {
    String equipamento;
    String especialidade;
    String tempo;
    String horario_de_funcionamento;
    String status;

    
    Equipamento(){};
    Equipamento(String equipamento, String especialidade, String tempo, String horario_de_funcionamento, String status){
        this.equipamento = equipamento;
        this.especialidade = especialidade;
        this.tempo = tempo;
        this.horario_de_funcionamento = horario_de_funcionamento;
        this.status = status;
    }
}
package drapo.dashboard;

/**
 * @author Samuel Aguiar
 */
public class Exame {
    static int id = 0;
    String ref;
    String paciente;
    String telefone;
    String data;
    String equipamento;
    String medico;
    String horario;
    String especificacoes;
    String valor;
    String forma_de_atendimento;
    
    Exame(){}
    
    Exame(String paciente, String telefone, String data, String equipamento, String medico, String horario, String especificacoes, String valor, String forma_de_atendimento){
        Exame.id++;
        this.ref = "Ref: " + String.valueOf(HomeController.counter2);
        HomeController.counter2++;
        this.paciente = paciente;
        this.telefone = telefone;
        this.data = data;
        this.equipamento = equipamento;
        this.medico = medico;
        this.horario = horario;
        this.especificacoes = especificacoes;
        this.valor = valor;
        this.forma_de_atendimento = forma_de_atendimento;
    }
}
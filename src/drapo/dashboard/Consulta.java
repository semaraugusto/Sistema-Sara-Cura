package drapo.dashboard;

/**
 *
 * @author Samuel Aguiar
 */
public class Consulta {
    static int id = 0;
    String ref;
    String paciente;
    String telefone;
    String medico;
    String valor;
    String forma_de_atendimento;
    String data;
    String horario;
    
    
    Consulta(String paciente, String telefone, String medico, String valor, String forma_de_atendimento, String data, String horario){
        Consulta.id++;
        this.ref = "Ref: " + String.valueOf(Consulta.id);
        this.paciente = paciente;
        this.telefone = telefone;
        this.medico = medico;
        this.valor = valor;
        this.forma_de_atendimento = forma_de_atendimento;
        this.data = data;
        this.horario = horario;
    }
}
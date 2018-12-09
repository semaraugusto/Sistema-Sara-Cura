package drapo.dashboard;

/**
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
    
    Consulta(){}
    
    Consulta(String paciente, String telefone, String medico, String valor, String forma_de_atendimento, String data, String horario){
        this.ref = "Ref: " + String.valueOf(HomeController.counter);
        HomeController.counter++;
        this.paciente = paciente;
        this.telefone = telefone;
        this.medico = medico;
        this.valor = valor;
        this.forma_de_atendimento = forma_de_atendimento;
        this.data = data;
        this.horario = horario;
    }
}
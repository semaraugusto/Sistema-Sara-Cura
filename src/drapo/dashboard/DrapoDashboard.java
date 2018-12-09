package drapo.dashboard;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class DrapoDashboard extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event1) {
                for(Medico med : HomeController.medicos)
                    HomeController.dadosMedicos.salvaMedico(med);
                HomeController.dadosMedicos.escreve();
                for(Consulta c : HomeController.consultas){
                    HomeController.dadosConsultas.salvaConsulta(c);
                }
                HomeController.dadosConsultas.escreve();
                for(Exame e : HomeController.exames){
                    HomeController.dadosExames.salvaExame(e);
                }
                HomeController.dadosExames.escreve();
                for(Equipamento eqp : HomeController.equipamentos){
                    HomeController.dadosEquipamentos.salvaEquipamento(eqp);
                }
                HomeController.dadosEquipamentos.escreve();
            }
        });
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
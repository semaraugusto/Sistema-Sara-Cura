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
                HomeController.dadosMedicos.escreveMedicos();
                for(Consulta c : HomeController.consultas){
                    HomeController.dadosConsultas.salvaConsulta(c);
                }
                HomeController.dadosConsultas.escreveConsulta();
                for(Exame e : HomeController.exames){
                    HomeController.dadosExames.salvaExame(e);
                }
                HomeController.dadosExames.escreveExame();
            }
        });
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
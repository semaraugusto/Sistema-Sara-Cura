/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drapo.dashboard;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Henrique
 */
public class JSON {
    JSONParser parser = new JSONParser();
    
    JSONObject jsonMedicos;

    public JSON() {
        try {
            this.jsonMedicos = (JSONObject) parser.parse(new FileReader("dadosMedicos.json"));
            this.jsonMedicos.put("nome", "Helto Tosse");
            System.out.println(this.jsonMedicos.get("nome"));
        } catch (FileNotFoundException ex) {
            System.out.println("EXCEPTION1");
        } catch (IOException ex) {
            System.out.println("EXCEPTION2");
        } catch (ParseException ex) {
            System.out.println("EXCEPTION3");
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drapo.dashboard;

/**
 *
 * @author Henrique
 */
public class DiaTrabalho {
    String data;
    boolean horarios[][];
    
    DiaTrabalho(int tempo){
        this.horarios = new boolean[6][60/tempo];
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fisjava;

import net.sourceforge.jFuzzyLogic.FIS;

/**
 *
 * @author jnico
 */
public class FIS_system {
    private String path_FIS = "src/fisjava/source_FIS.fcl";
    private FIS fis_var;
    
    public FIS_system(){
        this.fis_var = FIS.load(this.path_FIS, true);
        
        if(this.fis_var == null){
            System.out.println("Ocurrio un error al cargar el fis");
        }
    }
    
    public double eval(double val_temp, double val_hora){
        this.fis_var.setVariable("temp_ambiente", val_temp);
        this.fis_var.setVariable("hora_dia", val_hora);
        
        this.fis_var.evaluate();
        
        return this.fis_var.getVariable("temp_ducha").getLatestDefuzzifiedValue();
    }
    
    public String get_CD(){
        String[] CD_salida = {"baja", "promedio", "generosa"};
        
        String CD_result = "";
        for(int i = 0; i < CD_salida.length; i++){
            if( this.fis_var.getVariable("temp_ducha").getMembership(CD_salida[i]) > 0 ){
                CD_result += CD_salida[i] + ",";
            }
        }
        
        return CD_result;
    }
}

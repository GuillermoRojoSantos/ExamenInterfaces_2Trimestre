package com.example.exameninterfaces.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.DecimalFormat;
import java.text.Format;

@Data
@AllArgsConstructor
public class Alumno {
    private String nombre;
    private String apellido;
    private Float AD;
    private Float SGE;
    private Float DI;
    private Float PMDM;
    private Float PSP;
    private Float EIE;
    private Float HLC;

    public Float getMedia(){
        var result = (this.AD+this.SGE+this.DI+this.PMDM+this.PSP+this.EIE+this.HLC)/7;
        Format format = new DecimalFormat("#.##");
        result= Float.parseFloat(format.format(result));
        return result;
    }
}

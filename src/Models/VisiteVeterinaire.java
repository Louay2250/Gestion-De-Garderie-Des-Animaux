/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Azertyy
 */
import java.util.Date;
    
public class VisiteVeterinaire {
    private Date dateVisite;
    private String notes;

    public VisiteVeterinaire(Date dateVisite, String notes) {
        this.dateVisite = dateVisite;
        this.notes = notes;
    }
    public Date getDateVisite() {
        return dateVisite;
    }   
   
}


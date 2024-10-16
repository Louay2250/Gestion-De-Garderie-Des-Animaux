/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;



/**
 *
 * @author Azertyy
 */
public abstract class Service implements Facturable {
    private String nom;
    private String description;
    private float tarif;

    public Service(String nom, String description,float tarif) {
        this.nom = nom;
        this.description = description;
        this.tarif=tarif;
    }

    public void setnom(String nom) {
        this.nom= nom;
    }

    public String getnom() {
        return nom;
    }
        public void setDescription(String description) {
        this.description= description;
    }

    public String getDescription() {
        return description;
    }
    public void settarif(float tarif){
        this.tarif=tarif;
    }
    public float gettarif(){
        return tarif;
    }

  

}


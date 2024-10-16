/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;


/**
 *
 * @author Azertyy
 */


public final class Personnel extends Personne {
    private String Poste;
    private float salaire;

    public Personnel(int CIN,String nom, String adresse, String telephone, String Poste, float salaire) {
        super(CIN,nom, adresse, telephone);
        this.Poste = Poste;
        this.salaire = salaire;
        
    }

    // Getters et setters
    public String getPoste() {
        return Poste;
    }

    public void setPoste(String Poste) {
        this.Poste = Poste;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

 
}

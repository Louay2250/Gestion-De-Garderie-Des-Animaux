/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Azertyy
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Animal {
    private String nom;
    private Date dateNaissance;
    private String Type;
    private Client proprietaire;
    private List<VisiteVeterinaire> visitesVeterinaires;


    public Animal(Client proprietaire,String nom, Date dateNaissance,String Type) {
        this.proprietaire=proprietaire;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.Type = Type;
        this.visitesVeterinaires=new ArrayList<>();
    }
    public void ajouterVisiteVeterinaire(VisiteVeterinaire visiteVeterinaire) {
        this.visitesVeterinaires.add(visiteVeterinaire);
    }

    public List<VisiteVeterinaire> getVisitesVeterinaires() {
        return visitesVeterinaires;
    }
    public Client getProprietaire() {
        return proprietaire;
    }

    public void setPrioritaire(Client proprietaire) {
        this.proprietaire = proprietaire;
    }


        public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
    public List<VisiteVeterinaire> getVisitesApresDate(Date date) {
        return visitesVeterinaires.stream()
                .filter(visite -> visite.getDateVisite().after(date))
                .collect(Collectors.toList());
    }



@Override
   public String toString() {
    return "Animal [nom=" + nom ;
}
   }
    




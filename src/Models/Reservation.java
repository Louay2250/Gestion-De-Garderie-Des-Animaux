/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Azertyy
 */
import Models.Client;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Reservation implements Facturable {
    private int idR;
    private Date dateDebut;
    private Date dateFin;
    private float prixParNuite;
    private Client client;
    private List<Service> servicesNecessaires;
    private Animal animal;
    private boolean estPayé;

    public Reservation(int idR,Date dateDebut, Date dateFin, Client client, Animal animal, float prixParNuite) {
       this.idR=idR;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.client = client;
        this.animal=animal;
        this.servicesNecessaires = new ArrayList<>();
        this.estPayé = false; // Par défaut, la réservation n'est pas payée lors de sa création.
        this.prixParNuite = prixParNuite;
    }

    // Getters et setters
    
    public float getPrixParNuite() {
        return prixParNuite;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }



    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    

    public List<Service> getServices() {
        return servicesNecessaires;
    }

    public void choisirService(Service service) {
        servicesNecessaires.add(service);
    }

    public boolean EstPayé() {
        return estPayé;
    }

    public void setEstPayé(boolean estPayé) {
        this.estPayé = estPayé;
    }

    @Override
    public float calculerMontantFacture() {
    float total = 0;

    // Calculate the duration between dateDebut and dateFin
    LocalDate localDateDebut = dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate localDateFin = dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    long nbJours = ChronoUnit.DAYS.between(localDateDebut, localDateFin);

    // Calculate the total amount
    for (Service service : servicesNecessaires) {
        total += service.gettarif();
    }
    total += prixParNuite * nbJours;

    return total;
}
        
}


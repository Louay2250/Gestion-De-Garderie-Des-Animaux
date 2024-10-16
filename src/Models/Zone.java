package Models;


import java.util.Date;

public record Zone(String nom, String emplacement, ZoneType type, int capacite) {

    public enum ZoneType {
        INDOOR, OUTDOOR
    }

    // Getters
    public String getNom() {
        return nom;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public ZoneType getType() {
        return type;
    }

    public int getCapacite() {
        return capacite;
    }

    // Check availability for a specific date range
    public boolean isAvailable(Date startDate, Date endDate) {
        // TODO: Implement logic to check existing reservations and determine availability
        return true; // Placeholder logic for now
    }

    // Calculate cost based on duration and number of occupants
    public float calculateCost(int numOccupants, Date startDate, Date endDate) {
        // TODO: Implement logic to calculate cost based on duration, occupants, and zone type
        return 0.0f; // Placeholder logic for now
    }

    @Override
    public String toString() {
        return "Zone: " + nom + " (" + type + ") - " + emplacement + " (Capacity: " + capacite + ")";
    }
}
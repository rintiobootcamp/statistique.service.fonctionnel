package helpers;

import com.bootcamp.entities.Projet;

public class ReturnMostViewProjects {
    private int idProjet;
    private String nom;
    private long nbrComments;
    private long nbrLikes;
    private double moyenneNbrs;

    public int getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(int idProjet) {
        this.idProjet = idProjet;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getNbrComments() {
        return nbrComments;
    }

    public void setNbrComments(long nbrComments) {
        this.nbrComments = nbrComments;
    }

    public long getNbrLikes() {
        return nbrLikes;
    }

    public void setNbrLikes(long nbrLikes) {
        this.nbrLikes = nbrLikes;
    }

    public double getMoyenneNbrs() {
        return moyenneNbrs;
    }

    public void setMoyenneNbrs(double moyenneNbrs) {
        this.moyenneNbrs = moyenneNbrs;
    }
}

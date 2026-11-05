package model;

public class Administrateur {

    private String login;
    private String motDePasse;

    public Administrateur(String login, String motDePasse) {
        this.login = login;
        this.motDePasse = motDePasse;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    // Authentifie l'administrateur en comparant login/motDePasse
    
    public boolean authentifier(String login, String motDePasse) {
        return this.login.equals(login) && this.motDePasse.equals(motDePasse);
    }

    @Override
    public String toString() {
        return "Administrateur [login=" + login + "]";
    }
}


package com.example.koktelmajstor;

public class Kartica {

    String naziv;
    String[] recept;
    String slika;

    public Kartica(String naziv, String[] recept, String slika){
        this.naziv = naziv;
        this.recept = recept;
        this.slika = slika;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String[] getRecept() {
        return recept;
    }

    public void setRecept(String[] recept) {
        this.recept = recept;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }
}

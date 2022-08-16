package com.example.koktelmajstor;

public class Kartica {

    private int id;
    private String naziv;
    private String recept;
    private String slika;
    private String tagovi;
    private boolean omiljen;


    public Kartica(){
    }

    public Kartica(String naziv, String recept, String slika, String tagovi, boolean omiljen) {
        this.naziv = naziv;
        this.recept = recept;
        this.slika = slika;
        this.tagovi = tagovi;
        this.omiljen = omiljen;
    }

    public Kartica(int id, String naziv, String recept, String slika, String tagovi, boolean omiljen) {
        this.id = id;
        this.naziv = naziv;
        this.recept = recept;
        this.slika = slika;
        this.tagovi = tagovi;
        this.omiljen = omiljen;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getRecept() {
        return recept;
    }

    public void setRecept(String recept) {
        this.recept = recept;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getTagovi() {
        return tagovi;
    }

    public void setTagovi(String tagovi) {
        this.tagovi = tagovi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOmiljen() {
        return omiljen;
    }

    public void setOmiljen(boolean omiljen) {
        this.omiljen = omiljen;
    }

    @Override
    public String toString() {
        return "Kartica{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", recept='" + recept + '\'' +
                ", slika='" + slika + '\'' +
                ", tagovi='" + tagovi + '\'' +
                ", omiljen=" + omiljen +
                '}';
    }
}

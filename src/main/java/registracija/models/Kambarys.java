package registracija.models;

import java.util.ArrayList;

public class Kambarys {


    private int kambario_numeris;        //kambario numeris
    private Svecias priregistruotasSvecias;     //prie kambario priregistruotas svecias jei tokio nėra reikšmė null
    private ArrayList<Svecias> registracijuIstorija;

    public Kambarys() {
        this.registracijuIstorija = new ArrayList<Svecias>();
    }
    public Kambarys(int kambario_numeris) {
        this.kambario_numeris = kambario_numeris;
        this.registracijuIstorija = new ArrayList<Svecias>();
    }
    public Kambarys(int id, Svecias sveciasUzsakesKambari, ArrayList<Svecias> registracijuIstorija) {
        this.kambario_numeris = id;
        this.priregistruotasSvecias = sveciasUzsakesKambari;
        this.registracijuIstorija = registracijuIstorija;
    }

    public boolean arLaisvas() {
        // patikrina ar kambarys siuo metu yra laisvas(true) ar jame yra svecias(false)
        boolean rezultatas = true;

        if (this.priregistruotasSvecias != null) {
            rezultatas = false;
        }

        return rezultatas;
    }

    public void pridetiSveciaPrieIstorijos(Svecias svecias){
        this.registracijuIstorija.add(svecias);
    }


//geteriai ir seteriai
    public int getKambario_id() {
        return kambario_numeris;
    }

    public void setKambario_id(int kambario_id) {
        this.kambario_numeris = kambario_id;
    }

    public Svecias getPriregistruotasSvecias() {
        return priregistruotasSvecias;
    }

    public void setPriregistruotasSvecias(Svecias priregistruotasSvecias) {
        this.priregistruotasSvecias = priregistruotasSvecias;
    }

    public ArrayList<Svecias> getRegistracijuIstorija() {
        return registracijuIstorija;
    }

    public void setRegistracijuIstorija(ArrayList<Svecias> registracijuIstorija) {
        this.registracijuIstorija = registracijuIstorija;
    }
}

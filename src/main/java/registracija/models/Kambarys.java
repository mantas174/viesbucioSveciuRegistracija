package registracija.models;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "kambariai")
public class Kambarys {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "kambario_numeris")
    private int kambarioNumeris;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priregistruoto_svecio_id")
    //prie kambario priregistruotas svecias jei tokio nėra reikšmė null
    private Svecias priregistruotasSvecias;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "kambarysKuriamePriregistruotas")
    private List<Svecias> registracijuIstorija;

    public Kambarys() {

    }
    public Kambarys(int numeris) {
        this.kambarioNumeris = numeris;
    }

    public Kambarys(int numeris, Svecias sveciasUzsakesKambari, List<Svecias> registracijuIstorija) {
       this.kambarioNumeris = numeris;
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

    public void surikiuotiRegistracijuIstorija() {
        Svecias temp;    //kintamasis reikšmėms sukeisti
        if (this.registracijuIstorija.isEmpty()) {
            for (int i = 0; i < this.registracijuIstorija.size() - 1; i++) {
                for (int j = i + 1; j < this.registracijuIstorija.size(); j++) {
                    if (this.registracijuIstorija.get(i).getSvecioId() > this.registracijuIstorija.get(j).getSvecioId()) {
                        temp = this.registracijuIstorija.get(i);
                        this.registracijuIstorija.set(i, this.registracijuIstorija.get(j));
                        this.registracijuIstorija.set(j, temp);
                    }
                }
            }
        }
    }




//geteriai ir seteriai
    public Svecias getPriregistruotasSvecias() {
        return priregistruotasSvecias;
    }

    public void setPriregistruotasSvecias(Svecias priregistruotasSvecias) {
        this.priregistruotasSvecias = priregistruotasSvecias;
    }


    public List<Svecias> getRegistracijuIstorija() {
        return registracijuIstorija;
    }

    public void setRegistracijuIstorija(List<Svecias> registracijuIstorija) {
        this.registracijuIstorija = registracijuIstorija;
    }

    public int getKambarioNumeris() {
        return kambarioNumeris;
    }

    public void setKambarioNumeris(int kambarioNumeris) {
        this.kambarioNumeris = kambarioNumeris;
    }
}

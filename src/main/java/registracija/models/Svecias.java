package registracija.models;

import javax.persistence.*;

@Entity
@Table(name = "sveciai")
public class Svecias {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int svecioId;

    @Column
    private String vardas;

    @Column
    private String pavarde;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kambario_numeris", nullable = false)
    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "priregistruotasSvecias")
    private Kambarys kambarysKuriamePriregistruotas;


    public Svecias() {
    }

    public Svecias(int id, String vardas, String pavarde) {
        this.svecioId = id;
        this.vardas = vardas;
        this.pavarde = pavarde;
    }

    @Override
    public String toString() {
        return "Svecio id: " + this.svecioId + " vardas: " + this.vardas + " pavardė: " + this.pavarde;
    }

    public boolean arLygus(Svecias lyginamasSvecias) {
        boolean arLygus = false;

        if (this.vardas.equals(lyginamasSvecias.getVardas()) &&
                this.pavarde.equals(lyginamasSvecias.getPavarde())) {
            arLygus = true;
        }
        return arLygus;
    }

    public boolean arNetuscias() {
        //ar svečio duomenys nėra tušti

        //iš svečio vardo ir pavardės pradžios ir pabaigos panaikinami tarpai jei ivesti vien tik tarpai lieka tuscia eilute
        tarpuPanaikinimas();
        if (!this.vardas.isEmpty() && !this.pavarde.isEmpty()) {
            return true;
        }
        return false;
    }


    public boolean arTeisingaiIvestas() {
        //ar svečio duomenys nėra kalidingai įvesti

        //ar svečio duomenys nėra tušti, neturi skaičių, neturi simbolių
        if(arNetuscias()) {
            if (this.vardas.matches("^[ A-Za-ząčęėįšųūžĄČĘĖĮŠŲŪŽ]+$") &&
                    this.pavarde.matches("^[A-Za-ząčęėįšųūžĄČĘĖĮŠŲŪŽ]+$")) {
                return true;
            }
        }
        return false;
    }
    public void tarpuPanaikinimas( ){
        //iš svečio vardo ir pavardės pradžios panaikinami tarpai jei yra vien tik tarpai lieka tuscia eilute

        this.vardas = this.vardas.trim();
        this.pavarde = this.pavarde.trim();
    }


    //geteriai ir seteriai
    public int getSvecioId() {
        return svecioId;
    }

    public void setSvecioId(int svecioId) {
        this.svecioId = svecioId;
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getPavarde() {
        return pavarde;
    }

    public void setPavarde(String pavarde) {
        this.pavarde = pavarde;
    }

    public Kambarys getKambarysKuriamePriregistruotas() {
        return kambarysKuriamePriregistruotas;
    }

    public void setKambarysKuriamePriregistruotas(Kambarys kambarysKuriamePriregistruotas) {
        this.kambarysKuriamePriregistruotas = kambarysKuriamePriregistruotas;
    }
}

package registracija.models;

public class Svecias {

    private int svecio_id;
    private String vardas;
    private String pavarde;


    public int getSvecio_id() {
        return svecio_id;
    }

    public void setSvecio_id(int svecio_id) {
        this.svecio_id = svecio_id;
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

    public Svecias() {
    }

    public Svecias(int id, String vardas, String pavarde) {
        this.svecio_id = id;
        this.vardas = vardas;
        this.pavarde = pavarde;
    }

    @Override
    public String toString() {
        return "Svecio id: " + this.svecio_id + " vardas: " + this.vardas + " pavardė: " + this.pavarde;
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
        if (!this.vardas.isEmpty() && !this.pavarde.isEmpty()) {

//            for(int i = 0; i <)
            return true;
        }
        return false;
    }

    public boolean arNeturiSkaiciu() {
        //ar svečio duomenyse nėra skaičių


        return true;
    }

    public boolean arTeisingaiIvestas() {
        //ar svečio duomenys nėra kalidingai įvesti

        //ar svečio duomenys nėra tušti, neturi skaičių, neturi simbolių
        if(arNetuscias()) {
            if (this.vardas.matches("^[ A-Za-z]+$") &&
                    this.pavarde.matches("^[A-Za-z]+$")) {
                return false;
            }
            else {
                return true;
            }
        }
        return false;
    }
}

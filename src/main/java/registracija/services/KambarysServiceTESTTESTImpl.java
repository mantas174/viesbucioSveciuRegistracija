package registracija.services;

import org.springframework.stereotype.Service;
import registracija.models.Kambarys;
import registracija.models.Svecias;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class KambarysServiceTESTTESTImpl implements KambarysServiceTEST {
    private ArrayList<Kambarys> viesbucioKambariai = new ArrayList<Kambarys>() {{

        Svecias svecias = new Svecias(1, "Mantas", "Muzikevičius");
        ArrayList<Svecias> registracijuIstorija = new ArrayList<>();
        registracijuIstorija.add(svecias);
        add(new Kambarys(1, svecias, registracijuIstorija));

        Svecias svecias2 = new Svecias(2, "Mantas2", "Muzikevičius");
        Svecias svecias3 = new Svecias(3, "Mantas3", "Muzikevičius");
        registracijuIstorija.add(svecias2);
        registracijuIstorija.add(svecias3);
        add(new Kambarys(2, null, registracijuIstorija));
    }};


    @Override
    public Kambarys redaguotiKambari(Kambarys kambarys) {
        for (int i = 0; i < this.viesbucioKambariai.size(); i++) {
            if (this.viesbucioKambariai.get(i).getKambario_id() == kambarys.getKambario_id()) {
                this.viesbucioKambariai.set(i, kambarys);
            }
        }
        return kambarys;
    }

    @Override
    public Kambarys tusciasKambarys() {
        for (int i = 0; i < this.viesbucioKambariai.size(); i++) {
            if (this.viesbucioKambariai.get(i).arLaisvas()) {
                //ar svčio duomenys nėra tušti
                return viesbucioKambariai.get(i);
            }
        }
        return null;
    }


    @Override
    public Kambarys rastKambariPagalSvecia(Svecias ieskomasSvecias) {
        for (int i = 0; i < this.viesbucioKambariai.size(); i++) {
            //ieškomas kambarys kur priregistruotas svečias būtų toks pat kaip ieškomas svečias
            //radus gražianamas kambarys

            if (this.viesbucioKambariai.get(i).getPriregistruotasSvecias().arLygus(ieskomasSvecias)) {
                return viesbucioKambariai.get(i);
            }

        }
        return null;
    }


    @Override
    public Kambarys surastPagalNumeri(int kambarioNumeris) {
        return this.viesbucioKambariai.stream().
                filter(p -> Objects.equals(p.getKambario_id(), kambarioNumeris))
                .findFirst().orElse(null);
    }

    @Override
    public ArrayList<Kambarys> paimtUzimtusKambarius() {
        ArrayList<Kambarys> rezultatas = new ArrayList<Kambarys>();
        for (int i = 0; i < this.viesbucioKambariai.size(); i++) {
            if (viesbucioKambariai.get(i).getPriregistruotasSvecias() != null) {
                rezultatas.add(viesbucioKambariai.get(i));
            }
        }
        return rezultatas;
    }

    @Override
    public ArrayList<Kambarys> paimtVisusKambarius() {
        return this.viesbucioKambariai;
    }


}

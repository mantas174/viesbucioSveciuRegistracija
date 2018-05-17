package registracija.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registracija.models.Kambarys;
import registracija.models.Svecias;
import registracija.repositories.KambarysRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class KambarysServiceImpl implements KambarysService {
    public static final int PIRMAS_RASTAS_TUSCIAS_KAMBARYS = 0;


    @Autowired
    private KambarysRepository kambarysRepository;


    @Override
    public Kambarys tusciasKambarys() {

        List<Kambarys> visiTusti = this.kambarysRepository.paimtTusciusKambarius();
        return visiTusti.get(PIRMAS_RASTAS_TUSCIAS_KAMBARYS);
    }

    @Override
    public List<Kambarys> paimtUzimtusKambarius() {
        return kambarysRepository.paimtUzimtusKambarius();
    }

    @Override
    public int kambariuSkaicius() {
        return kambarysRepository.findAll().size();
    }

    @Override
    public List<Kambarys> paimtVisusKambarius() {
        return this.kambarysRepository.visiSurikiuoti();
    }

    @Override
    public Kambarys surastPagalNumeri(int kambarioNumeris) {
        return this.kambarysRepository.findOne(kambarioNumeris);
    }

    @Override
    public Kambarys rastKambariPagalSvecia(Svecias ieskomasSvecias) {
        List<Kambarys> uzimtiKambariai = paimtVisusKambarius();

        for (int i = 0; i < uzimtiKambariai.size(); i++) {
            if (uzimtiKambariai.get(i).getPriregistruotasSvecias() != null)
                if (uzimtiKambariai.get(i).getPriregistruotasSvecias().arLygus(ieskomasSvecias)) {
                    return uzimtiKambariai.get(i);
                }
        }
        return null;
    }

    @Override
    public void atnaujintiKambarioDuomenis(Kambarys kambarys) {
        this.kambarysRepository.save(kambarys);
    }
}

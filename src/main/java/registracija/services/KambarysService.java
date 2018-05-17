package registracija.services;

import registracija.models.Kambarys;
import registracija.models.Svecias;

import java.util.ArrayList;
import java.util.List;

public interface KambarysService {
    void atnaujintiKambarioDuomenis(Kambarys kambarys);
    Kambarys tusciasKambarys();

    Kambarys rastKambariPagalSvecia(Svecias ieskomasSvecias);

    List<Kambarys> paimtUzimtusKambarius();

    List<Kambarys> paimtVisusKambarius();
    Kambarys surastPagalNumeri(int kambarioNumeris);

    int kambariuSkaicius();

}

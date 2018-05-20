package registracija.services;

import registracija.models.Kambarys;
import registracija.models.Svecias;

import java.util.ArrayList;

public interface KambarysServiceTEST {
    ArrayList<Kambarys> paimtVisusKambarius();
    ArrayList<Kambarys> paimtUzimtusKambarius();
    Kambarys surastPagalNumeri(int kambarioNumeris);
    Kambarys redaguotiKambari(Kambarys kambarys);


    Kambarys tusciasKambarys();
    Kambarys rastKambariPagalSvecia(Svecias ieskomasSvecias);



}

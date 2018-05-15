package registracija.services;

import registracija.models.Svecias;

public interface SvecioService {
    void irasykNaujaSvecia(Svecias svecias);
    Svecias suraskSveciaPagalId(int id);

}

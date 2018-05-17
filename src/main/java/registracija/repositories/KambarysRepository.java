package registracija.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import registracija.models.Kambarys;
import registracija.models.Svecias;

import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface KambarysRepository extends JpaRepository<Kambarys, Integer> {

    @Query("SELECT k FROM Kambarys k ORDER BY k.kambarioNumeris ASC")
    List<Kambarys> visiSurikiuoti();

    @Query("SELECT k FROM Kambarys k WHERE k.priregistruotasSvecias IS NULL")
    List<Kambarys> paimtTusciusKambarius();

    @Query("SELECT k FROM Kambarys k WHERE k.priregistruotasSvecias IS NOT NULL ORDER BY k.kambarioNumeris ASC")
    List<Kambarys> paimtUzimtusKambarius();



}

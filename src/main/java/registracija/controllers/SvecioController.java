package registracija.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import registracija.models.Kambarys;
import registracija.models.Svecias;
import registracija.services.KambarysService;


@Controller
public class SvecioController {
    public static final Svecias ISREGISTRUOJAMAS_SVECIAS = null;


    @Autowired
    private KambarysService kambarysService;

    @GetMapping("/svecias/registracija")
    public String svecioRegistracijaForma(Model model) {
        //model objektui pateikiamas nauajs svečio šablonas, svečio objekto
        //laukai (vardas, pavarde) bus naudojami informacijai užpildyti iš formos
        model.addAttribute("svecias", new Svecias());
        return "/svecias/registracija";
    }

    @PostMapping("/svecias/registracija")
    public String svecioRegistracijaPateikimas(@ModelAttribute Svecias registruojamasSvecias, Model model) {
        Kambarys tusciasKambarys;
        String nepavyko;
        String pavyko;

        if (registruojamasSvecias.arTeisingaiIvestas()) {
            try {
                //svecio registracija

                //"null" jei tuščio kambario nėra
                tusciasKambarys = kambarysService.tusciasKambarys();

                //nurodoma kuriame kambaryje(numeris) bus priregistruotas svečias
                registruojamasSvecias.setKambarysKuriamePriregistruotas(tusciasKambarys);

                //į rastą tuščią kambarį idedama naujo svečio informacija
                tusciasKambarys.setPriregistruotasSvecias(registruojamasSvecias);
                tusciasKambarys.pridetiSveciaPrieIstorijos(registruojamasSvecias);

                //įrašomi nauji kambario duomenys į duomenų bazę
                kambarysService.atnaujintiKambarioDuomenis(tusciasKambarys);

                //jeigu registracija sekminga sugeneruojama žinutė
                pavyko = "Svečias užregistruotas į " + tusciasKambarys.getKambarioNumeris() + " kambary.";
                model.addAttribute("pavyko", pavyko);
            } catch (IndexOutOfBoundsException klaida){
                // tuščio kambario nėra
                nepavyko = "Šiuo metu lasvų kambarių nėra.";
                model.addAttribute("nepavyko", nepavyko);
            }
        } else {
            nepavyko = "Neteisingai įvesti duomenys.";
            model.addAttribute("nepavyko", nepavyko);
        }
        return "/svecias/registracija";
    }


    @GetMapping("/svecias/isregistravimas")
    public String svecioIsregistravimoForma(Model model) {
        model.addAttribute("svecias", new Svecias());
        return "/svecias/isregistravimas";
    }

    @PostMapping("/svecias/isregistravimas")
    public String svecioIsregistravimoPateikimas(@ModelAttribute Svecias isregistruojamasSvecias, Model model) {
        Kambarys rastasKambarys;
        String nepavyko;
        String pavyko;

        if (isregistruojamasSvecias.arTeisingaiIvestas()) {
            try {
                //svecio isregistravimas

                //null jei tokio svečio nėra
                rastasKambarys = kambarysService.rastKambariPagalSvecia(isregistruojamasSvecias);

                //kambaryje kuriame buvo rastas svečias, priregistruotas svečias yra ištrinamas
                rastasKambarys.setPriregistruotasSvecias(ISREGISTRUOJAMAS_SVECIAS);

                //įrašomi nauji kambario duomenys į duomenų bazę
                kambarysService.atnaujintiKambarioDuomenis(rastasKambarys);

                //jeigu registracija sekminga sugeneruojama žinutė
                pavyko = "Svečias išregistruotas iš " + rastasKambarys.getKambarioNumeris() + " kambario.";
                model.addAttribute("pavyko", pavyko);
            } catch (NullPointerException klaida) {
                // svečias su tokiu vardu ar pavarde nėra priregistruotas jokiam kambary

                nepavyko = "Svečias su tokiais duomenimis nėra priregistruotas jokiam kambaryje.";
                model.addAttribute("nepavyko", nepavyko);
            }
        }else {
            nepavyko = "Neteisingai įvesti duomenys.";
            model.addAttribute("nepavyko", nepavyko);
        }

        return "/svecias/isregistravimas";
    }


}

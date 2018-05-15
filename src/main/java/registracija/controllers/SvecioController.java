package registracija.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import registracija.models.Kambarys;
import registracija.models.Svecias;
import registracija.services.KambarysServiceTEST;

@Controller
public class SvecioController {

    @Autowired
    private KambarysServiceTEST kambarysServiceTEST;




    @GetMapping("/svecias/registracija")
    public String svecioRegistracijaForma(Model model){
        //model objektui pateikiamas nauajs svečio šablonas, svečio objekto
        //laukai (vardas, pavarde) bus naudojami informacijai užpildyti iš formos
        model.addAttribute("svecias", new Svecias());

        return "/svecias/registracija";
    }
    @PostMapping("/svecias/registracija")
    public String svecioRegistracijaPateikimas(@ModelAttribute Svecias registruojamasSvecias){


        //iš svečio vardo ir pavardės pradžios panaikinami tarpai jei ivesti vien tik tarpai lieka tuscia eilute
        registruojamasSvecias.setVardas(registruojamasSvecias.getVardas().trim());
        registruojamasSvecias.setPavarde(registruojamasSvecias.getPavarde().trim());
        if(registruojamasSvecias.arNetuscias()) {
            try {
                //svecio registracija

                //"null" jei tuščio kambario nėra
                Kambarys tusciasKambarys = kambarysServiceTEST.tusciasKambarys();

                tusciasKambarys.setPriregistruotasSvecias(registruojamasSvecias);
                tusciasKambarys.pridetiSveciaPrieIstorijos(registruojamasSvecias);

                kambarysServiceTEST.redaguotiKambari(tusciasKambarys);
            } catch (NullPointerException klaida) {
                // tuščio kambario nėra
                registruojamasSvecias.setVardas("NERASTA");
                registruojamasSvecias.setPavarde("NERASTA");
            }
        }else {
            System.out.println("TUSCIAS!!");
        }

        return "/svecias/registracija";
    }


    @GetMapping("/svecias/isregistravimas")
    public String svecioIsregistravimoForma(Model model){
        model.addAttribute("svecias", new Svecias());
        return "/svecias/isregistravimas";
    }
    @PostMapping("/svecias/išregistravimas")
    public String svecioIsregistravimoPateikimas(@ModelAttribute Svecias isregistruojamasSvecias){
        if(isregistruojamasSvecias.arNetuscias()) {
            try {
                //svecio isregistravimas

                //null jei tokio svečio nėra
                Kambarys rastasKambarys = kambarysServiceTEST.rastKambariPagalSvecia(isregistruojamasSvecias);
                rastasKambarys.setPriregistruotasSvecias(null);
                kambarysServiceTEST.redaguotiKambari(rastasKambarys);
            } catch (NullPointerException klaida) {
                // svečias su tokiu vardu ar pavarde nėra priregistruotas jokiam kambary
                isregistruojamasSvecias.setVardas("NERASTAs s");
                isregistruojamasSvecias.setPavarde("NERASTAs s");
            }
        }else{
            isregistruojamasSvecias.setVardas("Tuscias");
            isregistruojamasSvecias.setPavarde("Tuscias");
        }

        return "/svecias/isregistravimas";
    }
}

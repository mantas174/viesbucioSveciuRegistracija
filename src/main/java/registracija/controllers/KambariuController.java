package registracija.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import registracija.models.Kambarys;
import registracija.services.KambarysService;

import java.util.List;

@Controller
public class KambariuController {

    @Autowired
    private KambarysService kambarysService;


    @RequestMapping("/kambariai/uzimti")
    public String uzimtiKambariai(Model model){

        //paimami visi užimti kambariai
        List<Kambarys> uzimtiKambariai = kambarysService.paimtUzimtusKambarius();
        model.addAttribute("uzimtiKambariai", uzimtiKambariai);

        return "kambariai/uzimti";
    }

    @RequestMapping("/kambariai/visi")
    public String visiKambariai(Model model){

        //paimami visi kambariai
        List<Kambarys> visiKambariai = kambarysService.paimtVisusKambarius();
        model.addAttribute("visiKambariai", visiKambariai);

        return "kambariai/visi";
    }

    @RequestMapping("/kambario/istorija/{kambarioNumeris}")
    public String kambarioRegistracijuIstorija(@PathVariable("kambarioNumeris") int kambarioNumeris, Model model){
        //tikrinama ar duotas kambario numeris yra visu kambariu ribose
        if(kambarioNumeris <= kambarysService.kambariuSkaicius() && kambarioNumeris > 0) {
            Kambarys kambarioIstorija = kambarysService.surastPagalNumeri(kambarioNumeris);
            String svecioNera;


            kambarioIstorija.surikiuotiRegistracijuIstorija();
            model.addAttribute("kambarioIstorija", kambarioIstorija);

            if (kambarioIstorija.getPriregistruotasSvecias() == null) {
                svecioNera = "Joks svečias prie šio kambario nepriregistruotas.";
                model.addAttribute("svecioNera", svecioNera);
            }

            return "kambariai/istorija";
        }else{
            // jei duotas kambario numeris nėra visų kamabrių ribose tai gražinama i kambarių pasirinkimo langą
            return "redirect:/kambariai/visi";
        }
    }
}

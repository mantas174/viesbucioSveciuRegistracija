package registracija.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import registracija.models.Kambarys;
import registracija.services.KambarysServiceTEST;

import java.util.ArrayList;

@Controller
public class KambariuController {

    @Autowired
    private KambarysServiceTEST kambarysServiceTEST;

    @RequestMapping("/kambariai/visi")
    public String visiKambariai(Model model){

        ArrayList<Kambarys> visiKambariai = kambarysServiceTEST.paimtVisusKambarius();
        model.addAttribute("visiKambariai", visiKambariai);

        return "kambariai/visi";
    }

    @RequestMapping("/kambario/istorija/{kambarioNumeris}")
    public String kambarioRegistracijuIstorija(@PathVariable("kambarioNumeris") int kambarioNumeris, Model model){
        Kambarys kambarys = kambarysServiceTEST.surastPagalNumeri(kambarioNumeris);
        String svecioNera;

        model.addAttribute("kambarys", kambarys);

        if(kambarys.getPriregistruotasSvecias() == null){
            svecioNera = "Joks svečias prie šio kambario nepriregistruotas.";
            model.addAttribute("svecioNera", svecioNera);
        }

        return "kambariai/istorija";
    }

    @RequestMapping("/kambariai/uzimti")
    public String uzimtiKambariai(Model model){
        ArrayList<Kambarys> uzimtiKambariai = kambarysServiceTEST.paimtUzimtusKambarius();
        model.addAttribute("uzimtiKambariai", uzimtiKambariai);

        return "kambariai/uzimti";
    }
}

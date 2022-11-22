package org.sid.web;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.metier.IBanque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class BanqueController {
    @Autowired
    private IBanque banquemetier;

    @RequestMapping("/operations")
    public String index(){
        return "comptes";
    }

    @RequestMapping(value = "/consulterCompte", method = RequestMethod.GET)

    public String consulter(Model model, String codeCompte,
                            @RequestParam(name="page",defaultValue="0") int page, @RequestParam(name="size",defaultValue="4") int size){
         model.addAttribute("codeCompte",codeCompte);
      try {

          Compte cp=banquemetier.consulterCompte(codeCompte).get();
          Page<Operation> pageOperations=banquemetier.listOperation(codeCompte,page,size);
          model.addAttribute("listoperation",pageOperations.getContent());
          // decalrez un tableau qui contient le nombre total  des page
          int [] totalPages=new int[pageOperations.getTotalPages()];
          model.addAttribute("totalPages",totalPages);
          model.addAttribute("compte",cp);
          model.addAttribute("pageCourant",page);
          model.addAttribute("size",size);
      }catch (Exception ex){
          model.addAttribute("exception",ex);

      }
        return "comptes";
        

    }
    @RequestMapping(value = "/saveOperation", method = RequestMethod.POST)
    public String saveOperation(Model model, String codeCompte,String typeOperation ,double montant,String codeCompte2){
   try {
       if (typeOperation.equals("VERS")) {
           banquemetier.verser(codeCompte, montant);
       } else if (typeOperation.equals("RETR")) {
           banquemetier.retirer(codeCompte, montant);
       } else if (typeOperation.equals("VIRM")) {
           banquemetier.virement(codeCompte, codeCompte2, montant);
       }
   }catch (Exception ex){
       model.addAttribute("error",ex);
       return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+ex.getMessage();

   }
    return "redirect:/consulterCompte?codeCompte="+codeCompte;


    } 
    

}

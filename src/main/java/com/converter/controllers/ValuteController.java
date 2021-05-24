package com.converter.controllers;

import com.converter.DAO.ValuteDAO;
import com.converter.models.History;
import com.converter.models.Parser;
import com.converter.models.Valute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ValuteController {
    private final ValuteDAO valuteDAO;

    private final static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");

    private String formatDate=simpleDateFormat.format(new Date());

    private static Valute valute=new Valute();




    @Autowired
    public ValuteController(ValuteDAO valuteDAO){
        this.valuteDAO=valuteDAO;
    }


    @GetMapping("/hello-parse")
    public String index(
            Model model) throws IOException, SAXException, ParserConfigurationException {

       if( valuteDAO.show(formatDate,"USD").isEmpty()) {
           Parser.downloadUsingStream();
           for (int i = 0; i < Parser.getList().size(); i++)
               valuteDAO.save(Parser.getList().get(i));
       }




        List<Valute> list=valuteDAO.index(formatDate);

        model.addAttribute("formatDate",formatDate);
        model.addAttribute("valute" ,valute);

        model.addAttribute("list",list);



        return "hello-parse";
    }

    @PostMapping ("/hello-parse")
    public String postIndex(@RequestParam("value") String amount,
                            @RequestParam("value2") String targetAmount,
                            @RequestParam ("flex") Long option,
                            @RequestParam("target_value") Long key)
    {



        valute= valuteDAO.takeByKey(option);
        String valuteName = valute.getName();

        valute = valuteDAO.takeByKey(key);
        String valuteTargetCharCode = valute.getCharCode();



        History history=new History(valuteName,valuteTargetCharCode,amount,targetAmount,formatDate,option);
        valuteDAO.save(history);

        return "redirect:hello-parse";

    }

    @GetMapping("/history")
    public String getHistory(Model model){
        List<Valute> list=valuteDAO.index(formatDate);
        System.out.println(list.size());


        model.addAttribute("list",list);
        model.addAttribute("valute",valute);

        return "history";

    }

    @PostMapping("/history")
    public String postHistory(@ModelAttribute("listValute") List<Valute> valutes,
                              @RequestParam ("charCode1") String charCodeFirst,
                              @RequestParam("charCode2") String charCodeSecond,
                              @RequestParam ("date") String date,
                              Model model) throws ParseException {

        System.out.println(charCodeFirst+" "+charCodeSecond);
        date= simpleDateFormat.format(Date.parse(date.replace('-','/')));


        List<Valute> valuateFirst=valuteDAO.show(date,charCodeFirst);
        String valuateNameFirst=valuateFirst.get(0).getName();
        System.out.println(valuateFirst.get(0).getCharCode());

        List<Valute> valuateSecond=valuteDAO.show(date,charCodeSecond);
        String valuateCharCodeSecond=valuateSecond.get(0).getCharCode();
        System.out.println(valuateSecond.get(0).getCharCode());

        List<History> history=valuteDAO.showHistory(date,valuateNameFirst,valuateCharCodeSecond);

        model.addAttribute("list",valutes);
        model.addAttribute("listHistory",history);
        model.addAttribute("valute",valute);

        return "history";
    }
    @ModelAttribute ("listValute")
    public List<Valute> getList(){
        return valuteDAO.index(formatDate);
    }









}

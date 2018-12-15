package forest.Controllers;

import forest.Data.Contact;
import forest.Data.DataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@org.springframework.stereotype.Controller
@RequestMapping("/hello")
public class Controller {

    @Autowired
    private DataBase dataBase;

    @GetMapping(path="/contacts")
    public @ResponseBody List<Contact> showContacts (@RequestParam String nameFilter) {

        List<Contact> list = new ArrayList<>();
        for(Contact c: dataBase.findAll()){
            Pattern p = Pattern.compile(nameFilter);
            Matcher m = p.matcher(c.getName());
            if(!m.find()) list.add(c);
        }

        return list;
    }

}

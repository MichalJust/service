import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.hibernate.Session;

import java.util.List;

@Controller
@EnableAutoConfiguration
public class Main {

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addName(@RequestBody NameRequestWrapper input) {

        if (input.name == null) {
            throw new IllegalArgumentException("Bad body format");
        }

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Name inputName = new Name();

        inputName.setName(input.name);

        session.save(inputName);
        session.getTransaction().commit();

        return ResponseEntity.ok(inputName);
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<Name> listNames() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        return session.createCriteria(Name.class).list();
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
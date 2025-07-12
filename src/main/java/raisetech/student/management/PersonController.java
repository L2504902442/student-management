package raisetech.student.management;

import org.springframework.web.bind.annotation.*;
import  java.util.concurrent.ConcurrentHashMap;
import  java.util.Map;

@RestController
@RequestMapping("/person")
public class PersonController {
    private Map<String, Integer> personMap = new ConcurrentHashMap<>();

    @PostMapping
    public String addPerson(@RequestParam String name, @RequestParam int age) {
        personMap.put(name, age);
        return  name + "さん(" + age + "歳)を追加しました";

    }

    @GetMapping
    public Map<String, Integer> getAllPersons() {
        return  personMap;
    }

    @PutMapping
    public String updatePerson(@RequestParam String name, @RequestParam int age) {
       if (personMap.containsKey(name)) {
           personMap.put(name, age);
           return  name + "さんの年齢を" + age + "歳に更新しました";

       } else {
           return name +"さんは登録されていません";

       }
    }
}

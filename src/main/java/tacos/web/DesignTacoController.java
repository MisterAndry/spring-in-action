package tacos.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.Ingredient;
import tacos.Taco;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static tacos.Ingredient.Type;
import static tacos.Ingredient.Type.*;

@Controller
@RequestMapping("/design")
public class DesignTacoController {

    private static final Logger log = LoggerFactory.getLogger(DesignTacoController.class);

    @ModelAttribute
    public String addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", WRAP),
                new Ingredient("COTO", "Corn Tortilla", WRAP),
                new Ingredient("GRBF", "Ground Beef", PROTEIN),
                new Ingredient("CARN", "Carnitas", PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", VEGGIES),
                new Ingredient("LETC", "Lettuce", VEGGIES),
                new Ingredient("CHED", "Cheddar", CHEESE),
                new Ingredient("JACK", "Monterrey Jack", CHEESE),
                new Ingredient("SLSA", "Salsa", SAUCE),
                new Ingredient("SRCR", "Sour Cream", SAUCE)
        );

        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());
        return "design";
    }

    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "design";
        }
        log.info("Processing design: " + design);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }
}

package tacos.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.Order;
import tacos.data.OrderRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private OrderRepository orderrepo;

    public OrderController(OrderRepository orderrepo) {
        this.orderrepo = orderrepo;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "order";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "order";
        }
        orderrepo.save(order);
                sessionStatus.setComplete();
        return "redirect:/";
    }
}

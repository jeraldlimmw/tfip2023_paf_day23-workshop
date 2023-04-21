package main.java.sg.edu.nus.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import main.java.sg.edu.nus.iss.model.OrderDetails;
import main.java.sg.edu.nus.iss.repository.OrderDetailsRepository;

@Controller
@RequestMapping
public class OrderDetailsController {
    @Autowired
    private OrderDetailsRepository odRepo;

    @GetMapping(path = {"/", "/index.html"})
    public String showOrderIdForm(Model m) {
        OrderDetails orderDetails = new OrderDetails();
        m.addAttribute("orderDetails", orderDetails);
        return "index";
    }

    @PostMapping(path = "/order/total")
    public String getOrderId(Model m, OrderDetails orderDetails) {
        String id = orderDetails.getId().toString();
        System.out.println("order_id >>>> " + id);
        m.addAttribute("orderDetails", orderDetails);
        return "redirect:/order/total/" + id;
    }

    @GetMapping(path = "/order/total/{id}")
    public String showOrderDetailsFromId(Model m, @PathVariable String id, OrderDetails orderDetails, BindingResult bindings) {
        OrderDetails od = new OrderDetails();
        try {
            od = odRepo.getOrderDetailsWithDiscount(Integer.parseInt(id));
        } catch (Exception e) {
            FieldError fe = new FieldError("orderDetails", "id", "invalid order_id");
            bindings.addError(fe);
            m.addAttribute("orderDetails", orderDetails);
            return "index";
        }
        m.addAttribute("orderDetails", od);
        return "order";
    }
}

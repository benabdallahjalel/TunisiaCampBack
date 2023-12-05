package esprit.tunisiacamp.restControllers;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import esprit.tunisiacamp.paypal.Order;
import esprit.tunisiacamp.paypal.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.RedirectView;

@CrossOrigin("http://localhost:4200/")
@RestController
public class PaypalController {
    @Autowired
    PaypalService service;


    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @GetMapping("/hme")
    public String home() {
        return  "home";
    }
    @CrossOrigin("http://localhost:4200")
    @PreAuthorize("hasAnyAuthority('CAMPER','MANAGER','SHOP','ADMIN')")
    @PostMapping("/pay")
    public RedirectView payment(@RequestBody Order order) {
        return service.payment(order);}
       /* try {
            Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
                    order.getIntent(),"http://localhost:9090/rest/" + CANCEL_URL,
                    "http://localhost:9090/rest/" + SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    System.out.println("success");
                    return new RedirectView(link.getHref());
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return new RedirectView("http://localhost:9090/rest/cancel");
    }*/
    @CrossOrigin("http://localhost:4200")
    @PreAuthorize("hasAnyAuthority('CAMPER','MANAGER','SHOP','ADMIN')")
    @GetMapping(value = CANCEL_URL)
    public RedirectView cancelPay() {

        return new RedirectView("http://localhost:1111/rest/cancel");
       // return "cancel";
    }
    @CrossOrigin("http://localhost:4200")
    @PreAuthorize("hasAnyAuthority('CAMPER','MANAGER','SHOP','ADMIN')")
    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {

                return (" sucsess");
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return ("cancel");
    }
}

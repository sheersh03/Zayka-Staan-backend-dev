package com.zayka.backend.controller;
import com.zayka.backend.dto.*; import com.zayka.backend.model.*; import com.zayka.backend.service.AppService; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api")
public class PublicController {
  private final AppService svc; public PublicController(AppService s){ this.svc=s; }
  @GetMapping("/children") public List<Child> children(){ return svc.listChildren(); }
  @GetMapping("/menus") public List<MenuItem> menus(@RequestParam String from, @RequestParam String to){ return svc.menusBetween(from,to); }
  @GetMapping("/subscriptions") public List<Subscription> subs(@RequestParam Long childId){ return svc.subsForChild(childId); }
  @PostMapping("/subscriptions") public Subscription changePlan(@RequestBody PlanChangeRequest req){ return svc.changePlan(req); }
  @GetMapping("/invoices") public List<Invoice> invoices(@RequestParam Long subscriptionId){ return svc.invoices(subscriptionId); }
  @PostMapping("/invoices/{id}/pay") public Invoice pay(@PathVariable Long id, @RequestBody PayInvoiceRequest req){ return svc.pay(id, req.method()); }
  @PostMapping("/selections") public Selection toggle(@RequestBody SelectionRequest req){ return svc.toggleSelection(req); }
  @GetMapping("/deliveries") public List<Delivery> deliveries(@RequestParam String date){ return svc.deliveriesForDate(date); }
  @PostMapping("/deliveries/{id}/mark-delivered") public Delivery markDelivered(@PathVariable Long id){ return svc.markDelivered(id); }
  @PostMapping("/feedback") public Feedback feedback(@RequestBody FeedbackRequest req){ return svc.createFeedback(req); }
}
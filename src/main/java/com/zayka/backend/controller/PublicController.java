package com.zayka.backend.controller;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zayka.backend.dto.FeedbackRequest;
import com.zayka.backend.dto.PayInvoiceRequest;
import com.zayka.backend.dto.PlanChangeRequest;
import com.zayka.backend.dto.SelectionRequest;
import com.zayka.backend.model.Child;
import com.zayka.backend.model.Delivery;
import com.zayka.backend.model.Feedback;
import com.zayka.backend.model.Invoice;
import com.zayka.backend.model.MenuItem;
import com.zayka.backend.model.Selection;
import com.zayka.backend.model.Subscription;
import com.zayka.backend.service.AppService;
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
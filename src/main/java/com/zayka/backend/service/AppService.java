package com.zayka.backend.service;
import com.zayka.backend.dto.*; import com.zayka.backend.model.*; import com.zayka.backend.repo.*; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.time.LocalDate; import java.util.List;
@Service @Transactional
public class AppService {
  private final ChildRepo childRepo; private final SubscriptionRepo subscriptionRepo; private final MenuItemRepo menuItemRepo; private final SelectionRepo selectionRepo; private final DeliveryRepo deliveryRepo; private final FeedbackRepo feedbackRepo; private final InvoiceRepo invoiceRepo;
  public AppService(ChildRepo c, SubscriptionRepo s, MenuItemRepo m, SelectionRepo sel, DeliveryRepo d, FeedbackRepo f, InvoiceRepo i){ this.childRepo=c; this.subscriptionRepo=s; this.menuItemRepo=m; this.selectionRepo=sel; this.deliveryRepo=d; this.feedbackRepo=f; this.invoiceRepo=i; }
  public List<Child> listChildren(){ return childRepo.findAll(); }
  public List<MenuItem> menusBetween(String from, String to){ return menuItemRepo.findByDateBetween(from,to); }
  public List<Subscription> subsForChild(Long childId){ return subscriptionRepo.findByChildId(childId); }
  public Subscription changePlan(PlanChangeRequest req){
    Plan plan = Plan.valueOf(req.planId()); Subscription s = subscriptionRepo.findByChildId(req.childId()).stream().findFirst().orElse(null);
    String start = LocalDate.now().toString(); String next = plan==Plan.WEEKLY? LocalDate.now().plusDays(7).toString(): LocalDate.now().plusDays(30).toString();
    int base = plan==Plan.WEEKLY? 499 : 1799; int amount = base - (req.couponCode()!=null?100:0) - (Boolean.TRUE.equals(req.useReferral())?50:0); if (amount<0) amount=0;
    if (s==null){ s = Subscription.builder().childId(req.childId()).planId(plan).status("ACTIVE").startDate(start).nextRenewal(next).price(amount).currency("INR").build(); }
    else { s.setPlanId(plan); s.setStartDate(start); s.setNextRenewal(next); s.setPrice(amount); s.setStatus("ACTIVE"); }
    subscriptionRepo.save(s); Invoice inv = Invoice.builder().subscriptionId(s.getId()).periodStart(start).periodEnd(next).amount(amount).status("DUE").build(); invoiceRepo.save(inv); return s;
  }
  public List<Invoice> invoices(Long subscriptionId){ return invoiceRepo.findBySubscriptionId(subscriptionId); }
  public Invoice pay(Long invoiceId, String method){ Invoice inv = invoiceRepo.findById(invoiceId).orElseThrow(); inv.setStatus("PAID"); inv.setMethod(method); return invoiceRepo.save(inv); }
  public Selection toggleSelection(SelectionRequest req){ var existing = selectionRepo.findByChildIdAndDate(req.childId(), req.date()); if (existing.isEmpty()){ Selection s = Selection.builder().childId(req.childId()).date(req.date()).status(req.status()).build(); return selectionRepo.save(s);} else { selectionRepo.deleteAll(existing); return null; } }
  public List<Delivery> deliveriesForDate(String date){ return deliveryRepo.findByDate(date); }
  public Delivery markDelivered(Long id){ Delivery d = deliveryRepo.findById(id).orElseThrow(); d.setStatus("DELIVERED"); d.setDeliveredAt(java.time.Instant.now().toString()); return deliveryRepo.save(d); }
  public Feedback createFeedback(FeedbackRequest req){ Feedback f = Feedback.builder().childId(req.childId()).date(req.date()).rating(req.rating()).tags(req.tags()).comment(req.comment()).build(); return feedbackRepo.save(f); }
}
import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store;
    private final TaxCalculator taxCalc;
    private final DiscountCalculator discCalc;
    private final InvoiceFormatter formatter;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceStore store, TaxCalculator taxCalc, DiscountCalculator discCalc, InvoiceFormatter formatter) {
        this.store = store;
        this.taxCalc = taxCalc;
        this.discCalc = discCalc;
        this.formatter = formatter;
    }

    public void addToMenu(MenuItem i){
        menu.put(i.id, i); 
    }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);
        List<String> details = new ArrayList<>();
        double subtotal = 0.0;

        for(OrderLine l : lines){
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            details.add(String.format("- %s x%d = %.2f", item.name, l.qty, lineTotal));
        }

        double tax = taxCalc.calculateTax(customerType, subtotal);
        double taxPct = taxCalc.getTaxPercent(customerType);
        double discount = discCalc.calculateDiscount(customerType, subtotal, lines);
        double total = subtotal + tax - discount;

        BillingSummary summary = new BillingSummary(invId, details, subtotal, tax, taxPct, discount, total);
        String printable = formatter.format(summary);

        System.out.print(printable);
        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
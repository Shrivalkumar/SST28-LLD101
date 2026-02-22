import java.util.List;

public class BillingSummary {
    public final String invId;
    public final List<String> lineDetails;
    public final double subtotal;
    public final double tax;
    public final double taxPct;
    public final double discount;
    public final double total;

    public BillingSummary(String invId, List<String> lineDetails, double subtotal, double tax, double taxPct, double discount, double total) {
        this.invId = invId;
        this.lineDetails = lineDetails;
        this.subtotal = subtotal;
        this.tax = tax;
        this.taxPct = taxPct;
        this.discount = discount;
        this.total = total;
    }
}
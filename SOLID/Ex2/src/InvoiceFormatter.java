public class InvoiceFormatter {
    public String format(BillingSummary s) {
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(s.invId).append("\n");
        for (String line : s.lineDetails){
            out.append(line).append("\n");
        }
        out.append(String.format("Subtotal: %.2f\n", s.subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", s.taxPct, s.tax));
        out.append(String.format("Discount: -%.2f\n", s.discount));
        out.append(String.format("TOTAL: %.2f\n", s.total));
        return out.toString();
    }
}
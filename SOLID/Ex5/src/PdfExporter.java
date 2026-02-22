import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {

    @Override
    public int maxLength(){
        return 20;
    }
    @Override
    public String getFormatName(){
        return "PDF";
    }
    @Override
    public String getContentType(){
        return "application/pdf";
    }



    @Override
    public ExportResult generateExport(ExportRequest req) {
       String fakePdf = "PDF(" + req.title + "):" + req.body;
       return new ExportResult(getContentType(), fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}

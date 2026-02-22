import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {

    @Override
    public String getFormatName(){
        return "CSV";
    }

    @Override
    public String getContentType(){
        return "text/csv";
    }


    @Override
    public ExportResult generateExport(ExportRequest req) {
       String title = req.title == null ? "" : req.title;
       String body = req.body == null ? "" : req.body;

        String csv = title + ","+body;
        return new ExportResult(getContentType(), csv.getBytes(StandardCharsets.UTF_8));
    }
}

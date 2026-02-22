public abstract class Exporter {
        
    public final ExportResult export(ExportRequest req){
        if(req == null){
            return new ExportResult(getContentType(), new byte[0]);
        }
        if (req.body != null && req.body.length() > maxLength()) {
            throw new IllegalArgumentException(getFormatName() + " cannot handle content > " + maxLength() + " chars");
        }
        return generateExport(req);
        
    }
    protected abstract ExportResult generateExport(ExportRequest req);

    public int maxLength(){
        return (int)1e9;
    }
    public abstract String getFormatName();
    public abstract String getContentType();
}

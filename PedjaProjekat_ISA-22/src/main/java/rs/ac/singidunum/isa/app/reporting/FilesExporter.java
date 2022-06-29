package rs.ac.singidunum.isa.app.reporting;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import org.dom4j.DocumentException;
import rs.ac.singidunum.isa.app.model.Nastavnik;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Document;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FilesExporter {
    public void setReponseHeader(HttpServletResponse response, String contentType,
                                 String extension, String prefix) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timeStamp = dateFormat.format(new Date());
        String fileName = prefix + timeStamp + extension;

        response.setContentType(contentType);

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName;
        response.setHeader(headerKey, headerValue);

    }

    public void exportToPDF(Iterable<Nastavnik> listNastavnik, HttpServletResponse response) throws DocumentException,IOException{
        setReponseHeader(response, "application/pdf", ".pdf", "Nastavnik_");

        Document document = (Document) new com.lowagie.text.Document(PageSize.A4);
        PdfWriter.getInstance((com.lowagie.text.Document) document, response.getOutputStream());

        ((com.lowagie.text.Document) document).open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);
        Paragraph para = new Paragraph("List of Nastavnik", font);

        ((com.lowagie.text.Document) document).add(para);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);

        ((com.lowagie.text.Document) document).add(table);
        ((com.lowagie.text.Document) document).close();

    }
}

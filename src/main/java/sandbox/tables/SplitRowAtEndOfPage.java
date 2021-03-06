/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/24616920/last-row-in-itext-table-extending-when-it-shouldnt
 */

package sandbox.tables;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sandbox.WrapToTest;

@WrapToTest
public class SplitRowAtEndOfPage {
    public static final String DEST = "results/tables/split_row_at_end_of_page.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SplitRowAtEndOfPage().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        PdfPTable table = new PdfPTable(1);
        table.setTotalWidth(550);
        table.setLockedWidth(true);
        for (int i = 0; i < 10; i++) {
            PdfPCell cell;
            if (i == 9) {
                cell = new PdfPCell(new Phrase("Two\nLines"));
            }
            else {
                cell = new PdfPCell(new Phrase(Integer.toString(i)));
            }
            table.addCell(cell);
        }
        Document document = new Document(new Rectangle(612, 242));
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        table.setSplitLate(false);
        document.add(table);
        document.close();
    }
}

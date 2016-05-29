package PDF;

import Models.Invoice.InvoiceItem;
import Models.Store.Item;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Invoice {

    public static void generate(Models.Invoice.Invoice invoiceModel) throws DocumentException, IOException
    {
        Float total = 0f;

        String Dir = System.getProperty("user.dir");
        System.out.println(Dir);
        Document document = new Document();

        String input = Dir + "/storage/Invoice/generated.html";
        String componentsDir = Dir + "/storage/Invoice/components/";
        PrintWriter inputWriter = new PrintWriter(input, "UTF-8");

        String header = readFile(componentsDir + "header.html", StandardCharsets.UTF_8);
        String content = readFile(componentsDir + "content.html", StandardCharsets.UTF_8);
        String footer = readFile(componentsDir + "footer.html", StandardCharsets.UTF_8);
        String item = readFile(componentsDir + "_item.html", StandardCharsets.UTF_8);

        String itemRow;
        String allItems = "";
        List<InvoiceItem> invoiceItems = invoiceModel.getAll(InvoiceItem.class);

        for(InvoiceItem current : invoiceItems) {

            Object cost = current.parent(Item.class).get("cost");
            Object qty = current.get("quantity");
            Float summaryCost = Float.parseFloat(cost.toString()) * Integer.parseInt(qty.toString());

            itemRow = item.replace("{{name}}", current.parent(Item.class).get("name").toString());
            itemRow = itemRow.replace("{{quantity}}", qty.toString());
            itemRow = itemRow.replace("{{cost}}", cost.toString());
            itemRow = itemRow.replace("{{total_cost}}", summaryCost.toString());

            allItems += itemRow;
            total += summaryCost;
        }

        content = content.replace("{{items}}", allItems);
        content = content.replace("{{total}}", String.format("%.2f", total));

        inputWriter.println(header + content + footer);
        inputWriter.close();
        String pdfPAth = Dir + "/pdf";
        String pdfFileName = "generated.pdf";
        String fullPath = pdfPAth + "/" + pdfFileName;
        Boolean created = (new File(pdfPAth)).mkdirs();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fullPath));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
            new FileInputStream(input)
        );
        document.close();
        System.out.println( "PDF Created!" );

        Desktop.getDesktop().open(new File(pdfPAth));
    }

    static String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}

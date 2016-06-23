import com.snowtide.PDF;
import com.snowtide.pdf.Document;
import com.snowtide.pdf.OutputTarget;
import com.snowtide.pdf.Page;

import java.io.*;

/**
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String source = "";
        String outputName = "";
        System.out.println("*****************\nThis is simple parsing program\ndeveloped special for our project,\nbased on the PDFTextStream library\n*****************\n");
        System.out.println("Please, give me the PDF-path (include document name and its extension)");
        source = reader.readLine();
        System.out.println("And name of the output file: ");
        outputName = reader.readLine();
        System.out.println("Parsing in process...\n");
        File pdfFile = new File(source);
        File outputFile = new File(outputName + ".txt");
        try {
            savePDFText(pdfFile, outputFile);
            System.out.println("\nSuccess!");
        } catch (IOException e) {
            System.out.println("There is an error: " + e.getMessage());
        }
    }

    public static void savePDFText(File pdfFile, File outputFIle) throws IOException{

        Document pdf = PDF.open(pdfFile);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFIle)));
        for(int i = 0; i < pdf.getPageCnt()-1; i++) {
            Page page = pdf.getPage(i);
            page.pipe(new OutputTarget(writer));
        }
        pdf.close();
        writer.flush();
        writer.close();
    }

}

@Grapes(
        [@Grab(group = 'org.xhtmlrenderer', module = 'flying-saucer-pdf', version = '9.0.7'),
                @Grab(group = 'org.jsoup', module = 'jsoup', version = '1.8.2')]
)

import org.xhtmlrenderer.pdf.ITextRenderer
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Entities

def pdf = "flyingsaucer.pdf"
def testFile = new File("test.html")

Document doc = Jsoup.parse(testFile, "UTF-8");
doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml); //This will ensure the validity
doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
doc.outputSettings().charset("UTF-8");

println(doc)

OutputStream os = new FileOutputStream(pdf);

ITextRenderer renderer = new ITextRenderer();
renderer.setDocumentFromString(doc.toString());
renderer.layout();
renderer.createPDF(os);
os.close();
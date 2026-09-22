package org.sto;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder.FontStyle;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class App {

    public static void main(String[] args) throws Exception {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setClassForTemplateLoading(App.class, "/templates");
        cfg.setDefaultEncoding("UTF-8");

        Map<String, Object> data = new HashMap<>();
        data.put("department", "Программных систем");
        data.put("title", "РАЗРАБОТКА ИНФОРМАЦИОННОЙ СИСТЕМЫ...");
        data.put("specialtyCode", "09.03.04");
        data.put("specialtyName", "Программная инженерия");
        data.put("studentName", "И.О. Фамилия");
        data.put("supervisorTitle", "д-р техн. наук, профессор");
        data.put("supervisorName", "И.О. Фамилия");
        data.put("year", "2026");

        Template template = cfg.getTemplate("title_page.ftl");
        StringWriter writer = new StringWriter();
        template.process(data, writer);
        String htmlContent = writer.toString();

        URL baseUrl = App.class.getResource("/");

        try (OutputStream os = new FileOutputStream("TitlePage.pdf")) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            // builder.useFastMode();

            registerTimesFonts(builder);

            builder.withHtmlContent(htmlContent, baseUrl.toExternalForm());

            builder.toStream(os);
            builder.run();
        }

        System.out.println("PDF успешно сгенерирован!");
    }

    private static void registerTimesFonts(PdfRendererBuilder builder) {
        builder.useFont(() -> getFontStream("/fonts/times.ttf"),   "Times New Roman", 400, FontStyle.NORMAL, true);
        builder.useFont(() -> getFontStream("/fonts/timesbd.ttf"), "Times New Roman", 700, FontStyle.NORMAL, true);
        builder.useFont(() -> getFontStream("/fonts/timesi.ttf"),  "Times New Roman", 400, FontStyle.ITALIC, true);
        builder.useFont(() -> getFontStream("/fonts/timesbi.ttf"), "Times New Roman", 700, FontStyle.ITALIC, true);
        
    }

    private static InputStream getFontStream(String resourcePath) {
        InputStream is = App.class.getResourceAsStream(resourcePath);
        if (is == null) {
            throw new IllegalStateException("Font not found in classpath: " + resourcePath);
        }
        return is;
    }
}
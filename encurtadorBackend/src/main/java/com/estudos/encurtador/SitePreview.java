package com.estudos.encurtador;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SitePreview {
    public static void main(String[] args) throws Exception {
        String url = "https://youtube.com";
        Document doc = Jsoup.connect(url).get();

        // Pegar título
        String title = doc.title();
        System.out.println("Título: " + title);

        // Pegar favicon (simplificado)
        String favicon = doc.select("link[rel~=icon]").attr("abs:href");
        System.out.println("Favicon: " + favicon);

        // Pegar Open Graph Image (thumbnail)
        String ogImage = doc.select("meta[property=og:image]").attr("content");
        System.out.println("Thumbnail: " + ogImage);
    }
}

package com.station.service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import com.station.model.Client;
import com.station.model.Entretien;
import com.station.model.Service;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

public class PdfGenerator {

    private static final String DOSSIER_RECUS = "recus";
    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public String genererRecuEntretien(Entretien entretien, Client client, Service service) throws IOException {
        Path dossier = Path.of(DOSSIER_RECUS);
        if (!Files.exists(dossier)) {
            Files.createDirectories(dossier);
        }

        String nomFichier = "recu_" + entretien.getNumEntr() + ".pdf";
        Path cheminFichier = dossier.resolve(nomFichier);

        Document document = new Document();

        try (FileOutputStream fos = new FileOutputStream(cheminFichier.toFile())) {
            PdfWriter.getInstance(document, fos);
            document.open();

            ajouterEntete(document);
            ajouterInfosClient(document, client, entretien);
            ajouterDetailPrestation(document, entretien, service);
            ajouterPied(document, entretien);

            document.close();
        } catch (DocumentException e) {
            throw new IOException("Erreur lors de la génération du PDF", e);
        }

        return cheminFichier.toString();
    }

    private void ajouterEntete(Document document) throws DocumentException {
        Font fontTitre = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        Paragraph titre = new Paragraph("Station-Service - Reçu d'entretien", fontTitre);
        titre.setAlignment(Element.ALIGN_CENTER);
        titre.setSpacingAfter(20);
        document.add(titre);
    }

    private void ajouterInfosClient(Document document, Client client, Entretien entretien) throws DocumentException {
        Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 11);

        document.add(new Paragraph("N° fiche d'intervention : " + entretien.getNumEntr(), fontNormal));
        document.add(new Paragraph("Date : " + entretien.getDateEntretien().format(FORMAT_DATE), fontNormal));
        document.add(new Paragraph(" ", fontNormal));
        document.add(new Paragraph("Client : " + client.getNomClient(), fontNormal));
        document.add(new Paragraph("Téléphone : " + (client.getTelephone() != null ? client.getTelephone() : "-"), fontNormal));
        document.add(new Paragraph("Immatriculation : " + entretien.getImmatriculationVoiture(), fontNormal));
        document.add(new Paragraph(" ", fontNormal));
    }

    private void ajouterDetailPrestation(Document document, Entretien entretien, Service service) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);

        Font fontEntete = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11);
        Font fontCellule = FontFactory.getFont(FontFactory.HELVETICA, 11);

        PdfPCell enteteService = new PdfPCell(new Paragraph("Prestation", fontEntete));
        PdfPCell enteteMontant = new PdfPCell(new Paragraph("Montant", fontEntete));
        enteteService.setBackgroundColor(new Color(230, 230, 230));
        enteteMontant.setBackgroundColor(new Color(230, 230, 230));
        table.addCell(enteteService);
        table.addCell(enteteMontant);

        table.addCell(new PdfPCell(new Paragraph(service.getDesignation(), fontCellule)));
        table.addCell(new PdfPCell(new Paragraph(entretien.getMontantTotal() + " Ar", fontCellule)));

        document.add(table);
    }

    private void ajouterPied(Document document, Entretien entretien) throws DocumentException {
        Font fontTotal = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13);
        Paragraph total = new Paragraph("Total payé : " + entretien.getMontantTotal() + " Ar", fontTotal);
        total.setAlignment(Element.ALIGN_RIGHT);
        total.setSpacingBefore(20);
        document.add(total);

        Font fontMerci = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10);
        Paragraph merci = new Paragraph("Merci de votre confiance.", fontMerci);
        merci.setAlignment(Element.ALIGN_CENTER);
        merci.setSpacingBefore(30);
        document.add(merci);
    }
}
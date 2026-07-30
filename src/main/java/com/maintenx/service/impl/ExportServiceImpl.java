package com.maintenx.service.impl;
import com.maintenx.exception.BusinessException;
import com.maintenx.model.Intervention;
import com.maintenx.service.ExportService;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
public class ExportServiceImpl implements ExportService {
    public File exportInterventions(List<Intervention> interventions, File file) {
        try (var w = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            w.println("reference;titre;categorie;priorite;statut;localisation;technicien;cout_estime;cout_reel");
            for (var i : interventions) w.printf("%s;%s;%s;%s;%s;%s;%s;%s;%s%n", csv(i.getReference()), csv(i.getTitre()), csv(i.getCategorie()), i.getPriorite(), i.getStatut(), csv(i.getLocalisation()), i.getTechnicien() == null ? "" : csv(i.getTechnicien().nomComplet()), i.getCoutEstime(), i.getCoutReel());
            return file;
        } catch (IOException e) { throw new BusinessException("Export CSV impossible : " + e.getMessage()); }
    }
    private String csv(String s) { return s == null ? "" : s.replace(';', ',').replace('\n', ' '); }
}

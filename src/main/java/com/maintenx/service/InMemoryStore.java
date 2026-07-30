package com.maintenx.service;

import com.maintenx.model.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryStore {
    public final List<Utilisateur> utilisateurs = new ArrayList<>();
    public final List<Technicien> techniciens = new ArrayList<>();
    public final List<Intervention> interventions = new ArrayList<>();
    public final List<HistoriqueIntervention> historiques = new ArrayList<>();
    public final List<JournalActivite> journaux = new ArrayList<>();
    public final AtomicLong userSeq = new AtomicLong(1);
    public final AtomicLong techSeq = new AtomicLong(1);
    public final AtomicLong intSeq = new AtomicLong(1);
    public final AtomicLong histSeq = new AtomicLong(1);
    public final AtomicLong journalSeq = new AtomicLong(1);
}

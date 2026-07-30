package com.maintenx.service.impl;
import com.maintenx.model.JournalActivite;
import com.maintenx.service.*;
import java.util.List;
public class JournalActiviteServiceImpl implements JournalActiviteService {
    private final InMemoryStore store;
    public JournalActiviteServiceImpl(InMemoryStore store) { this.store = store; }
    public void log(String utilisateur, String action, String details) { store.journaux.add(new JournalActivite(store.journalSeq.getAndIncrement(), utilisateur, action, details)); }
    public List<JournalActivite> findAll() {
        return store.journaux.stream()
                .sorted(java.util.Comparator.comparing(JournalActivite::getDateAction).reversed())
                .toList();
    }
}

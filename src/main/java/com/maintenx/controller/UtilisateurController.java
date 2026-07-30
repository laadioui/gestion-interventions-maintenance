package com.maintenx.controller;
import com.maintenx.model.Utilisateur;
import com.maintenx.model.enums.Role;
import com.maintenx.service.UtilisateurService;
import java.util.List;
public class UtilisateurController {
    private final UtilisateurService service; private final Utilisateur connected;
    public UtilisateurController(UtilisateurService service, Utilisateur connected) { this.service = service; this.connected = connected; }
    public List<Utilisateur> search(String text, Role role, Boolean active) { return service.search(text, role, active); }
    public Utilisateur create(Utilisateur u, String pwd) { return service.create(u, pwd); }
    public void update(Utilisateur u) { service.update(u); }
    public void deactivate(long id) { service.deactivate(id, connected.getId()); }
    public void activate(long id) { service.activate(id); }
    public void resetPassword(long id, String pwd) { service.resetPassword(id, pwd); }
}

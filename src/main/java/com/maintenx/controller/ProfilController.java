package com.maintenx.controller;
import com.maintenx.model.Utilisateur;
import com.maintenx.service.UtilisateurService;
public class ProfilController {
    private final UtilisateurService service; private final Utilisateur user;
    public ProfilController(UtilisateurService service, Utilisateur user) { this.service = service; this.user = user; }
    public Utilisateur current() { return user; }
    public void update(Utilisateur updated) { service.update(updated); }
    public void changePassword(String pwd) { service.resetPassword(user.getId(), pwd); }
}

package com.maintenx.service;
import com.maintenx.model.Utilisateur;
public interface AuthenticationService { Utilisateur login(String username, String password); }

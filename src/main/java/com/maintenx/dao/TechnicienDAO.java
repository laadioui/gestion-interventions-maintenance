package com.maintenx.dao;
import com.maintenx.model.Technicien;
public interface TechnicienDAO extends GenericDAO<Technicien, Long> { boolean existsByMatricule(String matricule); }

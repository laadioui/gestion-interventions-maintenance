package com.maintenx.service;
import com.maintenx.model.Intervention;
import java.io.File;
import java.util.List;
public interface ExportService { File exportInterventions(List<Intervention> interventions, File file); }

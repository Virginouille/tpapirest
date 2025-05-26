package com.tpapirest.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpapirest.dao.TaskDAO;
import com.tpapirest.models.Task;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/***Classe TaskServkets*/
public class TaskServlets extends HttpServlet {
    /**Récup de la classe TaskDAO et de ObjectMapper*/
    private final TaskDAO taskDAO = new TaskDAO();
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**Méthode pour faire un get*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        resp.setContentType("application/json");

        if (pathInfo == null || "/".equals(pathInfo)) {

            List<Task> tasks = taskDAO.recupToutesTasks();
            resp.setStatus(HttpServletResponse.SC_OK);
            objectMapper.writeValue(resp.getOutputStream(), tasks);
        } else {

            String idStr = pathInfo.substring(1);
            try {
                int id = Integer.parseInt(idStr);
                Task task = taskDAO.recupToutesTasks().get(id);
                if (task == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"erreur\":\"Task pas trouvee\"}");
                } else {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    objectMapper.writeValue(resp.getOutputStream(), task);
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"erreur\":\"Format invalide\"}");
            }
        }
    }

    /**Méthode pour faire un post*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        try {
            // Lire le JSON reçu et le convertir en objet Task
            Task task = objectMapper.readValue(req.getInputStream(), Task.class);

            if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"erreur\":\"Titre obligatoire, ne peut être vide\"}");
                return;
            }

            // Initialiser les champs automatiques
            task.setId(0); // ou laisser à 0 pour auto-incrément
            task.setDone(false);
            task.setCreatedAt(LocalDate.from(java.time.LocalDateTime.now()));
            task.setUpdatedAt (LocalDate.from(java.time.LocalDateTime.now()));

            // Créer la tâche via la DAOTask
            Task createdTask = taskDAO.create(task);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            objectMapper.writeValue(resp.getOutputStream(), createdTask);

        } catch (Exception e) {
            // En cas d’erreur JSON mal formée ou autre
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"erreur\":\"format Json Invalid ou erreur de requete\"}");
        }
    }

}

package com.tpapirest.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpapirest.dao.TaskDAO;
import com.tpapirest.models.Task;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
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
}

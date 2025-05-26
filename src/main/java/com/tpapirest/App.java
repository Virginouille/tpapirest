package com.tpapirest;

import com.tpapirest.servlets.TaskServlets;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class App {
    public static void main(String[] args) throws Exception {

            //Lancement d'un Jetty avec servlet
            Server server = new Server(8080);

            ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
            handler.setContextPath("/");

            // Mappe le servlet sur /tasks et /tasks/*
            handler.addServlet(new ServletHolder(new TaskServlets()), "/tasks/*");

            server.setHandler(handler);
            server.start();
            server.join();
        }
    }


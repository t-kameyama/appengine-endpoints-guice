package com.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

// For devserver
@WebServlet(name = "empty", value = "/_ah/api/*")
public class EmptyServlet extends HttpServlet {}

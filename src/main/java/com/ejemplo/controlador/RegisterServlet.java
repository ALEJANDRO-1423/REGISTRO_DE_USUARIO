package com.ejemplo.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.ejemplo.modelo.User;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User user = new User(username, password, email);

        // Validación de la longitud del nombre de usuario
        if (user.getUsername().length() < 4) {
            request.setAttribute("errorMessage", "El nombre de usuario debe tener al menos 4 caracteres.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        // Validación del formato de correo electrónico
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!user.getEmail().matches(emailPattern)) {
            request.setAttribute("errorMessage", "El correo electrónico no es válido.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        // Verificación de que la contraseña no contenga el nombre de usuario
        if (user.getPassword().toLowerCase().contains(user.getUsername().toLowerCase())) {
            request.setAttribute("errorMessage", "La contraseña no debe contener el nombre de usuario.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        // Validación de la longitud de la contraseña
        if (user.getPassword().length() < 8) {
            request.setAttribute("errorMessage", "La contraseña debe tener al menos 8 caracteres.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        // Validación de que el nombre de usuario no contenga símbolos especiales
        if (!user.getUsername().matches("[A-Za-z0-9]+")) {
            request.setAttribute("errorMessage", "El nombre de usuario no debe contener símbolos especiales.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        // Simulación de lista de nombres de usuario existentes
        List<String> existingUsernames = Arrays.asList("usuario1", "admin", "testuser");
        if (existingUsernames.contains(user.getUsername())) {
            request.setAttribute("errorMessage", "El nombre de usuario ya existe.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        // Si pasa todas las validaciones, se guarda el usuario y se redirige a la página de éxito
        request.setAttribute("user", user);
        request.getRequestDispatcher("success.jsp").forward(request, response);
    }
}


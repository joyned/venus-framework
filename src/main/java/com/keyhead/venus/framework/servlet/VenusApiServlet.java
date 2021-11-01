package com.keyhead.venus.framework.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyhead.venus.framework.api.handler.VenusApiHandler;

@WebServlet(name = "VenusApiServlet", urlPatterns = { "/" })
public class VenusApiServlet extends HttpServlet {

	private static final long serialVersionUID = -2687145576814334555L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Writer w = resp.getWriter();
		w.write(new ObjectMapper()
				.writeValueAsString(VenusApiHandler.executeMethodByInformedUrl(req.getRequestURI(), req.getMethod())));
		w.flush();
		w.close();
	}

}

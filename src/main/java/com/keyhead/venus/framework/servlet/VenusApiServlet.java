package com.keyhead.venus.framework.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyhead.venus.framework.api.handler.VenusApiHandler;
import com.keyhead.venus.framework.http.HttpMethod;
import com.keyhead.venus.framework.http.exceptions.BadRequestException;
import com.keyhead.venus.framework.http.model.ErrorResponse;

@WebServlet(name = "VenusApiServlet", urlPatterns = { "/" })
public class VenusApiServlet extends HttpServlet {

	private static final long serialVersionUID = -2687145576814334555L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Object requestBody = null;
			Writer w = resp.getWriter();

			HttpMethod httpMethod = HttpMethod.valueOf(req.getMethod());

			if (httpMethod.equals(HttpMethod.POST) || httpMethod.equals(HttpMethod.PUT)) {
				requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			}

			resp.addHeader("content-type", "application/json");

			w.write(new ObjectMapper().writeValueAsString(
					VenusApiHandler.executeMethodByInformedUrl(req.getRequestURI(), httpMethod, requestBody)));
			w.flush();
			w.close();
		} catch (Exception e) {
			Writer w = resp.getWriter();
			ErrorResponse errorResponse = new ErrorResponse();

			if (e instanceof BadRequestException) {
				BadRequestException exception = (BadRequestException) e;
				resp.setStatus(exception.CODE);

				errorResponse.setCode(exception.CODE);
				errorResponse.setMessage(e.getMessage());
				errorResponse.setDate(new Date());
			}

			w.write(new ObjectMapper().writeValueAsString(errorResponse));
			w.flush();
			w.close();
		}
	}

}

package br.com.caelum.agenda.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.agenda.dao.ContatoDao;
import br.com.caelum.agenda.modelo.Contato;

@WebServlet("/adicionaContato")
public class AdicionaContatoServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// busca o writer
		PrintWriter out = response.getWriter();
		
		// buscando os parametros do request
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");
		String dataEmTexto = request.getParameter("dataNascimento");
		Calendar dataNascimento = null;
		
		//fazendo a convesao da data
		
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
			dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(date);
		} catch (ParseException e) {
			out.println("Erro na conversao da data");
			return; // para a execu��o do metodo
		}
		
		// monta um objeto contato
		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setEndereco(endereco);
		contato.setDataNascimento(dataNascimento);
		
		// salva o contato
		ContatoDao dao = new ContatoDao();
		dao.adiciona(contato);
		
		RequestDispatcher rd = request.getRequestDispatcher("/contato-adicionado.jsp");
		rd.forward(request, response);
		
		// imprime o nome do contato que foi adicionado
//		out.println("<html>");
//		out.println("<body>");
//		out.println("Contato " + contato.getNome() + "Adicionado com sucesso");
//		out.println("</body>");
//		out.println("</html>");


	}


}

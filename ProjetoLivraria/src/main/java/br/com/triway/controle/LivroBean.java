package br.com.triway.controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.triway.model.Livro;
import br.com.triway.service.CatalogoService;

@ManagedBean(name = "LivroBean")
@SessionScoped
public class LivroBean implements Serializable {

	private static final String RESULTADO = "Resultado";
	private static final String LIVRO = "Livro";
	private String titulo;
	private Livro livro;
	private List<Livro> livros;
	private CatalogoService service;

	private void init() {
		service = new CatalogoService();
		System.out.println("LivroBean.init()");
	}

	public String pesquisar() {
		livros = service.consultar(titulo);
		return RESULTADO;
	}

	public String verLivro(int codigo) {
		livro = service.verLivro(codigo);
		return LIVRO;
	}
}

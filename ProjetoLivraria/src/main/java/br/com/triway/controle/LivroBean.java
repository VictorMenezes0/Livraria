package br.com.triway.controle;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

import br.com.triway.model.Livro;
import br.com.triway.service.CatalogoService;

@ManagedBean(name = "LivroBean")
@SessionScoped
public class LivroBean implements Serializable {

	private static String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\" ", "");
				return filename.substring(filename.lastIndexOf('/') + 1);
			}
		}
		return null;
	}

	private static final String RESULTADO = "Resultado";
	private static final String LIVRO = "Livro";
	private String titulo;
	private Livro livro;
	private List<Livro> livros;
	private CatalogoService service;
	public String autor;
	public Double preco;
	public String descricao;
	public String imagem;

	private Part file;

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public void upload() {
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String cadastrar() {
		Livro livro = new Livro();
		livro.setTitulo(titulo.toUpperCase());
		livro.setAutor(autor.toUpperCase());
		livro.setPreco(preco);
		livro.setImagem("imagens/");
		livro.setDescricao(descricao);
		System.out.println("LivroBean.cadastrar()");
		service.cadastroLivro(livro);
		return SUCESSO;
	}

	private static final String SUCESSO = "Sucesso";

	@PostConstruct
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

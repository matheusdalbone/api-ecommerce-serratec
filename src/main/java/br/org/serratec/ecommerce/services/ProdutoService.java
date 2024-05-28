package br.org.serratec.ecommerce.services;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.org.serratec.ecommerce.entities.Imagem;
import br.org.serratec.ecommerce.entities.Produto;
import br.org.serratec.ecommerce.exceptions.EntityNotFoundExceptionHandler;
import br.org.serratec.ecommerce.repositories.ImagemRepository;
import br.org.serratec.ecommerce.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	ImagemRepository imagemRepository;

	public List<Produto> findAll() {
		List<Produto> produtos = produtoRepository.findAll();
		if(produtos.isEmpty()) {
			throw new NoSuchElementException("Não existe nunhum produto cadastrado.");
		}
		return produtoRepository.findAll();
	}

	public Produto findById(Integer id) {
		return produtoRepository.findById(id).orElseThrow(
				()-> new EntityNotFoundExceptionHandler("Este produto não existe. Id:"+id));
	}

	@Transactional
	public Produto save(MultipartFile file, Produto produto) throws IOException {
		Produto produtoAux = saveProduto(produto);
		Imagem imagem = saveImagem(file);
		imagemRepository.save(imagem);
		produtoAux.setImagem(imagem);

		return produtoRepository.save(produtoAux);
	}

	public Produto update(Integer id, MultipartFile file, Produto produto) throws IOException {
		Produto produtoBanco = updateProduto(id, produto);
		Imagem imagem = updateImagem(id, file);

		imagemRepository.saveAndFlush(imagem);
		produtoBanco.setImagem(imagem);

		return produtoRepository.saveAndFlush(produtoBanco);
	}

	public Boolean delete(Integer id) {
		if (produtoRepository.existsById(id)) {
			produtoRepository.deleteById(id);
			Produto produtoDeletado = produtoRepository.findById(id).orElse(null);
			if (produtoDeletado == null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public long count() {
		return produtoRepository.count();
	}

	@Transactional
	public Imagem getImagemId(Integer id) {
		Optional<Produto> opImagem = produtoRepository.findById(id);
		if (opImagem.isPresent())
			return opImagem.get().getImagem();
		return null;
	}

	public Produto saveProduto(Produto produto) {
		Produto produtoAux = new Produto();
		produtoAux.setNome(produto.getNome());
		produtoAux.setDescricao(produto.getDescricao());
		produtoAux.setQtdEstoque(produto.getQtdEstoque());
		produtoAux.setDataCadastro(produto.getDataCadastro());
		produtoAux.setValorUnitario(produto.getValorUnitario());
		produtoAux.setCategoria(produto.getCategoria());
		return produtoAux;
	}

	public Produto updateProduto(Integer id, Produto produto) {
		Optional<Produto> opProd = produtoRepository.findById(id);
		if (opProd.isEmpty())
			return null;
		Produto produtoBanco = opProd.get();
		produtoBanco.setNome(produto.getNome());
		produtoBanco.setDescricao(produto.getDescricao());
		produtoBanco.setQtdEstoque(produto.getQtdEstoque());
		produtoBanco.setDataCadastro(produto.getDataCadastro());
		produtoBanco.setValorUnitario(produto.getValorUnitario());
		produtoBanco.setCategoria(produto.getCategoria());
		produtoBanco.setImagem(produto.getImagem());
		return produtoBanco;
	}

	public Imagem saveImagem(MultipartFile file) throws IOException {
		Imagem imagem = new Imagem();
		imagem.setData(file.getBytes());
		imagem.setTipo(file.getContentType());
		return imagem;
	}

	public Imagem updateImagem(Integer id, MultipartFile file) throws IOException {
		Imagem imagem = imagemRepository.findById(id).orElseThrow(
				()-> new EntityNotFoundExceptionHandler("Esta imagem não existe. Id: " + id));
		if (imagem == null) {
			imagem = new Imagem();
			imagem.setIdImagem(id);
		}
		imagem.setData(file.getBytes());
		imagem.setTipo(file.getContentType());
		return imagem;
	}

}

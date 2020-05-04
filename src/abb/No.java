package abb;

public class No<Chave extends Comparable<Chave>, Valor> {

	private No<Chave, Valor> pai;
	private No<Chave, Valor> filhoDir;
	private No<Chave, Valor> filhoEsq;

	private Chave chave;
	private Valor valor;

	No(Chave chave, Valor valor) {
		this.valor = valor;
		this.chave = chave;
	}

	public No<Chave, Valor> getPai() {
		return pai;
	}

	public void setPai(No<Chave, Valor> pai) {
		this.pai = pai;
	}

	public No<Chave, Valor> getFilhoDir() {
		return filhoDir;
	}

	public void setFilhoDir(No<Chave, Valor> filhoDir) {
		this.filhoDir = filhoDir;
	}

	public No<Chave, Valor> getFilhoEsq() {
		return filhoEsq;
	}

	public void setFilhoEsq(No<Chave, Valor> filhoEsq) {
		this.filhoEsq = filhoEsq;
	}

	public Chave getChave() {
		return chave;
	}

	public void setChave(Chave chave) {
		this.chave = chave;
	}

	public Valor getValor() {
		return valor;
	}

	public void setValor(Valor valor) {
		this.valor = valor;
	}

}

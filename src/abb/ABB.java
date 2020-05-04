package abb;

import java.util.LinkedList;

public class ABB<Chave extends Comparable<Chave>, Valor> {

    private No<Chave, Valor> raiz;
    private int tamanho;
    private LinkedList<No<Chave, Valor>> fila = new LinkedList<>();

    public ABB() {
        setRaiz(null);
        this.tamanho = 0;
    }

    public boolean inserir(Chave chave, Valor valor) {
        No<Chave, Valor> novoNo = new No<>(chave, valor);
        No<Chave, Valor> noAtual;
        boolean teste = false;
        if (tamanho == 0) {
            setRaiz(novoNo);
            this.tamanho++;
            teste = true;
        } else {
            noAtual = getRaiz();
            while (teste != true) {
                if (chave.compareTo(noAtual.getChave()) == 0) {
                    noAtual.setValor(novoNo.getValor());
                } else if (chave.compareTo(noAtual.getChave()) > 0) {
                    if (noAtual.getFilhoDir() != null) {
                        noAtual = noAtual.getFilhoDir();
                    } else {
                        noAtual.setFilhoDir(novoNo);
                        novoNo.setPai(noAtual);
                        this.tamanho++;
                        teste = true;
                    }
                } else if (chave.compareTo(noAtual.getChave()) < 0) {
                    if (noAtual.getFilhoEsq() != null) {
                        noAtual = noAtual.getFilhoEsq();
                    } else {
                        noAtual.setFilhoEsq(novoNo);
                        novoNo.setPai(noAtual);
                        this.tamanho++;
                        teste = true;
                    }
                }
            }
        }
        if (teste) {
            return true;
        } else {
            return false;
        }
    }

    public No<Chave, Valor> getFilho(Chave chave, Lado lado) {
        No<Chave, Valor> noBusca = null;
        No<Chave, Valor> no;
        no = get(chave);
        if (lado.equals(Lado.Esquerdo)) {
            noBusca = no.getFilhoEsq();
        } else if (lado.equals(Lado.Direito)) {
            noBusca = no.getFilhoDir();
        }
        return noBusca;
    }

    public No<Chave, Valor> getFilho(No<Chave, Valor> noPai, Lado lado) {
        No<Chave, Valor> noBusca = null;
        if ((noBusca = ABB.this.getFilho(noPai.getChave(), lado)) != null) {
            return noBusca;
        } else {
            System.out.println("Nao existe");
            return null;
        }
    }

    public No<Chave, Valor> get(Chave chave) {
        No<Chave, Valor> noAtual = getRaiz();
        No<Chave, Valor> noBusca = null;
        boolean teste = false;
        while (teste != true) {
            if (chave.compareTo(noAtual.getChave()) == 0) {
                noBusca = noAtual;
                teste = true;
            } else if (chave.compareTo(noAtual.getChave()) > 0) {
                if (noAtual.getFilhoDir() != null) {
                    noAtual = noAtual.getFilhoDir();
                } else {
                    teste = true;
                }
            } else if (chave.compareTo(noAtual.getChave()) < 0) {
                if (noAtual.getFilhoEsq() != null) {
                    noAtual = noAtual.getFilhoEsq();
                } else {
                    teste = true;
                }
            }
        }
        if (teste && noBusca != null) {
            return noBusca;
        } else {
            return noBusca;
        }
    }

    public boolean remove(No<Chave, Valor> node) {
        No<Chave, Valor> noBusca = node;
        No<Chave, Valor> noAtual = null;
        boolean teste = false;
        if (noBusca != null) {
            noAtual = noBusca;
            if (noAtual.getFilhoDir() != null && noAtual.getFilhoEsq() != null) {
                noAtual = noAtual.getFilhoDir();
            }
            if (noAtual.equals(getRaiz())) {
                if (noAtual.getFilhoEsq() != null) {
                    this.raiz = noAtual.getFilhoEsq();
                    this.raiz.setPai(null);
                    noAtual.setFilhoEsq(null);
                    teste = true;
                } else if (noAtual.getFilhoDir() != null) {
                    this.raiz = noAtual.getFilhoDir();
                    noAtual.getFilhoDir().setPai(null);
                    this.raiz.setPai(null);
                    noAtual.setFilhoDir(null);
                    teste = true;
                }
            }
            while (teste != true) {
                if (noAtual.getFilhoDir() != null && noAtual.getFilhoEsq() != null) {
                    noAtual = noAtual.getFilhoEsq();
                } else if (noAtual.getFilhoDir() != null) {
                    noBusca.setChave(noAtual.getChave());
                    noBusca.setValor(noAtual.getValor());
                    noAtual.getFilhoDir().setPai(noAtual.getPai());
                    if (noAtual.getPai().getFilhoDir() != null
                            && noAtual.getPai().getFilhoDir().equals(noAtual)) {
                        noAtual.getPai().setFilhoDir(noAtual.getFilhoDir());
                    } else if (noAtual.getPai().getFilhoEsq() != null
                            && noAtual.getPai().getFilhoEsq().equals(noAtual)) {
                        noAtual.getPai().setFilhoEsq(noAtual.getFilhoDir());
                    }
                    noAtual.setPai(null);
                    noAtual.setFilhoDir(null);
                    this.tamanho--;
                    teste = true;
                } else if (noAtual.getFilhoEsq() != null) {
                    noBusca.setChave(noAtual.getChave());
                    noBusca.setValor(noAtual.getValor());
                    noAtual.getFilhoEsq().setPai(noAtual.getPai());
                    if (noAtual.getPai().getFilhoDir() != null
                            && noAtual.getPai().getFilhoDir().equals(noAtual)) {
                        noAtual.getPai().setFilhoDir(noAtual.getFilhoEsq());
                    } else if (noAtual.getPai().getFilhoEsq() != null
                            && noAtual.getPai().getFilhoEsq().equals(noAtual)) {
                        noAtual.getPai().setFilhoEsq(noAtual.getFilhoEsq());
                    }
                    noAtual.setPai(null);
                    noAtual.setFilhoEsq(null);
                    this.tamanho--;
                    teste = true;
                } else {
                    noBusca.setChave(noAtual.getChave());
                    noBusca.setValor(noAtual.getValor());
                    if (noAtual.getPai().getFilhoDir() != null
                            && noAtual.getPai().getFilhoDir().equals(noAtual)) {
                        noAtual.getPai().setFilhoDir(null);
                    } else if (noAtual.getPai().getFilhoEsq() != null
                            && noAtual.getPai().getFilhoEsq().equals(noAtual)) {
                        noAtual.getPai().setFilhoEsq(null);
                    }
                    noAtual.setPai(null);
                    this.tamanho--;
                    teste = true;
                }
            }
        }
        if (teste) {
            return true;
        } else {
            return false;
        }
    }

    public boolean remove(No<Chave, Valor> noPai, Lado lado) {
        if (noPai != null) {
            if (lado.equals(Lado.Direito) && noPai.getFilhoDir() != null) {
                return remove(noPai.getFilhoDir());
            } else if (lado.equals(Lado.Esquerdo) && noPai.getFilhoEsq() != null) {
                return remove(noPai.getFilhoEsq());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean remove(Chave chave) {
        if (remove(get(chave))) {
            return true;
        } else {
            return false;
        }
    }

    public int altura(Chave chave) {
        No<Chave, Valor> noAtual = getRaiz();
        No<Chave, Valor> noBusca = null;
        int altura = 0;
        boolean teste = false;
        while (teste != true) {
            if (chave.compareTo(noAtual.getChave()) == 0) {
                noBusca = noAtual;
                teste = true;
            } else if (chave.compareTo(noAtual.getChave()) > 0) {
                if (noAtual.getFilhoDir() != null) {
                    noAtual = noAtual.getFilhoDir();
                    altura++;
                } else {
                    teste = true;
                }
            } else if (chave.compareTo(noAtual.getChave()) < 0) {
                if (noAtual.getFilhoEsq() != null) {
                    noAtual = noAtual.getFilhoEsq();
                    altura++;
                } else {
                    teste = true;
                }
            }
        }
        if (teste && noBusca != null) {
            return altura;
        } else {
            System.out.println("Nao existe");
            return -1;
        }
    }

    public No<Chave, Valor> menor() {
        No<Chave, Valor> noAtual = getRaiz();
        while (noAtual.getFilhoEsq() != null) {
            noAtual = noAtual.getFilhoEsq();
        }
        return noAtual;
    }

    public No<Chave, Valor> maior() {
        No<Chave, Valor> noAtual = getRaiz();
        while (noAtual.getFilhoDir() != null) {
            noAtual = noAtual.getFilhoDir();
        }
        return noAtual;
    }

    public boolean vazia() {
        if (tamanho <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public String inOrder() {
        fila.clear();
        inOrder(getRaiz());
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        int auxSize = 0;
        for (No<Chave, Valor> node : fila) {
            builder.append(node.getValor().toString());
            auxSize++;
            if (auxSize < getTamanho()) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    private void inOrder(No<Chave, Valor> node) {
        if (node != null) {
            inOrder(node.getFilhoEsq());
            fila.add(node);
            inOrder(node.getFilhoDir());
        }
    }

    public String preOrder() {
        fila.clear();
        preOrder(getRaiz());
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        int auxSize = 0;
        for (No<Chave, Valor> node : fila) {
            builder.append(node.getValor().toString());
            auxSize++;
            if (auxSize < getTamanho()) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    private void preOrder(No<Chave, Valor> node) {
        if (node != null) {
            fila.add(node);
            preOrder(node.getFilhoEsq());
            preOrder(node.getFilhoDir());
        }
    }

    public String posOrder() {
        fila.clear();
        posOrder(getRaiz());
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        int auxSize = 0;
        for (No<Chave, Valor> no : fila) {
            builder.append(no.getValor().toString());
            auxSize++;
            if (auxSize < getTamanho()) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    private void posOrder(No<Chave, Valor> no) {
        if (no != null) {
            posOrder(no.getFilhoEsq());
            posOrder(no.getFilhoDir());
            fila.add(no);
        }
    }

    @Override
    public String toString() {
        fila.clear();
        if (this.getTamanho() == 0) {
            return "[]";
        }
        No<Chave, Valor> noAtual = null;
        int auxTamanho = 0;
        this.fila.add(getRaiz());
        auxTamanho++;
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(getRaiz().getValor().toString());
        if (auxTamanho < getTamanho()) {
            builder.append(", ");
        }
        while (!this.fila.isEmpty()) {
            noAtual = this.fila.remove();
            if (noAtual.getFilhoEsq() != null) {
                this.fila.add(noAtual.getFilhoEsq());
                builder.append(noAtual.getFilhoEsq().getValor().toString());
                auxTamanho++;
                if (auxTamanho < getTamanho()) {
                    builder.append(", ");
                }
            }
            if (noAtual.getFilhoDir() != null) {
                this.fila.add(noAtual.getFilhoDir());
                builder.append(noAtual.getFilhoDir().getValor().toString());
                auxTamanho++;
                if (auxTamanho < getTamanho()) {
                    builder.append(", ");
                }
            }
        }
        builder.append("]");
        return builder.toString();
    }

    public LinkedList<No<Chave, Valor>> listInOrder() {
        fila.clear();
        inOrder(getRaiz());
        return fila;
    }

    public LinkedList<No<Chave, Valor>> listPreOrder() {
        fila.clear();
        preOrder(getRaiz());
        return fila;
    }

    public LinkedList<No<Chave, Valor>> listPostOrder() {
        fila.clear();
        posOrder(getRaiz());
        return fila;
    }

    public int getTamanho() {
        return tamanho;
    }

    public No<Chave, Valor> getRaiz() {
        return raiz;
    }

    private void setRaiz(No<Chave, Valor> raiz) {
        this.raiz = raiz;
    }

    public boolean busca(Chave chave) {
        No<Chave, Valor> noAtual = getRaiz();
        No<Chave, Valor> noBusca = null;
        boolean teste = false;
        int soma = 0;
        while (teste != true) {
            if (chave.compareTo(noAtual.getChave()) == 0) {
                noBusca = noAtual;
                teste = true;
            } else if (chave.compareTo(noAtual.getChave()) > 0) {
                if (noAtual.getFilhoDir() != null) {
                    noAtual = noAtual.getFilhoDir();
                } else {
                    teste = true;
                }
            } else if (chave.compareTo(noAtual.getChave()) < 0) {
                if (noAtual.getFilhoEsq() != null) {
                    noAtual = noAtual.getFilhoEsq();
                } else {
                    teste = true;
                }
            }
        }
        if (teste && noBusca != null) {
            return true;
        } else {
            return false;
        }
    }

}

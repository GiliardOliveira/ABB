package abb;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        ABB<Integer, Integer> arvore = new ABB<>();
        arvore.inserir(64, 64);
        arvore.inserir(12, 12);
        arvore.inserir(99, 99);
        arvore.inserir(94, 94);
        arvore.inserir(75, 75);
        arvore.inserir(84, 84);
        arvore.inserir(19, 19);
        arvore.inserir(41, 41);
        arvore.inserir(2, 2);
        arvore.inserir(10, 10);
        arvore.inserir(3, 3);
        System.out.println("Arvore" + arvore.toString());

        //Letra A
        System.out.println("Arvore PreOrder: " + arvore.preOrder());
        System.out.println("Arvore PosOrder: " + arvore.posOrder());
        System.out.println("Arvore InOrder: " + arvore.inOrder());

        //Letra B
        if (arvore.busca(64) == true) {
            System.out.println("Existe");
        } else {
            System.out.println("Não Existe");
        }

        //Letra C
        System.out.println("Maior Valor: " + arvore.menor().getValor());
        System.out.println("Menor Valor: " + arvore.maior().getValor());

        //Letra D
        int total = 0;
        for (int i = 0; i < arvore.listInOrder().size(); i++) {
            int soma = arvore.listInOrder().get(i).getValor();
            total = total + soma;
        }
        System.out.println("Media dos valores da arvore: " + (total / arvore.getTamanho()));

        //Letra E
        System.out.println("Tamanho: " + arvore.getTamanho());

        //Letra F
        LinkedList<No> nos = new LinkedList<>();
        for (int i = 0; i < arvore.listInOrder().size(); i++) {
            if (arvore.listInOrder().get(i).getFilhoEsq() == null && arvore.listInOrder().get(i).getFilhoDir() == null) {
                nos.add(arvore.listInOrder().get(i));
            }
        }
        System.out.println("Numero de folhas: " + nos.size());

        //Letra G
        int maiorAltura = 0;
        int aux = 0;
        for (int j = 0; j < arvore.listInOrder().size(); j++) {
            aux = arvore.altura(arvore.listInOrder().get(j).getChave());
            if (maiorAltura <= aux) {
                maiorAltura = aux;
            }
        }
        System.out.println("Altura da arvore: " + maiorAltura);

        //Letra H
        arvore.remove(99);

        System.out.println("Arvore" + arvore.toString());

    }

}

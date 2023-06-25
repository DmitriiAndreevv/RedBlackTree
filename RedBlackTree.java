import java.util.Scanner;

class node {
    node left, right;
    int data;

    // красный ==> true, черный ==> false
    boolean color;

    node(int data) {
        left = null;
        right = null;

// Новый узел, который создается, является всегда красного цвета.
        color = true;
    }
}

public class RedBlackTree {
    private static node root = null;

    node Left(node myNode) {
        System.out.printf("поворот влево!\n");
        node child = myNode.right;
        node childLeft = child.left;

        child.left = myNode;
        myNode.right = childLeft;

        return child;

// Функция для поворота узла против часовой стрелки.
    }

    node Right(node myNode) {
        System.out.printf("поворот вправо!\n");
        node child = myNode.left;
        node childRight = child.right;

        child.right = myNode;
        myNode.left = childRight;

        return child;

// Функция для поворота узла по часовой стрелке.
    }

    boolean isRED(node myNode) {
        if (myNode == null) {
            return false;
        }
        return (myNode.color == true);

// Функция для проверки того, является ли узел красного цвета или нет.
    }

    void changeColor(node node1, node node2) {
        boolean temp = node1.color;
        node1.color = node2.color;
        node2.color = temp;

// Функция для изменения цвета двух узлы.
    }

    // вставка в левостороннее Красно-черное дерево.
    node insert(node myNode, int data) {
// Обычный код вставки для любого двоичного файла
        if (myNode == null) {

            return new node(data);

        }
        if (data < myNode.data) {
            myNode.left = insert(myNode.left, data);

        } else if (data > myNode.data) {
            myNode.right = insert(myNode.right, data);

        } else {
            return myNode;
        }
// 1.правый дочерний элемент красный, а левый дочерний элемент черный или не существует.
        if (isRED(myNode.right) && !isRED(myNode.left)) {
// Повернуть узел влево
            myNode = Left(myNode);

// Поменять местами цвета дочернего узла всегда должен быть красным
            changeColor(myNode, myNode.left);
        }
// 2.левый ребенок, а также левый внук выделены красным цветом
        if (isRED(myNode.left) && isRED(myNode.left.left)) {
// Повернуть узел в право
            myNode = Right(myNode);
            changeColor(myNode, myNode.right);
        }
// 3. левый и правый дочерние элементы окрашены в красный цвет.

        if (isRED(myNode.left) && isRED(myNode.right)) {
// Инвертировать цвет узла это левый и правый дети.
            myNode.color = !myNode.color;

// Изменить цвет на черный.

            myNode.left.color = false;
            myNode.right.color = false;
        }

        return myNode;
    }

// Обход по порядку
    void order(node node) {
        if (node != null) {

            order(node.left);
            char col = '⚫';
            if (node.color == false)
                col = '⭕';
            System.out.print(node.data + "" + col + "");
            order(node.right);
        }
    }

    public static void main(String[] args) {
        RedBlackTree node = new RedBlackTree();
        Scanner scanner = new Scanner(System.in);

        char ch;
        do {
            System.out.println("Введите целое число: ");

            int number = scanner.nextInt();
            root = node.insert(root, number);

            node.order(root);
            System.out.println("\nЖелаете ли вы продолжить? (введите y или n)");
            ch = scanner.next().charAt(0);
        }  while (ch == 'Y' || ch == 'y');
    }
}
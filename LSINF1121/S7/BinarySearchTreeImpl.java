class BinarySearchTreeImpl{

  private class Node{
    int value;
    Node left, right;

    private Node(int value){
      this.value = value;
      this.left = null;
      this.right = null;
    }

    private int getValue(){
      return value;
    }

    private Node getLeft(){
      return left;
    }

    private Node getRight(){
      return right;
    }
  }

  Integer ceil(Node root, int value){
    if(root == null) return null;
    if(value > root.getValue()) return ceil(root.getRight(), value);
    if(value == root.getValue()) return root.getValue();
    if(value < root.getValue()){
        Integer temp = ceil(root.getLeft(), value);
        if(temp == null) return root.getValue();
        else return temp;
    }
    return null;
  }
}

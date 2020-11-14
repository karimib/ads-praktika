public class DijkstraNode<E> extends Node<E> implements Comparable<DijkstraNode<E>>  {
    double dist;
    DijkstraNode<E> prev;
    boolean mark;

    public double getDist (){
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public void setPrev(DijkstraNode<E> p) {
        this.prev = p;
    }
    public DijkstraNode<E> getPrev() {
        return prev;
    }

    public int compareTo(DijkstraNode n) {
        return Double.compare(dist, n.dist);
    }

    public void setMark(boolean b) {
        this.mark = b;
    }

    public boolean getMark() {
        return mark;
    }
}
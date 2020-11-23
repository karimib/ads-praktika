public class LabyrinthNode<E> extends Node<E> {
	private boolean mark;
	private LabyrinthNode<E> prev;

	public boolean getMark() {
		return mark;
	}

	public void setMark(boolean mark) {
		this.mark = mark;
	}

	public LabyrinthNode<E> getPrev() {
		return prev;
	}

	public void setPrev(LabyrinthNode<E> prev) {
		this.prev = prev;
	}
}

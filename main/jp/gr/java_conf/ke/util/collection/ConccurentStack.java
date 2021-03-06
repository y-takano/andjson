package jp.gr.java_conf.ke.util.collection;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class ConccurentStack<E> implements Stack<E>, Blockable {

	private List<E> stack = new LinkedList<E>();

	@Override
	public synchronized void push(E e) {
		if (!stack.add(e)) {
			throw new IndexOutOfBoundsException("限界値到達(over the limit)=" + stack.size());
		}
	}

	@Override
	public synchronized E pop() {
		if (stack.size() == 0) {
			throw new NoSuchElementException("リソース枯渇(Stack is Empty)");
		}
		return stack.remove(stack.size() - 1);
	}

	@Override
	public int size() {
		return stack.size();
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}
}

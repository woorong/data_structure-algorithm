package dsa_assignment1;

public class OrderedMruList<E extends Comparable<E>> implements OrderedMruListInterface<E>
{
	MLNodeInterface<E> head = new MLNode<E>(null);

	public OrderedMruList()
	{
	}

	public boolean isEmptyOrdered()
	{
		/* WRITE THIS CODE */
		return head.getNext1() == head;
	}

	public boolean isEmptyMru()
	{
		/* WRITE THIS CODE */
		return head.getNext2() == head;
	}

	public OrderedMruListInterface<E> touch(MLNodeInterface<E> target)
	{
		/* WRITE THIS CODE */
		target.addAfter2(head);
		return this;
	}

	public MLNodeInterface<E> getFirstMru()
	{
		/* WRITE THIS CODE */
		if (isEmptyMru())
			return null;
		return head.getNext2();
	}

	public MLNodeInterface<E> getFirstOrdered()
	{
		/* WRITE THIS CODE */
		if (isEmptyOrdered())
			return null;
		else
			return head.getNext1();
	}

	public MLNodeInterface<E> getNextOrdered(MLNodeInterface<E> current)
	{
		/* WRITE THIS CODE */
		return current.getNext1();
	}

	public MLNodeInterface<E> getNextMru(MLNodeInterface<E> current)
	{
		/* WRITE THIS CODE */
		return current.getNext2();
	}

	public OrderedMruListInterface<E> remove(MLNodeInterface<E> target)
	{
		/* WRITE THIS CODE */
		target.remove1();
		target.remove2();
		return this;
	}

	public OrderedMruListInterface<E> add(E element)
	{
		/* WRITE THIS CODE */
		MLNodeInterface<E> a = new MLNode<E>(element); //newNode
		MLNodeInterface<E> b = head.getNext1(); //tempHead

		while (b != head && element.compareTo(b.getElement()) > 0)
			b = b.getNext1();

		a.addBefore1(b);
		a.addAfter2(head);

		return this;
	}
}

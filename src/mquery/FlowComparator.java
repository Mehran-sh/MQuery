package mquery;
import java.util.Comparator;

import interfaces.IProperty;

public final class FlowComparator<T, E extends Comparable<E>> implements Comparator<T> {

	IProperty<T, E> property;
	int descValue;
	
	public FlowComparator(IProperty<T, E> property, boolean desc) {
		this.property = property;
		if(desc == true)
		{
			this.descValue = -1;
		}
		else
		{
			this.descValue = 1;
		}
	}

	@Override
	public int compare(T o1, T o2) {
		return this.descValue * property.property(o1).compareTo(property.property(o2));
	}

}

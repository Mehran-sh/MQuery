package mquery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import interfaces.IProperty;
import interfaces.ICondition;

public final class Flow<T> {
	
	private ArrayList<T> data;
	
	private Flow(){}
	
	private void setData(ArrayList<T> data)
	{
		this.data = data;
	}
	
	public static <E> Flow<E> query(ArrayList<E> list)
	{
		Flow<E> flow = new Flow<E>();
		flow.setData(list);
		return flow;
	}
	
	public T first()
	{
		return this.data.get(0);
	}
	
	public T firstOrDefault()
	{
		if(this.data.size() == 0)
		{
			return null;
		}
		else
		{
			return this.first();
		}
	}

	public ArrayList<T> take(int count)
	{
		return (ArrayList<T>) this.data.subList(0, count);
	}
	
	public ArrayList<T> all()
	{
		return this.data;
	}
	
	public Flow<T> where(ICondition<T> condition)
	{
		ArrayList<T> resultList = new ArrayList<>();
		
		for(T item : this.data)
		{
			if(condition.condition(item) == true)
			{
				resultList.add(item);
			}
		}
		
		this.setData(resultList);
		return this;
	}
	
	public <E extends Comparable<E>> Flow<T> orderBy(IProperty<T, E> property)
	{	
		this.data.sort(new FlowComparator<T, E>(property, false));
		
		return this;
	}
	
	public <E extends Comparable<E>> Flow<T> orderByDesc(IProperty<T, E> property)
	{	
		this.data.sort(new FlowComparator<T, E>(property, true));
		
		return this;
	}
	
	public <E extends Number> double sum(IProperty<T, E> property)
	{
		double result = 0;
		for(T item : this.data)
		{
			result += property.property(item).doubleValue();
		}
		return result;
	}

	public <E extends Number> double min(IProperty<T, E> property)
	{
		double result = property.property(this.data.get(0)).doubleValue();
		
		for(T item : this.data)
		{
			double value = property.property(item).doubleValue();
			if(value < result)
			{
				result = value;
			}
		}
		
		return result;
	}
	
	public <E extends Number> double max(IProperty<T, E> property)
	{
		double result = property.property(this.data.get(0)).doubleValue();
		
		for(T item : this.data)
		{
			double value = property.property(item).doubleValue();
			if(value > result)
			{
				result = value;
			}
		}
		
		return result;
	}
	
	public <E, G> Map<E, ArrayList<G>> groupBy(IProperty<T, E> key, IProperty<T, G> value)
	{
		Map<E, ArrayList<G>> result = new HashMap<>();
		
		for(T item : this.data)
		{
			E k = key.property(item);
			G v = value.property(item);
			if(!result.containsKey(k))
			{
				result.put(k,  new ArrayList<>());
			}
			result.get(k).add(v);
		}
		
		return result;
	}

	public <E> Flow<E> select(IProperty<T, E> property)
	{
		ArrayList<E> resultList = new ArrayList<>();
		for(T item : this.data)
		{
			resultList.add(property.property(item));
		}
		Flow<E> result = new Flow<>();
		result.setData(resultList);
		return result;
	}
}

package MergeArrays;

import java.util.Arrays;
import MyException.Exceptions;

public class Array 
{
    
    private int[] array;
    private int size;
    
    public Array(int size)
    {
        array = new int[size];
        this.size = size;
    }
    
    public Array()
    {
        this(1);
    }
    
    public void set(int value, int index) throws Exceptions
    {
        if (index > 0 && array[index - 1] > value)
            throw new Exceptions(size);
        array[index] = value;
    }
    
    public int get(int index)
    {
        return array[index];
    }
    
    public void merge(Array b)
    {
        int[] tempArray = array;
        
        array = new int[size + b.size];
        for(int i = 0; i < size; i++)
        {
            array[i] = tempArray[i];
        }
        
        for(int i = size, j = 0; i < size + b.size; i++, j++)
        {
            array[i] = b.get(j);
        }
        
        size = array.length;
        
        sort();
    }
    
    private void sort()
    {
        Arrays.sort(array);
        
        int temp;
        
        for(int i = 0; i < size / 2; i++)
        {
            temp = array[i];
            array[i] = array[size - i - 1];
            array[size - i - 1] = temp;
        }
    }
    
    @Override
    public String toString()
    {
        String str = new String();
        
        for(int i=1; i<size; i++)
        {
            if(array[i] < array[i-1])
                return "Try again";
        }
        
        for(int i = 0; i < array.length; i ++)
        {
            str += array[i]+ " ";
        }
        
return str;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if ( this == o )
            return true;
        if ( o == null )
            return false;
        if( !( o instanceof Array))
            return false;
        Array a = (Array)o;
        if(size != a.size)
            return false;
        for(int i = 0; i<size; i ++)
        {
            if (array[i] != a.array[i])
                return false;
        }
        
        return true;
    }
    
    @Override
    public int hashCode()
    { 
        int uo = 0;
    
        for(int i = 0; i<size; i++)
        {
            uo += array[i];
        }
        return uo + size;
    }
    

}

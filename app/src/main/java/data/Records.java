package data;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Records {
    private static final Records ourInstance = new Records();
    Map<String,Integer> map = new HashMap<String,Integer>();

    public static Records getInstance(){
        return ourInstance;
    }
    private Records(){
    }

    public void addRecord(String name,int rec){
        map.put(name,rec);
    }
    public Integer getElement(String name){
        return map.get(name);
    }

    public Map<String,Integer> getList() {
        return map;
    }

    public void resetList()
    {
        map = new HashMap<String,Integer>();
    }

    public Map<String,Integer> getOrdered(){
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(map.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public Map<String,Integer> getTOP15(){
        Map<String,Integer> ordered=getOrdered();
        Map<String,Integer> top15=new LinkedHashMap<String,Integer>();
        Iterator it=ordered.entrySet().iterator();
        int i=0;
        while(it.hasNext() && i<15){
            Map.Entry pair = (Map.Entry)it.next();
            String name= (String) pair.getKey();
            Integer score= (Integer) pair.getValue();
            top15.put(name,score);
            i++;
        }
        return top15;
    }

    public String toFile(){
        String text="";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            text=text+entry.getKey()+";"+entry.getValue() + "\n";
        }
        return text;
    }

    @Override
    public String toString() {
        return "Records{" +
                "map=" + map +
                '}';
    }
}

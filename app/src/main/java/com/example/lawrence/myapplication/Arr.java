package com.example.lawrence.myapplication;

/**
 * Created by lawrence on 16/2/22.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Arr
{
    public List<String> nameToSelect;
    public Arr()
    {
        this.nameToSelect=new ArrayList<String>();
    }
    public void Add(String name)
    {

        nameToSelect.add(name);
    }
}

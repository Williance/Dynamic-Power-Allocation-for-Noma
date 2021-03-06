package com.noma.gui;

import java.util.List;

import com.noma.entity.ShannonCapacityData;

/**
 * 
 * Interface for the tasks that need to be done before and after an optimization algorithm is
 * executed
 *
 */
public interface GuiExecutorThreadListener {
    
    public void before();

    public void after(Long timeElapsed, List<ShannonCapacityData> shannonList);
    
}

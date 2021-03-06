package com.ctl.it.qa.staf.xml.reader;

import java.util.List;
/***
 * 
 * @author Mohamed Abdul Kader
 *
 */

public interface IntContainerList {

	/**
	 * Returns this containers list of containers
	 * 
	 * @return - reference to List of data containers
	 */
	public List<IntDataContainer> getDataContainers();
	
	/**
	 * Get reference to the container by index.
	 * 
	 * @param containerIndex - index of the container
	 * @return - reference to the container
	 */
	public IntDataContainer getContainer(int containerIndex);
	
	/**
	 * Get name of the list
	 * 
	 * @return - name of the list
	 */
	public String getName();
	
	/**
	 * Adds a data container to this list
	 * 
	 * @param dataContainer
	 */
	public void addContainer(IntDataContainer dataContainer);
	
	public int size();
	

}

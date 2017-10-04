package com.conygre.spring.components;

import org.springframework.stereotype.Component;

@Component
public class PetrolEngine implements Engine {

	private double engineSize = 2.6;

	/* (non-Javadoc)
	 * @see com.conygre.spring.components.Engine#getEngineSize()
	 */
	@Override
	public double getEngineSize() {
		return engineSize;
	}

	/* (non-Javadoc)
	 * @see com.conygre.spring.components.Engine#setEngineSize(double)
	 */
	@Override
	public void setEngineSize(double engineSize) {
		this.engineSize = engineSize;
	}
	
	
	
}

package com.jdc.atm.model;

import java.util.List;

public interface MoneyChain {

	List<CashDTO> withdraw(int mount);
	
	void addChild(MoneyChain child);
	
	List<CashDTO> getCurrentCash();

	void revert(List<CashDTO> result);
	
}

package com.jdc.atm.model;

import java.util.ArrayList;
import java.util.List;

public enum AtmMachine {

	ATM;

	private MoneyChain chain;

	public void setChain(List<CashDTO> data) {
		chain = null;
		for (CashDTO dto : data) {
			MoneyChain current = new MoneyChainBase(dto.getCash(), dto.getCount());
			if (null == chain) {
				chain = current;
			} else {
				chain.addChild(current);
			}
		}
	}

	public List<CashDTO> getCurrentCash() {
		return null == chain ? new ArrayList<>() : chain.getCurrentCash();
	}

	public List<CashDTO> withDraw(int withdrawAmount) {
		
		List<CashDTO> result = chain.withdraw(withdrawAmount);
		
		int net = result.stream().mapToInt(a -> a.getTotal()).sum();
		
		if(withdrawAmount != net) {
			chain.revert(result);
			throw new IllegalArgumentException("There is no enough cash.");
		}
		
		return result;
	}
}

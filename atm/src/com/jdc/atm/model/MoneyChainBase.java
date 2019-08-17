package com.jdc.atm.model;

import java.util.ArrayList;
import java.util.List;

public class MoneyChainBase implements MoneyChain {

	private MoneyChain child;
	private Kyat cash;
	private int count;

	public MoneyChainBase(Kyat cash, int count) {
		super();
		this.cash = cash;
		this.count = count;
	}

	@Override
	public List<CashDTO> withdraw(int amount) {
		
		int cashCount = amount / cash.getValue();
		int remain = amount % cash.getValue();
		
		List<CashDTO> list = new ArrayList<>();
		if(cashCount > 0) {
			list.add(new CashDTO(cash, cashCount));
			this.count -= cashCount;
		}
		
		if(remain > 0 && null != child) {
			list.addAll(child.withdraw(remain));
		}
		
		return list;
	}

	@Override
	public void addChild(MoneyChain child) {
		if(null == this.child) {
			this.child = child;
		} else {
			this.child.addChild(child);
		}
	}

	@Override
	public List<CashDTO> getCurrentCash() {
		List<CashDTO> list = new ArrayList<>();
		list.add(new CashDTO(cash, count));
		if(null != child) {
			list.addAll(child.getCurrentCash());
		}
		return list;
	}

	@Override
	public void revert(List<CashDTO> result) {

		for (CashDTO cashDTO : result) {
			if(cashDTO.getCash() == cash) {
				this.count += cashDTO.getCount();
			}
		}
		
		if(null != child) {
			child.revert(result);
		}
	}
	
}

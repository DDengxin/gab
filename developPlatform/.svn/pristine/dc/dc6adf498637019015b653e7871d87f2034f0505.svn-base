package com.tengzhi.publicmethod;

import java.util.ArrayList;

import cn.hutool.core.convert.Convert;

/**
 * @author admin
 */
public class ConvertorPool {
	private ConvertorPool() {
	}

	private static final ConvertorPool instance = new ConvertorPool();
	private ArrayList<ConvertorObject> pool = new ArrayList<ConvertorObject>();
	// 池内维护了最大为5个实例，可以根据自己的服务器性能调整最大值
	private static final int maxSize = 5;
	private int availSize = 0;
	private int current = 0;

	public static ConvertorPool getInstance() {
		return instance;
	}

	// 获取池内一个转换实例
	public synchronized ConvertorObject getConvertor() {
		if (availSize > 0) {
			return getIdleConvertor();
		} else if (pool.size() < maxSize) {
			return createNewConvertor();
		} else {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return getConvertor();
		}
	}

	// 使用完成需要还给池内
	public synchronized void returnConvertor(ConvertorObject convertor) {
		for (ConvertorObject co : pool) {
			if (co == convertor) {
				co.available = true;
				availSize++;
				notify();
				break;
			}
		}
	}

	private synchronized ConvertorObject getIdleConvertor() {
		for (ConvertorObject co : pool) {
			if (co.available) {
				co.available = false;
				availSize--;
				return co;
			}
		}
		return null;
	}

	private synchronized ConvertorObject createNewConvertor() {
		ConvertorObject co = new ConvertorObject(++current);
		co.convertor = new Convert();
		co.available = false;
		pool.add(co);
		return co;
	}

	// 包装convert类，可记录是否在使用中
	public class ConvertorObject {
		public ConvertorObject(int id) {
			this.id = id;
		}

		public int id;
		public Convert convertor;
		public boolean available;
	}
}
